package com.softarea.ppsm_lab_5.data.model;

public class CalculatorItemClearOperation implements CalculatorPanelItem{
  private String name;

  public CalculatorItemClearOperation(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String resultOnClick(CalculatorPanelItem previousOperation, String mathText) {
    return "";
  }
}
