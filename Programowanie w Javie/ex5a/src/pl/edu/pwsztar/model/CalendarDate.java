package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;
import pl.edu.pwsztar.strings.pl.CalendarStringsPl;

public class CalendarDate {
    private int day;
    private int month;
    private int year;
    private int dayNumber;

    public CalendarDate(int day, int month, int year, int dayNumber) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dayNumber = dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getDayNumber() {
        if( dayNumber == 0) {
            System.out.println("GetDayNumber - badVersion");
            this.dayNumber = MyCalendar.getDayNumberFromRefDate(new CalendarDate(day, month,year,0));
        }
        return dayNumber;
    }

    public String getDateString() {
        return getDateString(new CalendarStringsPl(), 1);
    }

    public String getDateString(AppStrings appStrings, int type ) {
        StringBuilder date = new StringBuilder();
        switch (type) {
            case 1:
                date.append(appStrings.getDays()[dayNumber-1]).append(", ").append(day).append(" ").append(appStrings.getMonths()[month-1]).append(" ").append(year);
                return date.toString();
            case 2:
                date.append(day).append(" ").append(appStrings.getMonths()[month-1]).append(" ").append(year);
                return date.toString();
            case 3:
                date.append(day).append(".").append(appStrings.getMonthsRoman()[month-1]).append(".").append(year);
                return date.toString();
            case 4:
                date.append(day).append("-").append(appStrings.getMonthsShort()[month-1]).append("-").append(year);
                return date.toString();
            default:
                return appStrings.getError_BadDateType();
        }
    }

    public Boolean equals( CalendarDate calendarDate ) {
        if( day == calendarDate.day && month == calendarDate.getMonth() && year == calendarDate.getYear()) {
            return true;
        }
        return false;
    }
    public Boolean isSmallerThan( CalendarDate calendarDate ) {
        if( calendarDate.getYear() < year) {
            return false;
        }
        if( calendarDate.getMonth() < month ) {
            return false;
        }
        if( calendarDate.getDay() < day ) {
            return false;
        }
        return true;
    }
}
