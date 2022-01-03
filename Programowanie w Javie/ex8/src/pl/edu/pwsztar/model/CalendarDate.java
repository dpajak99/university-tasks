package pl.edu.pwsztar.model;

import pl.edu.pwsztar.strings.AppStrings;
import pl.edu.pwsztar.strings.pl.CalendarStringsPl;

/**
 * Abstract class for all types of dates
 */
public abstract class CalendarDate implements CalendarDateModel, Comparable<CalendarDate> {
    /**
     * Is holding the day number
     */
    protected int day;

    /**
     * Is holding the month number
     */
    protected int month;

    /**
     * Is holding the year number
     */
    protected int year;

    /**
     * Is holding the day of week number
     */
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

    /**
     * Sets the day of week number
     * @param dayNumber
     */
    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    /**
     * Give the day number
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * Give the month number
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * Give the year number
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Give the day of week number
     * @return dayOfweek
     */
    public int getDayNumber() {
        return dayNumber;
    }

    /**
     * Triggers getDateString(AppString) with default parameter
     * @return getDateString with default parameter
     */
    public String getDateString() {
        return getDateString(new CalendarStringsPl());
    }

    /**
     * Returns date with specific for date type format
     *
     * @param appStrings - strings language
     * @return date in string
     */
    public String getDateString(AppStrings appStrings) {
        StringBuilder date = new StringBuilder();
        date.append(day).append("-").append(appStrings.getMonthsShort()[month-1]).append("-").append(year);
        return date.toString();
    }

    /**
     * Checks if one date is equal to other date
     *
     * @param calendarDate - date to check
     * @return (bool) true or false (isEqual / isNotEqual)
     */
    public Boolean equals( CalendarDateModel calendarDate ) {
        if( day == calendarDate.getDay() && month == calendarDate.getMonth() && year == calendarDate.getYear()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if this date is smaller than param date
     * @param calendarDate - date to check
     * @return (bool) true or false (this date isSmaller or not)
     */
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

    /**
     * Method to Collections sort
     * @param calendarDate - date
     * @return 1 or -1
     */
    @Override
    public int compareTo(CalendarDate calendarDate) {
        if( isSmallerThan( calendarDate )) {
            return -1;
        } else {
            return 1;
        }
    }
}
