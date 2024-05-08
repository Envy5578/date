package Model;

import java.time.LocalDate;

public class DateInfo {
    private LocalDate date;

    private DayType type;

    private String title;

    public DateInfo(LocalDate date, DayType type) {
        this.date = date;
        this.type = type;
    }

    public DateInfo(LocalDate date, DayType type, String title) {
        this.date = date;
        this.type = type;
        this.title = title;
    }

    public DayType getType() {
        return type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(DayType type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String toString() {
        if (title == null) {
            return date + " " + type;
        }
        return date + " " + type + " " + title;
    }

}
