package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;

public class CalendarDateSlash extends CalendarDate {
    public CalendarDateSlash(){};
    public CalendarDateSlash(int day, int month, int year, int dayNumber) {
        super(day, month, year, dayNumber);
    }

    public CalendarDateSlash(int day, int month, int year) {
        super(day, month, year);
    }

    @Override
    public String getDateString(AppStrings appStrings ) {
        StringBuilder date = new StringBuilder();
        date.append(day).append("/").append(month).append("/").append(year);
        return date.toString();
    }
}
