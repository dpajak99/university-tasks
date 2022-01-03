package pl.edu.pwsztar.tests;

import pl.edu.pwsztar.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A class with sample data providers
 */
public class CalendarDateSampleData {
    public static List<CalendarDate> getSampleCalendarDateList() {
        List<CalendarDate> calendarDatesList = new ArrayList<>();
        calendarDatesList.add( new CalendarDateDefault(1,1,2020, 3));
        calendarDatesList.add( new CalendarDateSlash(10,2,2019,7));
        calendarDatesList.add( new CalendarDateLong(14,11,2017,2));
        calendarDatesList.add( new CalendarDateRoman(2,3,2020, 1));
        calendarDatesList.add( new CalendarDateDefault(9,6,2020, 2));
        calendarDatesList.add( new CalendarDateSlash(25,7,1999, 7));
        calendarDatesList.add( new CalendarDateShort(14,2,2001, 3));

        return calendarDatesList;
    }

    public static CalendarDate[] getSampleCalendarDateArray() {
        return new CalendarDate[]{
                new CalendarDateSlash(1,1,2020, 3),
                new CalendarDateSlash(10,2,2019, 7),
                new CalendarDateSlash(14,12,2017, 2),
                new CalendarDateSlash(2,3,2020, 1),
                new CalendarDateSlash(9,6,2020, 2),
                new CalendarDateSlash(25,7,1999, 7),
                new CalendarDateSlash(14,2,2001, 3),
        };
    }
}
