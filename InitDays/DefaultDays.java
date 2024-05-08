package InitDays;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import Interface.CalendarDaysIntredace;
import Model.DateInfo;
import Model.DayType;

public class DefaultDays implements CalendarDaysIntredace {
    private Map<LocalDate, DateInfo> days = new HashMap<>();
    public DefaultDays() {
        init();
    }

    @Override
    public void init() {}

    @Override
    public void add(String date, DayType type) {
        add(date, type, null);
    }

    @Override
    public void add(String date, DayType type, String title) {
        LocalDate localDate = LocalDate.parse(date);
        days.put(localDate, new DateInfo(localDate, type, title));
    }

    @Override
    public Map<LocalDate, DateInfo> getDays() {
        return days;
    }
    
}
