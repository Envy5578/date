import java.time.LocalDate;
import java.util.Scanner;

import InitDays.RU_Days;
import Interface.CalendarDaysIntredace;
import Utils.Calendar;

public class Date {
    public static void main(String[] args) {
        //Инициализация производственного календаря
        CalendarDaysIntredace calendarDaysIntredace = new RU_Days();
        Calendar c = new Calendar(calendarDaysIntredace);
        //Ввод даты
        LocalDate dateInput = LocalDate.parse(new Scanner(System.in).nextLine());
        //Использование текущей
        LocalDate dateNow = LocalDate.now();
        //Вывод результата
        System.out.println(c.isDontWorkTime(dateNow));
        System.out.println(c.isDontWorkTime(dateInput));
        
    }
}
