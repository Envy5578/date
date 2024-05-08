package Interface;
import java.time.LocalDate;
import java.util.Map;

import Model.DateInfo;
import Model.DayType;

public interface CalendarDaysIntredace {
    void init();

    void add(String date, DayType type);

    void add(String date, DayType type, String title);

    Map<LocalDate, DateInfo> getDays();
}
