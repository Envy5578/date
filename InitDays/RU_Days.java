package InitDays;

import Model.DayType;

public class RU_Days extends DefaultDays{
    private static final String NEW_YEAR = "Новогодние каникулы (в ред. Федерального закона от 23.04.2012 № 35-ФЗ)";
    private static final String CHRISTMAS = "Рождество Христово";
    private static final String DEFENDER_DAY = "День защитника Отечества";
    private static final String WOMEN_DAY = "Международный женский день";
    private static final String MAY_DAY = "Праздник Весны и Труда";
    private static final String VICTORY_DAY = "День Победы";
    private static final String RUSSIA_DAY = "День России";
    private static final String UNITY_DAY = "День народного единства";

    @Override
    public void init() {
        add("2024-01-01", DayType.HOLIDAY, NEW_YEAR);
        add("2024-01-02", DayType.HOLIDAY, NEW_YEAR);
        add("2024-01-03", DayType.HOLIDAY, NEW_YEAR);
        add("2024-01-04", DayType.HOLIDAY, NEW_YEAR);
        add("2024-01-05", DayType.HOLIDAY, NEW_YEAR);
        add("2024-01-06", DayType.HOLIDAY, NEW_YEAR);
        add("2024-01-07", DayType.HOLIDAY, CHRISTMAS);
        add("2024-01-08", DayType.HOLIDAY, NEW_YEAR);
        add("2024-02-22", DayType.SHORTDAY);
        add("2024-02-23", DayType.HOLIDAY, DEFENDER_DAY);
        add("2024-03-07", DayType.SHORTDAY);
        add("2024-03-08", DayType.HOLIDAY, WOMEN_DAY);
        add("2024-05-01", DayType.HOLIDAY, MAY_DAY);
        add("2024-05-08", DayType.SHORTDAY);
        add("2024-05-09", DayType.HOLIDAY, VICTORY_DAY);
        add("2024-06-11", DayType.SHORTDAY);
        add("2024-06-12", DayType.HOLIDAY, RUSSIA_DAY);
        add("2024-11-02", DayType.SHORTDAY);
        add("2024-11-04", DayType.HOLIDAY, UNITY_DAY);
    }
}
