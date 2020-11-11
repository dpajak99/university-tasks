package com.softarea.ppsm_lab_4.data.model;

import com.softarea.ppsm_lab_4.utils.MathUtils;

public class CalculatorItemSumOperation implements CalculatorPanelItem {
  private String name;

  public CalculatorItemSumOperation( String name ) {
    this.name = name;
  }
  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String resultOnClick(CalculatorPanelItem previousOperation, String mathText) {
    if( mathText.length() == 0 ) return null;
    return MathUtils.calcEquation(mathText);
  }
}
