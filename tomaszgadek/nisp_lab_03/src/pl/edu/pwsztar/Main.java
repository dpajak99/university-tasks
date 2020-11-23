package pl.edu.pwsztar;

import java.util.Arrays;

class EmptyDataException extends Exception {}

// Random commit

class Main {

    private final static String ERROR_EMPTY_DATA = "Tablica nie moze byc pusta!";

    private static int getMinFromDigits(Integer[] digits) throws EmptyDataException {

        return Arrays
                .stream(digits == null ? new Integer[]{} : digits)
                .min(Integer::compare)
                .orElseThrow(EmptyDataException::new);
    }

    private static int getMaxFromDigits(Integer[] digits) throws EmptyDataException {

        return Arrays
                .stream(digits == null ? new Integer[]{} : digits)
                .max(Integer::compare)
                .orElseThrow(EmptyDataException::new);
    }

    public static void main(String[] args) {

        Integer[] listOfDigits = {1,2,3,4,5,5,4,3,1,2};

        try {
            Integer minFromDigits = Main.getMinFromDigits(listOfDigits);
            Integer minFromDigits = Main.getMaxFromDigits(listOfDigits);
            System.out.printf("MIN = %d \n", minFromDigits);
            System.out.printf("MAX = %d \n", minFromDigits);
        } catch(EmptyDataException e) {

            System.out.println(ERROR_EMPTY_DATA);
        }
    }
}