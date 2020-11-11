package com.softarea.ppsm_lab_5.data.model;

import com.softarea.ppsm_lab_5.utils.StringUtils;

public class CalculatorStrongOperation implements CalculatorPanelItem{

  private String name;

  public CalculatorStrongOperation(String name) {
    this.name = name;
  };

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String resultOnClick(CalculatorPanelItem previousOperation, String mathText) {
    if( !isInstanceOfInt(previousOperation)) {
      return null;
    }
    int sizeOfMathText = mathText.length();
    if( mathText.charAt(sizeOfMathText -1) == '!') {
      return nameNormal(mathText);
    } else {
      return makeSpecial(mathText);
    }
  }

  private boolean isInstanceOfInt( CalculatorPanelItem calculatorPanelItem) {
    return calculatorPanelItem instanceof CalculatorItemInt;
  }

  private boolean isInstanceOfThis( CalculatorPanelItem calculatorPanelItem) {
    return calculatorPanelItem instanceof CalculatorItemChangeStatusOperation;
  }


  private String nameNormal(String text) {
    return StringUtils.removeLastChar(text);
  }

  private String makeSpecial(String text) {
    return StringUtils.join(text, '!');
  }
}
