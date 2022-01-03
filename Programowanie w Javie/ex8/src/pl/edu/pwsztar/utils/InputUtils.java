package pl.edu.pwsztar.utils;

import pl.edu.pwsztar.exceptions.UnknownInputException;

public class InputUtils {
    public static Integer checkIfInteger(String text) throws UnknownInputException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new UnknownInputException("Unknown int input for text: '" +text+"'");
        }
    }
}
