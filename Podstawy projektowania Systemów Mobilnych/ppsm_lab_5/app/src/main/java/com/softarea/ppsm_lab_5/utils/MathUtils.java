package com.softarea.ppsm_lab_5.utils;

import android.util.Log;

import com.softarea.ppsm_lab_5.data.model.ResultHolder;

import org.mariuszgromada.math.mxparser.Expression;

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

    equation = equation.replace('x', '*');

    Expression e = new Expression(equation);
    return String.valueOf(e.calculate());
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

  public static boolean isInt(char a) {
    char[] integers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.'};
    for (char b : integers) {
      if (a == b) {
        return true;
      }
    }
    return false;
  }
}
