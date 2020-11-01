package com.softarea.ppsm_lab_4.utils;

import android.util.Log;

import java.text.Normalizer;
import java.util.List;

public class StringUtils {
  public static String EMPTY_STRING = "";
  public static String join(Object... values) {
    StringBuilder sb = new StringBuilder("");
    for (int i = 0; i < values.length; i++) {
      sb.append(values[i]);
    }
    return sb.toString();
  }

  public static String removeCharByIndex( String stringToRemove, int index ) {
    StringBuilder tmp = new StringBuilder();
    int sizeOfString = stringToRemove.length();
    if( index <  sizeOfString - 1 ) {
      for( int i = index + 1; i < sizeOfString; i++) {
        tmp.append(stringToRemove.charAt(i));
      }
    }
    stringToRemove = stringToRemove.substring(0, stringToRemove.length()-index);
    if( tmp.length() != 0) {
      stringToRemove = StringUtils.join(stringToRemove, tmp);
    }
    return stringToRemove;
  }

  public static String removeLastChar( String stringToRemove ) {
    return stringToRemove.substring(0, stringToRemove.length()-1);
  }

  public static String removeCharsByRange( String stringToRemove, int index ) {
    Log.i("TEST", "I comed here");
    return stringToRemove.substring(0, stringToRemove.length()-index);
  }
}
