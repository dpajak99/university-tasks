package pl.edu.pwsztar;

import pl.edu.pwsztar.model.CalendarDate;
import pl.edu.pwsztar.model.MyCalendar;
import pl.edu.pwsztar.strings.AppStrings;
import pl.edu.pwsztar.strings.en.CalendarStringsEn;
import pl.edu.pwsztar.strings.pl.CalendarStringsPl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final AppStrings AppStrings = new CalendarStringsEn();

    public static CalendarDate currentCalendarDate;

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1);
        String inputString;

        currentCalendarDate = MyCalendar.RefDate;

        System.out.println(AppStrings.getWelcomeMessage());
        inputString = stdin.readLine();
        int type = Integer.parseInt(inputString);
        System.out.println(AppStrings.getEnterData());

        System.out.println(currentCalendarDate.getDateString(AppStrings, type));
        while(true) {
            inputString = stdin.readLine();
            if( inputString.equals("q")) break;

            currentCalendarDate = doInputOperation(inputString, currentCalendarDate);
            System.out.println(currentCalendarDate.getDateString(AppStrings, type));
        }
    }

    public static CalendarDate doInputOperation( String inputString, CalendarDate calendarDate ) {
        switch (inputString) {
            case "a":
                calendarDate.setDayNumber(currentCalendarDate.getDayNumber());
                return MyCalendar.removeDaysFromDate(calendarDate, -7);
            case "d":
                calendarDate.setDayNumber(currentCalendarDate.getDayNumber());
                return MyCalendar.addDaysToDate(calendarDate, 7);
            default:
                int integer = Integer.parseInt(inputString);
                int days = currentCalendarDate.getDayNumber() + integer;
                if( Math.abs(days) % 7 == 0) {
                    days = 7;
                } else {
                    days = Math.abs(days % 7);
                }
                calendarDate.setDayNumber(days);
                if( integer > 0 ) {
                    return MyCalendar.addDaysToDate(calendarDate, integer);
                } else {
                    return MyCalendar.removeDaysFromDate(calendarDate, integer);
                }
        }
    }
}
