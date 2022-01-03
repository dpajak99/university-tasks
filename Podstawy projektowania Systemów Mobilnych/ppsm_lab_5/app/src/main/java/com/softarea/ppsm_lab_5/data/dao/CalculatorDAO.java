package com.softarea.ppsm_lab_5.data.dao;

import com.softarea.ppsm_lab_5.data.model.CalculatorItemChangeStatusOperation;
import com.softarea.ppsm_lab_5.data.model.CalculatorItemClearOperation;
import com.softarea.ppsm_lab_5.data.model.CalculatorItemInt;
import com.softarea.ppsm_lab_5.data.model.CalculatorItemNormalOperation;
import com.softarea.ppsm_lab_5.data.model.CalculatorItemSumOperation;
import com.softarea.ppsm_lab_5.data.model.CalculatorPanelItem;
import com.softarea.ppsm_lab_5.data.model.CalculatorSpecialOperation;
import com.softarea.ppsm_lab_5.data.model.CalculatorStrongOperation;

import java.util.ArrayList;
import java.util.List;

public class CalculatorDAO {
  public static List<CalculatorPanelItem> getPortraitPanelItems() {
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

  public static List<CalculatorPanelItem> getLandscapePanelItems() {
    List<CalculatorPanelItem> items = getPortraitPanelItems();
    items.add( 4, new CalculatorSpecialOperation("log10"));
    items.add( 9, new CalculatorStrongOperation("x!"));
    items.add( 14, new CalculatorSpecialOperation("sqrt"));
    items.add( 19, new CalculatorItemNormalOperation("^3"));
    items.add( 24, new CalculatorItemNormalOperation("^2"));
    return items;
  }


}
