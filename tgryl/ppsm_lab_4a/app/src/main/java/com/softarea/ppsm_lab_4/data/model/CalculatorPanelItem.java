package com.softarea.ppsm_lab_4.data.model;

import com.softarea.ppsm_lab_4.utils.StringUtils;

public interface CalculatorPanelItem {
  String getName();
  String resultOnClick( CalculatorPanelItem previousOperation, String mathText );
}
