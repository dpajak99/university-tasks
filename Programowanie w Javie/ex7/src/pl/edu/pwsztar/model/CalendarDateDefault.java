package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;

public class CalendarDateDefault extends CalendarDate {
    public CalendarDateDefault(){};
    public CalendarDateDefault(int day, int month, int year, int dayNumber) {
        super(day, month, year, dayNumber);
    }
    public CalendarDateDefault(int day, int month, int year) {
        super(day, month, year);
    }

    @Override
    public String getDateString(AppStrings appStrings ) {
        StringBuilder date = new StringBuilder();
        date.append(day).append(" ").append(dayNumber).append(appStrings.getMonths()[month-1]).append(" ").append(year);
        return date.toString();
    }
}
