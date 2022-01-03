package pl.edu.pwsztar;

import pl.edu.pwsztar.exceptions.UnknownInputException;
import pl.edu.pwsztar.model.*;
import pl.edu.pwsztar.strings.AppStrings;
import pl.edu.pwsztar.strings.en.CalendarStringsEn;
import pl.edu.pwsztar.strings.pl.CalendarStringsPl;
import pl.edu.pwsztar.tests.CalendarDateSampleData;
import pl.edu.pwsztar.utils.InputUtils;
import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The main class responsible for handling the entire program
 */
public class Main {
    /**
     * Application language variable
     *
     * CalendarStringsEn() - English
     * CalendarStringsPl() - Polish
     */
    private static final AppStrings AppStrings = new CalendarStringsEn();

    /**
     * An array of all possible date formats
     * CalendarDateLong()    - Tuesday, 1 December 2020
     * CalendarDateShort()   - 1-dec-2020
     * CalendarDateRoman()   - 01.XII.2020
     * CalendarDateDefault() - 01 December 2020
     * CalendarDateSlash()   - 1/12/2020
     */
    public static CalendarDateModel[] currentCalendarDate = {new CalendarDateLong(), new CalendarDateShort(), new CalendarDateRoman(), new CalendarDateDefault(), new CalendarDateSlash()};

    /**
     * The main method responsible for handling the entire program
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, UnknownInputException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1);
        String inputString;

        System.out.println(AppStrings.getWelcomeMessage());
        inputString = stdin.readLine();
        int type = InputUtils.checkIfInteger(inputString);

        System.out.println(AppStrings.getEnterData());

        currentCalendarDate[type] = MyCalendar.RefDate;
        System.out.println(currentCalendarDate[type].getDateString(AppStrings));
        while(true) {
            inputString = stdin.readLine();
            if( inputString.equals("q")) break;

            currentCalendarDate[type] = doInputOperation(inputString, currentCalendarDate[type]);
            System.out.println(currentCalendarDate[type].getDateString(AppStrings));
        }
    }

    /**
     * Method to handle the user's query
     *
     * @param inputString - user-entered text
     * @param calendarDate - current date
     * @return CalendarDateModel
     */
    public static CalendarDateModel doInputOperation( String inputString, CalendarDateModel calendarDate ) throws UnknownInputException {
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
                int integer = InputUtils.checkIfInteger(inputString);
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

    /**
     * Method responding to the "test" command. Prints and sorts sample dates from the calendar
     */
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

    /**
     * Prints out all items in the list
     * @param calendarDatesList - list to print
     */
    private static void printCalendarDatesList(List<CalendarDate> calendarDatesList) {
        System.out.println("\n\nList");
        for( CalendarDate calendarDate : calendarDatesList ) {
            System.out.println(calendarDate.getDateString());
        }
    }

    /**
     * Prints out all items from array
     * @param calendarDatesArray - array to print
     */
    private static void printCalendarDatesArray(CalendarDate[] calendarDatesArray) {
        System.out.println("\n\nArray");
        for( CalendarDate calendarDate : calendarDatesArray ) {
            System.out.println(calendarDate.getDateString());
        }
    }
}
