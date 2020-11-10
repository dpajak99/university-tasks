package com.dpajak99;

import com.dpajak99.cotroller.FractionFactory;
import com.dpajak99.exceptions.ZerowyMianownik;
import com.dpajak99.model.Fraction;

public class Main {
    public static void main(String[] args) throws ZerowyMianownik {
        Fraction fractionOne = new Fraction(1, 2);
        Fraction fractionTwo = new Fraction(1,0);

        System.out.println("Dodawanie :" + FractionFactory.addFractions(fractionOne, fractionTwo).toString());
        System.out.println("Odejmowanie :" + FractionFactory.substractFractions(fractionOne, fractionTwo).toString());
    }
}
