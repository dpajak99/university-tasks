package com.dpajak99;

import com.dpajak99.model.QuadraticEquation;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int a = 1;
        int b = 4;
        int c = 3;

        QuadraticEquation quadraticEquation = new QuadraticEquation(a, b, c);
        System.out.println("Miejsca zerowe: ");
        List<Double> zeros = quadraticEquation.getZeros();
        if (zeros != null) {
            for (Double zero : zeros) {
                System.out.println(zero + ", ");
            }
        } else {
            System.out.println("Brak miejsc zerowych");
        }
    }
}
