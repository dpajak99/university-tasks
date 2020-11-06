package com.softarea.ppsm_lab_5.data.model;

import com.softarea.ppsm_lab_5.utils.StringUtils;

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
      mathText = StringUtils.removeCharsByRange(mathText, previousOperation.getName().length());
    }
    mathText = StringUtils.join(mathText, this.name);
    return mathText;
  }

  private boolean isInstanceOfThis(CalculatorPanelItem calculatorPanelItem ) {
    return calculatorPanelItem instanceof CalculatorItemNormalOperation;
  }
}
