package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;

/**
 * Stores the date in the format Tuesday, 1 December 2020
 */
public class CalendarDateLong extends CalendarDate {
    public CalendarDateLong(){};
    public CalendarDateLong(int day, int month, int year, int dayNumber) {
        super(day, month, year, dayNumber);
    }
    public CalendarDateLong(int day, int month, int year) {
        super(day, month, year);
    }

    /**
     * Returns the date in the format Tuesday, 1 December 2020
     */
    @Override
    public String getDateString(AppStrings appStrings ) {
        StringBuilder date = new StringBuilder();
        date.append(appStrings.getDays()[dayNumber-1]).append(", ").append(day).append(" ").append(appStrings.getMonths()[month-1]).append(" ").append(year);
        return date.toString();
    }
}
