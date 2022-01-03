package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;
import pl.edu.pwsztar.strings.pl.CalendarStringsPl;

public abstract class CalendarDate implements CalendarDateModel, Comparable<CalendarDate> {
    protected int day;
    protected int month;
    protected int year;
    protected int dayNumber;

    public CalendarDate(){};

    public CalendarDate(int day, int month, int year, int dayNumber) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dayNumber = dayNumber;
    }

    public CalendarDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.dayNumber = 0;
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
        return dayNumber;
    }

    public String getDateString() {
        return getDateString(new CalendarStringsPl());
    }

    public String getDateString(AppStrings appStrings) {
        StringBuilder date = new StringBuilder();
        date.append(day).append("-").append(appStrings.getMonthsShort()[month-1]).append("-").append(year);
        return date.toString();
    }

    public Boolean equals( CalendarDateModel calendarDate ) {
        if( day == calendarDate.getDay() && month == calendarDate.getMonth() && year == calendarDate.getYear()) {
            return true;
        }
        return false;
    }
    public Boolean isSmallerThan( CalendarDateModel calendarDate ) {
        if( year > calendarDate.getYear()) {
            return true;
        } else if( year < calendarDate.getYear()) {
            return false;
        } else if( month > calendarDate.getMonth()) {
            return true;
        } else if( month < calendarDate.getMonth()) {
            return false;
        } else return day < calendarDate.getDay();
    }

    @Override
    public int compareTo(CalendarDate calendarDate) {
        if( isSmallerThan( calendarDate )) {
            return -1;
        } else {
            return 1;
        }
    }
}
