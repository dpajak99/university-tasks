package com.dpajak99.cotroller;

import com.dpajak99.exceptions.ZerowyMianownik;
import com.dpajak99.model.Fraction;
import com.dpajak99.utils.MathUtils;

public class FractionFactory {
    public static Fraction addFractions(Fraction one, Fraction two) throws ZerowyMianownik {
        Fraction tmp = one;
        one.setEqualNominative(two);
        two.setEqualNominative(tmp);
        return setShortFraction(new Fraction((one.getNumeral() + two.getNumeral()), one.getNominative()));
    }

    public static Fraction substractFractions(Fraction one, Fraction two) throws ZerowyMianownik {
        Fraction tmp = one;
        one.setEqualNominative(two);
        two.setEqualNominative(tmp);
        return setShortFraction(new Fraction((one.getNumeral() - two.getNumeral()), one.getNominative()));
    }

    public static Fraction setShortFraction(Fraction fraction) throws ZerowyMianownik {
        int nwd = MathUtils.nwd(fraction.getNumeral(), fraction.getNominative());
        return new Fraction( (fraction.getNumeral() / nwd), (fraction.getNominative() / nwd));
    }

}
