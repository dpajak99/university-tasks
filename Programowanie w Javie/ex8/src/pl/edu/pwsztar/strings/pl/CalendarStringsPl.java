package pl.edu.pwsztar.strings.pl;

import pl.edu.pwsztar.strings.AppStrings;

import java.util.Date;

/**
 * Class with Polish text translations
 */
public class CalendarStringsPl implements AppStrings {
    public String WelcomeMessage = "***********************************************\n" +
            "MyCalendar - Dominik Pająk\n" +
            "Wpisz 'a' aby przesunąć o tydzień w tył\n" +
            "Wpisz 'd' aby przesunąć o tydzień w przód\n" +
            "Wpisz 'test' aby wyświetlić testowe daty\n" +
            "Wpisz 'q' aby zakończyć\n" +
            "Wpisz liczbę aby przesunąć o podaną ilość dni\n" +
            ">> Ujemną aby przejść daną ilość dni w tył\n" +
            "***********************************************\n" +
            "**********Rodzaje Wyświetlania Dat*************\n" +
            "***********************************************\n" +
            "1 - wtorek, 1 grudzień\n" +
            "2 - 01 grudzień 2020\n" +
            "3 - 01.XII.2020\n" +
            "4 - 1-gru-2020\n" +
            "5 - 1/12/2020\n" +
            "***********************************************\n" +
            "Podaj typ wyświetlanej daty:";

    public String[] Months = new String[]{"Styczeń", "Luty", "Marzec", "Kwiecień", "Maj", "Czerwiec", "Lipiec", "Sierpień", "Wrzesień", "Październik", "Listopad", "Grudzień"};
    public String[] MonthsShort = new String[]{"sty", "lut", "mar", "kwi", "maj", "cze", "lip", "sie", "wrz", "paź", "lis", "gru"};
    public String[] MonthsRoman = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII"};
    public String EnterData = "Wpisz dni: ";
    public String[] Days = new String[]{"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"};
    public String Error_BadDateType = "Błąd - Podano zły typ daty";


    @Override
    public String getWelcomeMessage() {
        return WelcomeMessage;
    }

    @Override
    public String[] getMonths() {
        return Months;
    }

    @Override
    public String[] getMonthsShort() {
        return MonthsShort;
    }

    @Override
    public String[] getMonthsRoman() {
        return MonthsRoman;
    }

    @Override
    public String getEnterData() {
        return EnterData;
    }

    @Override
    public String[] getDays() {
        return Days;
    }

    @Override
    public String getError_BadDateType() {
        return Error_BadDateType;
    }
}
