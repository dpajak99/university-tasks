package com.dpajak99.utils;

public class MathUtils {
    public static int nwd( int a, int b) {
        while(a!=b)
            if(a>b)
                a-=b;
            else
                b-=a;
        return a;
    }
}
