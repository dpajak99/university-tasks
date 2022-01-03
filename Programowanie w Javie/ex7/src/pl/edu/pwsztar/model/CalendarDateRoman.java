package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;

public class CalendarDateRoman extends CalendarDate {
    public CalendarDateRoman(){};
    public CalendarDateRoman(int day, int month, int year, int dayNumber) {
        super(day, month, year, dayNumber);
    }

    public CalendarDateRoman(int day, int month, int year) {
        super(day, month, year);
    }

    @Override
    public String getDateString(AppStrings appStrings ) {
        StringBuilder date = new StringBuilder();
        date.append(day).append(".").append(appStrings.getMonthsRoman()[month-1]).append(".").append(year);
        return date.toString();
    }
}
