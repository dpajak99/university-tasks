package com.softarea.ppsm_lab_4.data.model;

import com.softarea.ppsm_lab_4.utils.StringUtils;

public class CalculatorItemInt implements CalculatorPanelItem{

  private final String name;

  public CalculatorItemInt(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String resultOnClick(CalculatorPanelItem previousOperation, String mathText) {
    return StringUtils.join(mathText, this.name);
  }
}
