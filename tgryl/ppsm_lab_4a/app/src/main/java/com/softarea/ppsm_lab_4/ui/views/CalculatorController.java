package com.softarea.ppsm_lab_4.ui.views;

import android.util.Log;
import android.widget.Toast;

import com.softarea.ppsm_lab_4.data.model.CalculatorItemSumOperation;
import com.softarea.ppsm_lab_4.data.model.CalculatorPanelItem;
import com.softarea.ppsm_lab_4.ui.adapters.CalculatorPanelAdapter;
import com.softarea.ppsm_lab_4.utils.MathUtils;
import com.softarea.ppsm_lab_4.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CalculatorController {
  private CalculatorActivity activity;
  private CalculatorPanelItem previousOperation;
  private List<String> contentPreviousOperations;

  public CalculatorController(CalculatorActivity activity) {
    this.contentPreviousOperations = new ArrayList<>();
    this.activity = activity;
  }

  public void updateNotepad(CalculatorPanelItem panelItem) {
    String mathText = activity.notepad.getText().toString();
    String result = panelItem.resultOnClick(previousOperation, mathText);
    if( result == null ) return;

    if(panelItem instanceof CalculatorItemSumOperation) {
      if( !MathUtils.isInt(mathText.charAt(mathText.length()-1)) && !MathUtils.isEquationSkip(mathText.charAt(mathText.length()-1))) {
        mathText = StringUtils.removeLastChar(mathText);
      }
      this.contentPreviousOperations.add(StringUtils.join(mathText, "=", result));
      activity.previousOperationAdapter.update(this.contentPreviousOperations);
      activity.listPreviousOperation.smoothScrollToPosition(contentPreviousOperations.size());
    }
    activity.update(result);
    previousOperation = panelItem;
  }
}
