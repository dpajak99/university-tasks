package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.en.CalendarStringsEn;
import pl.edu.pwsztar.strings.pl.CalendarStringsPl;

public class MyCalendar {
    public static final CalendarDate StartDate = new CalendarDate(23, 11, 2020);

    public static int pareMonthIfOutOfRange( int month ) {
        if( month < 12 ) {
            return month;
        } else {
            while( month > 12 ) {
                month -= 12;
            }
            return month;
        }
    }

    public static String getMonthInString(int month, String lang) {
        month = pareMonthIfOutOfRange( month );
        switch (lang) {
            case "PL":
                return CalendarStringsPl.Months[month - 1];
            default:
                return CalendarStringsEn.Months[month - 1];
        }
    }

    public static int getDayCountInMonth(int month, int year) {
        month = pareMonthIfOutOfRange( month );
        int daysInFeb = 28;
        if (isLapYear(year)) {
            daysInFeb = 29;
        }
        int[] daysInMonth = {31, daysInFeb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysInMonth[(month - 1)];
    }

    public static boolean isLapYear(int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else if (year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static CalendarDate addDaysToDate( CalendarDate calendarDate, int days ) {
        int currentDay = calendarDate.getDay();
        int currentMonth = calendarDate.getMonth();
        int currentYear = calendarDate.getYear();
        int remainingDays = days;

        while( remainingDays > 0 ) {
            int daysInMonth = getDayCountInMonth(currentMonth, currentYear);
            if( currentDay + remainingDays > daysInMonth ) {
                remainingDays -= daysInMonth - currentDay + 1;
                currentDay = 1;
                currentMonth += 1;
                if( currentMonth > 12 ) {
                    currentMonth = 1;
                    currentYear += 1;
                }
            } else {
                currentDay += remainingDays;
                remainingDays = 0;
            }
        }
        return new CalendarDate(currentDay, currentMonth, currentYear);
    }

    public static CalendarDate removeDaysFromDate( CalendarDate calendarDate, int days ) {
        int currentDay = calendarDate.getDay();
        int currentMonth = calendarDate.getMonth();
        int currentYear = calendarDate.getYear();
        int remainingDays = (days * (-1));

        while( remainingDays > 0 ) {
            if( currentDay - remainingDays <= 0 ) {
                currentMonth -= 1;
                remainingDays -= currentDay;
                currentDay = 31;
                if( currentMonth < 0 ) {
                    currentMonth = 12;
                    currentYear -= 1;
                }
            } else {
                currentDay -= remainingDays;
                remainingDays = 0;
            }
        }
        return new CalendarDate(currentDay, currentMonth, currentYear);
    }
}
