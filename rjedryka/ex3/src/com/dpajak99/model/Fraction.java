package com.dpajak99.model;

public class Fraction {
    private int numeral;
    private int nominative;

    public Fraction(int numeral, int nominative) {
        this.numeral = numeral;
        this.nominative = nominative;
    }

    public int getNumeral() {
        return numeral;
    }

    public int getNominative() {
        return nominative;
    }

    public void setEqualNominative(Fraction template ) {
        if( this.nominative == template.getNominative()) return;
        this.nominative *= template.getNominative();
        this.numeral *= template.getNominative();
    }

    @Override
    public String toString() {
        return numeral + " / " + nominative;
    }
}
