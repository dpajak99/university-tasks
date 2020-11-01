package com.softarea.ppsm_lab_4.utils;

import android.util.Log;

import java.math.BigDecimal;

public class MathUtils {
  public static String calcEquation(String equation) {
    int equationSize = equation.length();
    if( !isInt(equation.charAt(equationSize-1)) && !isEquationSkip(equation.charAt(equationSize-1))) {
      equation = StringUtils.removeLastChar(equation);
      equationSize--;
    }
    if (equationSize == 1 || equationSize == 0) {
      return equation;
    }


    int index = 0;
    String first = getNextIntegerFromEquation(equation, index);
    index += first.length();
    while(isNotEndOfEquation(index, equationSize)) {
      char operation = getOperation(equation, index);
      index += 1;
      String second = getNextIntegerFromEquation(equation, index);
      index += second.length();
      if(isNotEndOfEquation(index, equationSize)) {
        char nextOperation = getOperation(equation, index);
        while( isOperationWithPriority(nextOperation) ) {
          if( isOperationWithPriority(nextOperation)) {
            index += 1;
          }
          String third = getNextIntegerFromEquation(equation, index);
          index += third.length();
          second = calc(second, third, nextOperation);
          nextOperation = 0;
          if(isNotEndOfEquation(index, equationSize)) {
            nextOperation = getOperation(equation, index);
          }
        }
      }
      first = calc( first, second, operation);
    }

    return first;
  }

  private static boolean isNotEndOfEquation(int index, int equationSize) {
    return (equationSize) > index;
  }

  private static char getOperation( String equation, int index ) {
    return equation.charAt(index);
  }

  private static String getNextIntegerFromEquation( String equation, int index ) {
    StringBuilder nextString = new StringBuilder();
    for( int i = index; i < equation.length(); i++) {
      if( isInt(equation.charAt(i)) || isEquationSkip(equation.charAt(i)) || (equation.charAt(i) == '-' && equation.charAt(i-1) == '(')) {
        nextString.append(equation.charAt(i));
      } else {
        break;
      }
    }
    return nextString.toString();
  }

  public static boolean isEquationSkip(char letter) {
    char[] toSkipArray = { '(', ')' };
    for( char toSkip : toSkipArray ) {
      if( toSkip == letter ) return true;
    }
    return false;
  }

  public static boolean isOperationWithPriority(char letter) {
    char[] operationsWithPriority = { '/', 'x' };
    for( char operationWithPriority : operationsWithPriority ) {
      if( operationWithPriority == letter ) return true;
    }
    return false;
  }

  public static boolean isInt(char a) {
    char[] integers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
    for (char b : integers) {
      if (a == b) {
        return true;
      }
    }
    return false;
  }

  public static String removeEquationSkips(String text) {
    StringBuilder result = new StringBuilder();
    for( char a : text.toCharArray() ) {
      Log.i("TEST", "a: " + a + "");
      if( !isEquationSkip(a)) {
        result.append(a);
      }
    }
    return result.toString();
  }

  public static String calc(String first, String second, char operation) {
    BigDecimal firstInt = BigDecimal.valueOf(Double.parseDouble(removeEquationSkips(first)));
    BigDecimal secondInt = BigDecimal.valueOf(Double.parseDouble(removeEquationSkips(second)));
    BigDecimal result;

    switch (operation) {
      case '+':
        result = firstInt.add(secondInt);
        break;
      case '-':
        result = firstInt.subtract(secondInt);
        break;
      case 'x':
        result = firstInt.multiply(secondInt);
        break;
      case '/':
        if (secondInt.doubleValue() == 0.0) {
          return "Error: Dividing by 0";
        }
        result = firstInt.divide(secondInt);
        break;
      case '%':
        result = firstInt.remainder(secondInt);
        break;
      default:
        return  "Undefined";
    }
    if( result.doubleValue() % 1 == 0.0) {
      return String.valueOf(result.intValue());
    }
    return String.valueOf(result.doubleValue());
  }
}
