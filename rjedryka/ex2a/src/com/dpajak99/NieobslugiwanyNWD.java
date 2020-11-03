package com.dpajak99;

import java.io.IOException;
import java.util.Scanner;

public class NieobslugiwanyNWD {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Podaj x = ");
        int x = sc.nextInt();
        System.out.print("Podaj y = ");
        int y = sc.nextInt();

        System.out.println(nwd(x, y));
    }

    private static int nwd(int x, int y) throws Exception {
        if( x == 0 || y == 0 ) throw new Exception();
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
