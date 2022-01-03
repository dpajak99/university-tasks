package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;

/**
 * Stores the date in the format 01.XII.2020
 */
public class CalendarDateRoman extends CalendarDate {
    public CalendarDateRoman(){};
    public CalendarDateRoman(int day, int month, int year, int dayNumber) {
        super(day, month, year, dayNumber);
    }

    public CalendarDateRoman(int day, int month, int year) {
        super(day, month, year);
    }

    /**
     * Returns the date in the format 01.XII.2020
     */
    @Override
    public String getDateString(AppStrings appStrings ) {
        StringBuilder date = new StringBuilder();
        date.append(day).append(".").append(appStrings.getMonthsRoman()[month-1]).append(".").append(year);
        return date.toString();
    }
}
