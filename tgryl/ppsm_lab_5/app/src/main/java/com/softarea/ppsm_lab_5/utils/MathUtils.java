package com.softarea.ppsm_lab_5.utils;

import android.util.Log;

import com.softarea.ppsm_lab_5.data.model.ResultHolder;

import java.math.BigDecimal;

public class MathUtils {
  public static String calcEquation(String equation) {
    int equationSize = equation.length();
    if (!isInt(equation.charAt(equationSize - 1)) && !isEquationSkip(equation.charAt(equationSize - 1)) && equation.charAt(equationSize - 1) != '!') {
      equation = StringUtils.removeLastChar(equation);
      equationSize--;
    }
    if ((equationSize == 1 && equation.charAt(equationSize - 1) != '!') || equationSize == 0) {
      return equation;
    }


    int index = 0;
    ResultHolder resultHolder = getNextIntegerFromEquation(equation, index);
    String first = calcIfSpecial(resultHolder.getResult());
    index += resultHolder.getResultLength();
    while (isNotEndOfEquation(index, equationSize)) {
      char operation = getOperation(equation, index);
      index += 1;
      resultHolder = getNextIntegerFromEquation(equation, index);
      String second = calcIfSpecial(resultHolder.getResult());
      index += resultHolder.getResultLength();
      if (isNotEndOfEquation(index, equationSize)) {
        char nextOperation = getOperation(equation, index);
        while (isOperationWithPriority(nextOperation)) {
          if (isOperationWithPriority(nextOperation)) {
            index += 1;
          }
          resultHolder = getNextIntegerFromEquation(equation, index);
          String third = resultHolder.getResult();
          index += resultHolder.getResultLength();
          second = calc(second, third, nextOperation);
          nextOperation = 0;
          if (isNotEndOfEquation(index, equationSize)) {
            nextOperation = getOperation(equation, index);
          }
        }
      }
      first = calc(first, second, operation);
    }

    return first;
  }

  private static boolean isNotEndOfEquation(int index, int equationSize) {
    return (equationSize) > index;
  }

  private static String calcIfSpecial(String equation) {
    StringBuilder result = new StringBuilder();
    if (equation.charAt(0) == 's') {
      for (char a : equation.toCharArray()) {
        if (!isEquationSkip(a)) {
          result.append(a);
        }
      }
      return String.valueOf(Math.sqrt(Double.parseDouble(result.toString())));
    }

    if (equation.charAt(0) == 'l') {
      for (char a : equation.toCharArray()) {
        if (!isEquationSkip(a)) {
          result.append(a);
        }
      }
      return String.valueOf(Math.log10(Double.parseDouble(result.toString())));
    }
    return equation;
  }

  private static char getOperation(String equation, int index) {
    return equation.charAt(index);
  }

  private static ResultHolder getNextIntegerFromEquation(String equation, int index) {
    StringBuilder nextString = new StringBuilder();
    for (int i = index; i < equation.length(); i++) {
      if (equation.charAt(i) == '!') {
        return new ResultHolder(String.valueOf(strong(Double.parseDouble(nextString.toString()))), nextString.length() + 1);
      } else if (isInt(equation.charAt(i)) || isEquationSkip(equation.charAt(i)) || (equation.charAt(i) == '-' && equation.charAt(i - 1) == '(')) {
        nextString.append(equation.charAt(i));
      } else {
        break;
      }
    }
    return new ResultHolder(nextString.toString(), nextString.length());
  }

  public static boolean isEquationSkip(char letter) {
    String[] toSkipArray = {"(", ")", "sqrt", "log"};
    for (String toSkip : toSkipArray) {
      for (char a : toSkip.toCharArray()) {
        if (a == letter) return true;
      }
    }
    return false;
  }

  public static boolean isOperationWithPriority(char letter) {
    char[] operationsWithPriority = {'/', 'x', '^'};
    for (char operationWithPriority : operationsWithPriority) {
      if (operationWithPriority == letter) return true;
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
    for (char a : text.toCharArray()) {
      Log.i("TEST", "a: " + a + "");
      if (!isEquationSkip(a)) {
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
      case '^':
        result = BigDecimal.valueOf(MathUtils.pow(Double.parseDouble(first), Double.parseDouble(second)));
        break;
      default:
        return "Undefined";
    }
    if (result.doubleValue() % 1 == 0.0) {
      return String.valueOf(result.intValue());
    }
    return String.valueOf(result.doubleValue());
  }

  public static double pow(double x, double pow) {
    double result = x;
    for (int i = 1; i < (int) pow; i++) {
      result *= x;
    }
    return result;
  }

  public static double strong(double x) {
    Log.i("TEST", "STRONG: " + x);
    int result = 1;
    for (int i = 1; i <= (int) x; i++) {
      result *= i;
    }
    return result;
  }
}
