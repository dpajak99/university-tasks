package com.dpajak99.model;

public class Fraction {
    private double numeral;
    private double nominative;

    public Fraction(double numeral, double nominative) {
        this.numeral = numeral;
        this.nominative = nominative;
    }

    public double getNumeral() {
        return numeral;
    }

    public double getNominative() {
        return nominative;
    }

    public void setNumeral(double numeral) {
        this.numeral = numeral;
    }

    public void setNominative(double nominative) {
        this.nominative = nominative;
    }
}
