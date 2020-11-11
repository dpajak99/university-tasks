package com.softarea.ppsm_lab_4.data.model;

import com.softarea.ppsm_lab_4.utils.StringUtils;

public class CalculatorItemNormalOperation implements CalculatorPanelItem{
  private String name;

  public CalculatorItemNormalOperation(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String resultOnClick(CalculatorPanelItem previousOperation, String mathText) {
    if( mathText.length() == 0 ) return null;
    if( isInstanceOfThis(previousOperation) ) {
      mathText = StringUtils.removeLastChar(mathText);
    }
    mathText = StringUtils.join(mathText, this.name);
    return mathText;
  }

  private boolean isInstanceOfThis(CalculatorPanelItem calculatorPanelItem ) {
    return calculatorPanelItem instanceof CalculatorItemNormalOperation;
  }
}
