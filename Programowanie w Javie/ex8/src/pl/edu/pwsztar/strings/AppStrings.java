package pl.edu.pwsztar.strings;

import pl.edu.pwsztar.strings.pl.CalendarStringsPl;

/**
 * Interface that stores all methods for translation
 */
public interface AppStrings {
    String getWelcomeMessage();
    String[] getMonths();
    String[] getMonthsShort();
    String[] getMonthsRoman();
    String getEnterData();
    String[] getDays();
    String getError_BadDateType();
}
