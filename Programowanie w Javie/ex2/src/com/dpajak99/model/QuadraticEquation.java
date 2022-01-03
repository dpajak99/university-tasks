package com.dpajak99.model;

import java.util.ArrayList;
import java.util.List;

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;
    private double delta;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.delta = ((b * b) - (4 * a * c));
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getDelta() {
        return delta;
    }

    public List<Double> getZeros() {
        if( getDelta() > 0 ) {
            return getZerosIfDeltaMoreThanZero();
        } else if( getDelta() == 0 ) {
            return getZerosIfDeltaLessThanZero();
        }
        return null;
    }

    private List<Double> getZerosIfDeltaMoreThanZero() {
        List<Double> result = new ArrayList<>();
        result.add( ((-1 * b) + Math.sqrt(delta)) / (2 * a));
        result.add( ((-1 * b) - Math.sqrt(delta)) / (2 * a));
        return result;
    }

    private List<Double> getZerosIfDeltaLessThanZero() {
        List<Double> result = new ArrayList<>();
        result.add( (-1 * b) / (2 * a));
        return result;
    }
}
