package com.softarea.ppsm_lab_5.data.model;

import com.softarea.ppsm_lab_5.utils.MathUtils;
import com.softarea.ppsm_lab_5.utils.StringUtils;

public class CalculatorSpecialOperation implements CalculatorPanelItem{
  private String name;

  public CalculatorSpecialOperation(String name) {
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
    if( mathText.charAt(sizeOfMathText -1) == ')') {
      return makeNormal(mathText);
    } else {
      return makeSpecial(mathText);
    }
  }

  private boolean isInstanceOfInt( CalculatorPanelItem calculatorPanelItem) {
    return calculatorPanelItem instanceof CalculatorItemInt;
  }

  private boolean isInstanceOfThis( CalculatorPanelItem calculatorPanelItem) {
    return calculatorPanelItem instanceof CalculatorSpecialOperation;
  }


  private String makeNormal(String text) {
    StringBuilder integerToChange = new StringBuilder();
    int sizeOfText = text.length();
    while( text.charAt(sizeOfText - 1) != '(') {
      if( text.charAt(sizeOfText - 1) == ')')  {
        sizeOfText -= 1;
        continue;
      }
      integerToChange.append(text.charAt(sizeOfText-1));
      sizeOfText -= 1;
    }
    text = StringUtils.removeCharsByRange(text, (integerToChange.length() + getName().length() + 2));
    return StringUtils.join(text, integerToChange.reverse());
  }

  private String makeSpecial(String text) {
    StringBuilder integerToChange = new StringBuilder();
    int sizeOfText = text.length();
    while(MathUtils.isInt(text.charAt(sizeOfText-1)) ) {
      integerToChange.append(text.charAt(sizeOfText-1));
      sizeOfText -= 1;
      if( sizeOfText == 0 ) break;
    }
    text = StringUtils.removeCharsByRange(text, integerToChange.length());
    return StringUtils.join(text, getName(), "(", integerToChange.reverse(), ')');
  }
}
