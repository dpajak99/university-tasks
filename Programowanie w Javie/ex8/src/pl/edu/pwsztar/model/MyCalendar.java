package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.en.CalendarStringsEn;
import pl.edu.pwsztar.strings.pl.CalendarStringsPl;

import java.sql.Ref;

/**
 * A class that handles all available calendar events
 */
public class MyCalendar {
    /**
     * Variable that stores the calendar date from which the program starts
     */
    public static final CalendarDateModel RefDate = new CalendarDateDefault(2, 1, 1950, 1);

    /**
     * Change the month if it is greater than 12
     *
     * @param month - month
     * @return (int) parsedMonth
     */
    public static int parseMonthIfOutOfRange(int month ) {
        if( month < 12 ) {
            return month;
        } else {
            while( month > 12 ) {
                month -= 12;
            }
            return month;
        }
    }

    /**
     * Returns the number of days in a month for a given year
     *
     * @param month - month to check
     * @param year - the year for which the month we want to check
     * @return (int) day count in month
     */
    public static int getDayCountInMonth(int month, int year) {
        month = parseMonthIfOutOfRange( month );
        int daysInFeb = 28;
        if (isLapYear(year)) {
            daysInFeb = 29;
        }
        int[] daysInMonth = {31, daysInFeb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysInMonth[(month - 1)];
    }

    /**
     * Checks if the year is leap year
     *
     * @param year - year to check
     * @return (bool) true or false
     */
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

    /**
     * Adds days to date
     *
     * @param calendarDate - date to which we want to add days
     * @param days - the number of days we want to add
     * @return date with added days
     */
    public static CalendarDate addDaysToDate( CalendarDateModel calendarDate, int days ) {
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
        return new CalendarDateDefault( currentDay, currentMonth, currentYear, calendarDate.getDayNumber() );
    }

    /**
     * Removes days to date
     *
     * @param calendarDate - date we want to remove days from
     * @param days - the number of days we want to remove
     * @return date with removed days
     */
    public static CalendarDate removeDaysFromDate( CalendarDateModel calendarDate, int days ) {
        int currentDay = calendarDate.getDay();
        int currentMonth = calendarDate.getMonth();
        int currentYear = calendarDate.getYear();
        int remainingDays = (days * (-1));

        while( remainingDays > 0 ) {
            if( currentDay - remainingDays <= 0 ) {
                currentMonth -= 1;
                remainingDays -= currentDay;
                currentDay = getDayCountInMonth(currentMonth, currentYear);
                if( currentMonth < 0 ) {
                    currentMonth = 12;
                    currentYear -= 1;
                }
            } else {
                currentDay -= remainingDays;
                remainingDays = 0;
            }
        }
        return new CalendarDateDefault(currentDay, currentMonth, currentYear, calendarDate.getDayNumber());
    }

    /**
     * Never used method - created for the homework
     */
    public static int getDayNumberFromRefDate( CalendarDateModel calendarDate ) {
        int daySum = 0;
        if( calendarDate.isSmallerThan(RefDate)) {
            while( !calendarDate.equals(RefDate) ) {
                calendarDate = addDaysToDate(calendarDate, 1);
                daySum++;
            }
        } else {
            while( !calendarDate.equals(RefDate) ) {
                calendarDate = removeDaysFromDate(calendarDate, -1);
                daySum++;
            }
        }
        return daySum;
    }
}
