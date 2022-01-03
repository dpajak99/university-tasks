package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;

public interface CalendarDateModel {
    void setDayNumber(int dayNumber);
    int getDay();
    int getMonth();
    int getYear();
    int getDayNumber();
    String getDateString();
    String getDateString(AppStrings appStrings);
    Boolean equals( CalendarDateModel calendarDate );
    Boolean isSmallerThan( CalendarDateModel calendarDate );
}
