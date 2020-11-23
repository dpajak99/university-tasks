package pl.edu.pwsztar;

import pl.edu.pwsztar.model.CalendarDate;
import pl.edu.pwsztar.model.MyCalendar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /***
     * Months language
     * PL or EN
     *
     * default: EN
     */
    private static final String LANGUAGE = "PL";

    public static CalendarDate calendarDate;

    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in), 1);
        String inputString;

        calendarDate = MyCalendar.StartDate;

        System.out.println("***********************************************");
        System.out.println("MyCalendar - Dominik Pająk");
        System.out.println("Wpisz 'a' aby przesunąć o tydzień w tył");
        System.out.println("Wpisz 'd' aby przesunąć o tydzień w przód");
        System.out.println("Wpisz 'q' aby zakończyć");
        System.out.println("Wpisz liczbę aby przesunąć o podaną ilość dni ");
        System.out.println(">> Ujemną aby przejść daną ilość dni w tył ");
        System.out.println("***********************************************");

        System.out.println("\n\n" + calendarDate.getDateString(LANGUAGE));
        while(true) {
            inputString = stdin.readLine();
            if( inputString.equals("q")) break;

            calendarDate = doInputOperation(inputString, calendarDate);
            System.out.println(calendarDate.getDateString(LANGUAGE));
        }
    }

    public static CalendarDate doInputOperation( String inputString, CalendarDate calendarDate ) {
        switch (inputString) {
            case "a":
                return MyCalendar.removeDaysFromDate(calendarDate, -7);
            case "d":
                return MyCalendar.addDaysToDate(calendarDate, 7);
            default:
                int integer = Integer.parseInt(inputString);
                if( integer > 0 ) {
                    return MyCalendar.addDaysToDate(calendarDate, integer);
                } else {
                    return MyCalendar.removeDaysFromDate(calendarDate, integer);
                }
        }
    }
}
