package com.softarea.ppsm_lab_3.utils;

import java.util.Random;

public class MathUtils {
  public static int getRandom( int range ) {
    Random rd = new Random();
    int random = rd.nextInt()%range;
    if( random < 0 ) {
      random *= -1;
    }
    return random;
  }
}
