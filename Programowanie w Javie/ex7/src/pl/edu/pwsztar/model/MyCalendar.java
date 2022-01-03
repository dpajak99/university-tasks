package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.en.CalendarStringsEn;
import pl.edu.pwsztar.strings.pl.CalendarStringsPl;

import java.sql.Ref;

public class MyCalendar {
    public static final CalendarDateModel RefDate = new CalendarDateDefault(30, 11, 2020, 1);

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

    public static int getDayCountInMonth(int month, int year) {
        month = parseMonthIfOutOfRange( month );
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

    //Mało optymalna metoda - Stworzona na potrzeby lekcji
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
