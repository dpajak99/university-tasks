package pl.edu.pwsztar.strings.en;

import pl.edu.pwsztar.strings.AppStrings;

public class CalendarStringsEn implements AppStrings {
    public String WelcomeMessage = "***********************************************\n" +
            "MyCalendar - Dominik Pająk\n" +
            "Type 'a' to move back one week\n" +
            "Type 'd' to move forward one week\n" +
            "Type 'q' to finish\n" +
            "Enter a number to shift the specified number of days\n" +
            ">> Negative to go back the given number of days\n" +
            "***********************************************\n" +
            "*************Types of Date Display*************\n" +
            "***********************************************\n" +
            "1 - Tuesday, 1 December\n" +
            "2 - 01 December 2020\n" +
            "3 - 01.XII.2020\n" +
            "4 - 1-dec-2020\n" +
            "***********************************************\n" +
            "Enter the type of date displayed:";

    public String[] Months = new String[]{"January", "February", "February", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public String[] MonthsShort = new String[]{"jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec"};
    public String[] MonthsRoman = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII"};
    public String EnterData = "Enter days to move: ";
    public String[] Days = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public String Error_BadDateType = "Error - Bad Date Type";

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
