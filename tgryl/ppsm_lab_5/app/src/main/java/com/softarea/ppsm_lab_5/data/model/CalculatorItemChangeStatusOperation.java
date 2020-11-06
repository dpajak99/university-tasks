package com.softarea.ppsm_lab_5.data.model;

import android.util.Log;

import com.softarea.ppsm_lab_5.utils.MathUtils;
import com.softarea.ppsm_lab_5.utils.StringUtils;

public class CalculatorItemChangeStatusOperation implements CalculatorPanelItem{
  private String name;

  public CalculatorItemChangeStatusOperation(String name) {
    this.name = name;
  };

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String resultOnClick(CalculatorPanelItem previousOperation, String mathText) {
    if( !isInstanceOfInt(previousOperation) && !isInstanceOfThis(previousOperation)) {
      return null;
    }
    int sizeOfMathText = mathText.length();
    Log.i("TEST", mathText.charAt(sizeOfMathText -1) + "");
    if( mathText.charAt(sizeOfMathText -1) == ')') {
      return makePositive(mathText);
    } else {
      return makeNegative(mathText);
    }
  }

  private boolean isInstanceOfInt( CalculatorPanelItem calculatorPanelItem) {
    return calculatorPanelItem instanceof CalculatorItemInt;
  }

  private boolean isInstanceOfThis( CalculatorPanelItem calculatorPanelItem) {
    return calculatorPanelItem instanceof CalculatorItemChangeStatusOperation;
  }


  private String makePositive(String text) {
    StringBuilder integerToChange = new StringBuilder();
    int sizeOfText = text.length();
    while( text.charAt(sizeOfText - 1) != '(') {
      if( text.charAt(sizeOfText - 1) == ')' || text.charAt(sizeOfText - 1) == '-')  {
        sizeOfText -= 1;
        continue;
      }
      integerToChange.append(text.charAt(sizeOfText-1));
      sizeOfText -= 1;
    }
    text = StringUtils.removeCharsByRange(text, (integerToChange.length() + 3));
    return StringUtils.join(text, integerToChange.reverse());
  }

  private String makeNegative(String text) {
    StringBuilder integerToChange = new StringBuilder();
    int sizeOfText = text.length();
    while(MathUtils.isInt(text.charAt(sizeOfText-1)) ) {
      integerToChange.append(text.charAt(sizeOfText-1));
      sizeOfText -= 1;
      if( sizeOfText == 0 ) break;
    }
    text = StringUtils.removeCharsByRange(text, integerToChange.length());
    return StringUtils.join(text, "(-", integerToChange.reverse(), ')');
  }
}
