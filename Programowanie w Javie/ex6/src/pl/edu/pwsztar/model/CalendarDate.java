package pl.edu.pwsztar.model;

public class CalendarDate {
    private int day;
    private int month;
    private int year;

    public CalendarDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
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

    public String getDateString() {
        return getDateString("DEFAULT");
    }

    public String getDateString( String lang ) {
        StringBuilder date = new StringBuilder();
        date.append(day).append(" ").append(MyCalendar.getMonthInString(month, lang)).append(" ").append(year);
        return date.toString();
    }
}
