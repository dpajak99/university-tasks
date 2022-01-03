package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;

public class CalendarDateLong extends CalendarDate {
    public CalendarDateLong(){};
    public CalendarDateLong(int day, int month, int year, int dayNumber) {
        super(day, month, year, dayNumber);
    }

    public CalendarDateLong(int day, int month, int year) {
        super(day, month, year);
    }

    @Override
    public String getDateString(AppStrings appStrings ) {
        StringBuilder date = new StringBuilder();
        date.append(appStrings.getDays()[dayNumber-1]).append("test, ").append(day).append(" ").append(appStrings.getMonths()[month-1]).append(" ").append(year);
        return date.toString();
    }
}
