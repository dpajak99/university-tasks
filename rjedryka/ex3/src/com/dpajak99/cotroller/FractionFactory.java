package com.dpajak99.cotroller;

import com.dpajak99.model.Fraction;

public class FractionFactory {
    public static Fraction addFractions(Fraction one, Fraction two) {
        if( one.getNominative() != two.getNominative()) {
            one.setNominative(one.getNominative() * two.getNominative());
            one.setNumeral(one.getNumeral() * two.getNominative());

            two.setNominative(two.getNominative() * one.getNominative());
            two.setNumeral(two.getNumeral() * one.getNominative());
        }
        
    }
}
