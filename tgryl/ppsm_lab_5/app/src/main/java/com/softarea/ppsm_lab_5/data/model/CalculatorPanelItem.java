package com.softarea.ppsm_lab_5.data.model;

public interface CalculatorPanelItem {
  String getName();
  String resultOnClick( CalculatorPanelItem previousOperation, String mathText );
}
