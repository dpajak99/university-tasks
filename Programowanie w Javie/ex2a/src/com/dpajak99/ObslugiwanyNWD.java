package com.dpajak99;

import com.dpajak99.exceptions.ExceptionNWD;

import java.util.Scanner;

public class ObslugiwanyNWD {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Podaj x = ");
        int x = sc.nextInt();
        System.out.print("Podaj y = ");
        int y = sc.nextInt();

        try {
            System.out.println(nwd(x, y));
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
        }
    }

    private static int nwd(int x, int y) throws Exception {
        if( x == 0 || y == 0 ) throw new ExceptionNWD("Nie można dzielić przez zero");
        while (x != y) {
            if (x > y) {
                x -= y;
            } else {
                y -= x;
            }
        }
        return x;
    }
}
