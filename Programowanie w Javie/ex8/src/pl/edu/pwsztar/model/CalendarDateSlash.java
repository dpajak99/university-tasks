package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;

/**
 * Stores the date in the format 01/02/2020
 */
public class CalendarDateSlash extends CalendarDate {
    public CalendarDateSlash(){};
    public CalendarDateSlash(int day, int month, int year, int dayNumber) {
        super(day, month, year, dayNumber);
    }
    public CalendarDateSlash(int day, int month, int year) {
        super(day, month, year);
    }

    /**
     * Returns the date in the format 01/02/2020
     */
    @Override
    public String getDateString(AppStrings appStrings ) {
        StringBuilder date = new StringBuilder();
        date.append(day).append("/").append(month).append("/").append(year);
        return date.toString();
    }
}
