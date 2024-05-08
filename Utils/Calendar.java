package Utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import InitDays.DefaultDays;
import Interface.CalendarDaysIntredace;
import Model.DateInfo;
import Model.DayType;

public class Calendar {

    Map<LocalDate, DateInfo> days;

    /**
     * @param sstdCalendarDays SstdCalendarDaysInterface. Наследованный от {@link DefaultDays} объект
     */
    public Calendar(CalendarDaysIntredace sstdCalendarDays) {
        this.days = sstdCalendarDays.getDays();
    }

    /**
     * Конструктор по умолчанию. Без локального производственного календаря.
     */
    public Calendar() {
        DefaultDays defaultDays = new DefaultDays();
        this.days = defaultDays.getDays();
    }

    /**
     * Возвращает true, если date выпадает на выходные (Суббота или Воскресенье)
     *
     * @param date LocalDate - Дата для проверки
     * @return boolean
     */
    public boolean isWeekEnd(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    /**
     * Возвращает true, если date НЕ выпадает на выходные (Рабочая неделя)
     *
     * @param date LocalDate - Дата для проверки
     * @return boolean
     */
    public boolean isWorkWeek(LocalDate date){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY;
    }

    /**
     * Возвращает true, если date отмечен в производственном календаре
     * как Сокращенный рабочий день (DayType.SHORTDAY)
     *
     * @param date LocalDate - Дата для проверки
     * @return boolean
     */
    public boolean isShortDay(LocalDate date) {
        return days.containsKey(date) && days.get(date).getType() == DayType.SHORTDAY;
    }

    /**
     * Возвращает true, если date отмечен в производственном календаре
     * как Рабочий день (DayType.WORKDAY) или date на рабочей неделе
     * и НЕ отмечен как выходной (DayType.HOLIDAY)
     *
     * @param date LocalDate - Дата для проверки
     * @return boolean
     */
    public boolean isWorkDay(LocalDate date){
        if (days.containsKey(date)) {
            if (days.get(date).getType().equals(DayType.WORKDAY))
                return true;
            if (days.get(date).getType().equals(DayType.HOLIDAY))
                return false;
        }
        return isWorkWeek(date);
    }

    /**
     * Возвращает true, если date отмечен в производственном календаре
     * как Выходной день (DayType.HOLIDAY) или date выходной день (Суббота или Воскресенье)
     * и НЕ отмечен как Рабочий день (DayType.WORKDAY) или Сокращенный рабочий день (DayType.SHORTDAY)
     *
     * @param date LocalDate - Дата для проверки
     * @return boolean
     */
    public boolean isHoliday(LocalDate date){
        if(days.containsKey(date)) {
            if (days.get(date).getType().equals(DayType.HOLIDAY))
                return true;
            if (days.get(date).getType().equals(DayType.WORKDAY) || days.get(date).getType().equals(DayType.SHORTDAY))
                return false;
        }
        return isWeekEnd(date);
    }

    /**
     * Возвращает дату через dayInterval календарных дней от date
     *
     * @param date        LocalDate - дата начала отсчета
     * @param dayInterval int - интервал в календарных днях
     * @return LocalDate
     */
    public LocalDate getDateAfterInterval(LocalDate date, int dayInterval) {
        return date.plusDays(dayInterval);
    }

    /**
     * Возвращает дату через dayInterval календарных дней от date
     *
     * @param date            LocalDate - дата начала отсчета
     * @param dayInterval     int - интервал в календарных днях
     * @param includeFirstDay boolean - включить в расчет текущий день
     * @return LocalDate
     */
    public LocalDate getDateAfterInterval(LocalDate date, int dayInterval, boolean includeFirstDay) {
        if (includeFirstDay && dayInterval > 0) dayInterval--;
        return date.plusDays(dayInterval);
    }

    /**
     * Возвращает ближайший рабочей день через dayInterval календарных дней от date
     *
     * @param date        LocalDate - дата начала отсчета
     * @param dayInterval int - интервал в РАБОЧИХ днях
     * @return LocalDate
     */
    public LocalDate getWorkDateAfterInterval(LocalDate date, int dayInterval) {
        LocalDate result = date.plusDays(dayInterval);
        while (isHoliday(result))
            result = result.plusDays(1);
        return result;
    }

    /**
     * Возвращает ближайший рабочей день через dayInterval календарных дней от date
     *
     * @param date            LocalDate - дата начала отсчета
     * @param dayInterval     int - интервал в РАБОЧИХ днях
     * @param includeFirstDay boolean - включить в расчет текущий день
     * @return LocalDate
     */
    public LocalDate getWorkDateAfterInterval(LocalDate date, int dayInterval, boolean includeFirstDay) {
        if (includeFirstDay && dayInterval > 0) dayInterval--;
        LocalDate result = date.plusDays(dayInterval);
        while (isHoliday(result))
            result = result.plusDays(1);
        return result;
    }

    /**
     * Возвращает ближайший рабочий день через dayInterval РАБОЧИХ дней от date
     *
     * @param date LocalDate - дата начала отсчета
     * @param workDayInterval int - интервал в РАБОЧИХ днях
     * @return LocalDate
     */
    public LocalDate getDateAfterWorkDaysInterval(LocalDate date, int workDayInterval) {
        for (int i = 0; i < workDayInterval; i++) {
            date = getWorkDateAfterInterval(date, 1);
        }
        return date;
    }

    /**
     * Возвращает ближайший рабочий день через dayInterval РАБОЧИХ дней от date
     *
     * @param date            LocalDate - дата начала отсчета
     * @param workDayInterval int - интервал в РАБОЧИХ днях
     * @param includeFirstDay boolean - включить в расчет текущий день
     * @return LocalDate
     */
    public LocalDate getDateAfterWorkDaysInterval(LocalDate date, int workDayInterval, boolean includeFirstDay) {
        if (includeFirstDay && workDayInterval > 0) workDayInterval--;
        for (int i = 0; i < workDayInterval; i++) {
            date = getWorkDateAfterInterval(date, 1);
        }
        return date;
    }
    /**
     * Возвращает информацию об указанной дате (дата, тип, наименование праздника)
     *
     * @param date LocalDate - дата проверки
     * @return DateInfo - информация об указанной дате {@link DateInfo}
     */
    public DateInfo getDateInfo(LocalDate date) {
        if (days.containsKey(date))
            return days.get(date);
        else if (isWorkDay(date))
            return new DateInfo(date, DayType.WORKDAY);
        else if (isHoliday(date))
            return new DateInfo(date, DayType.HOLIDAY);
        else
            return null;
    }

    /**
     * Возвращает true, если date выпадает на выходные, также возвращает true
     * если текущее время больше 17:00 и меньше 9:00 по Московскому времени в сокращенный день
     * или текущее время больше 18:00 и меньше 9:00 по Московскому времени в рабочий день.
     * Реализация вывода данных о рабочей смены только на текущий момент времени
     * @param date LocalDate - Дата для проверки
     * @return boolean
     */
    public boolean isDontWorkTime(LocalDate date) {
        LocalDateTime dateTime = LocalDateTime.now();
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        ZonedDateTime zonedDateTime = dateTime.atZone(zoneId);
        System.out.println(zonedDateTime.getHour());
        if(isHoliday(date)){
            return true;
        }
        if(isShortDay(date)){
            return zonedDateTime.getHour() <= 9 && zonedDateTime.getHour() >= 17;
        }
        return zonedDateTime.getHour() <= 9 && zonedDateTime.getHour() >= 18;
    }
}
