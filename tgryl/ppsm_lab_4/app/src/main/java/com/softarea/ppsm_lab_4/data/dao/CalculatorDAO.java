package com.softarea.ppsm_lab_4.data.dao;

import com.softarea.ppsm_lab_4.data.model.CalculatorItemChangeStatusOperation;
import com.softarea.ppsm_lab_4.data.model.CalculatorItemClearOperation;
import com.softarea.ppsm_lab_4.data.model.CalculatorItemInt;
import com.softarea.ppsm_lab_4.data.model.CalculatorItemNormalOperation;
import com.softarea.ppsm_lab_4.data.model.CalculatorItemSumOperation;
import com.softarea.ppsm_lab_4.data.model.CalculatorPanelItem;

import java.util.ArrayList;
import java.util.List;

public class CalculatorDAO {
  public static List<CalculatorPanelItem> getPanelItems() {
    List<CalculatorPanelItem> items = new ArrayList<>();

    items.add( new CalculatorItemClearOperation("AC"));
    items.add( new CalculatorItemChangeStatusOperation("+/-"));
    items.add( new CalculatorItemNormalOperation("%"));
    items.add( new CalculatorItemNormalOperation("/"));

    items.add( new CalculatorItemInt("7"));
    items.add( new CalculatorItemInt("8"));
    items.add( new CalculatorItemInt("9"));
    items.add( new CalculatorItemNormalOperation("x"));

    items.add( new CalculatorItemInt("4"));
    items.add( new CalculatorItemInt("5"));
    items.add( new CalculatorItemInt("6"));
    items.add( new CalculatorItemNormalOperation("-"));

    items.add( new CalculatorItemInt("1"));
    items.add( new CalculatorItemInt("2"));
    items.add( new CalculatorItemInt("3"));
    items.add( new CalculatorItemNormalOperation("+"));

    items.add( new CalculatorItemInt("0"));
    items.add( new CalculatorItemInt(""));
    items.add( new CalculatorItemInt("."));
    items.add( new CalculatorItemSumOperation("="));

    return items;
  }
}
