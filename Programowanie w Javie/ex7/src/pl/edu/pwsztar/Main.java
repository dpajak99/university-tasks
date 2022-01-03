package pl.edu.pwsztar;

import pl.edu.pwsztar.model.*;
import pl.edu.pwsztar.strings.AppStrings;
import pl.edu.pwsztar.strings.en.CalendarStringsEn;
import pl.edu.pwsztar.strings.pl.CalendarStringsPl;
import pl.edu.pwsztar.tests.CalendarDateSampleData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    private static final AppStrings AppStrings = new CalendarStringsEn();

    public static CalendarDateModel[] currentCalendarDate = {new CalendarDateLong(), new CalendarDateShort(), new CalendarDateRoman(), new CalendarDateDefault()};

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1);
        String inputString;

        System.out.println(AppStrings.getWelcomeMessage());
        inputString = stdin.readLine();
        int type = Integer.parseInt(inputString);
        System.out.println(AppStrings.getEnterData());
        currentCalendarDate[type] = MyCalendar.RefDate;
        System.out.println("type: " + type + " " + currentCalendarDate[type]);
        System.out.println(currentCalendarDate[type].getDateString(AppStrings));
        while(true) {
            inputString = stdin.readLine();
            if( inputString.equals("q")) break;

            currentCalendarDate[type] = doInputOperation(inputString, currentCalendarDate[type]);
            System.out.println(currentCalendarDate[type].getDateString(AppStrings));
        }
    }

    public static CalendarDateModel doInputOperation( String inputString, CalendarDateModel calendarDate ) {
        switch (inputString) {
            case "test":
                printSampleDatas();
                return calendarDate;
            case "a":
                calendarDate.setDayNumber(calendarDate.getDayNumber());
                return MyCalendar.removeDaysFromDate(calendarDate, -7);
            case "d":
                calendarDate.setDayNumber(calendarDate.getDayNumber());
                return MyCalendar.addDaysToDate(calendarDate, 7);
            default:
                int integer = Integer.parseInt(inputString);
                int days = calendarDate.getDayNumber() + integer;
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

    private static void printSampleDatas() {
        List<CalendarDate> calendarDatesList = CalendarDateSampleData.getSampleCalendarDateList();
        printCalendarDatesList(calendarDatesList);

        CalendarDate[] calendarDatesArray = CalendarDateSampleData.getSampleCalendarDateArray();
        printCalendarDatesArray(calendarDatesArray);

        Collections.sort(calendarDatesList, CalendarDate::compareTo);
        printCalendarDatesList(calendarDatesList);

        Arrays.sort(calendarDatesArray, CalendarDate::compareTo);
        printCalendarDatesArray(calendarDatesArray);
    }

    private static void printCalendarDatesList(List<CalendarDate> calendarDatesList) {
        System.out.println("\n\nList");
        for( CalendarDate calendarDate : calendarDatesList ) {
            System.out.println(calendarDate.getDateString());
        }
    }

    private static void printCalendarDatesArray(CalendarDate[] calendarDatesArray) {
        System.out.println("\n\nArray");
        for( CalendarDate calendarDate : calendarDatesArray ) {
            System.out.println(calendarDate.getDateString());
        }
    }
}
