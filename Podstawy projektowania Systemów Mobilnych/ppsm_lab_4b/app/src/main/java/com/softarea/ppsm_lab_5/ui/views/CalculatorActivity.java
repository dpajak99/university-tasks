package com.softarea.ppsm_lab_5.ui.views;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softarea.ppsm_lab_5.R;
import com.softarea.ppsm_lab_5.data.dao.CalculatorDAO;
import com.softarea.ppsm_lab_5.ui.adapters.CalculatorPanelAdapter;
import com.softarea.ppsm_lab_5.ui.adapters.PreviousOperationAdapter;

import java.util.ArrayList;


public class CalculatorActivity extends Activity {
  private RecyclerView recyclerCalculatorPanel;
  public RecyclerView listPreviousOperation;
  public PreviousOperationAdapter previousOperationAdapter;
  public TextView notepad;
  private CalculatorController calculatorController;
  private static int GRID_COUNTS;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calculator);
    setUpViews();
    setUpCalculatorController();
    int orientation = getResources().getConfiguration().orientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      GRID_COUNTS = 5;
    } else {
      GRID_COUNTS = 4;
    }
    setUpAdapters();
  }

  public void setUpViews() {
    notepad = findViewById(R.id.tv_notepad);
    recyclerCalculatorPanel = findViewById(R.id.gl_calculator_panel);
    listPreviousOperation = findViewById(R.id.list_previous_operations);
  }

  public void setUpCalculatorController() {
    calculatorController = new CalculatorController(CalculatorActivity.this);
  }

  public void setUpAdapters() {
    previousOperationAdapter = new PreviousOperationAdapter();
    listPreviousOperation.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    listPreviousOperation.setAdapter(previousOperationAdapter);

    CalculatorPanelAdapter calculatorPanelAdapter = new CalculatorPanelAdapter(getApplicationContext(), calculatorController, GRID_COUNTS);
    recyclerCalculatorPanel.setHasFixedSize(true);
    recyclerCalculatorPanel.setLayoutManager(new GridLayoutManager(getApplicationContext(), GRID_COUNTS));
    recyclerCalculatorPanel.setAdapter(calculatorPanelAdapter);
    if( GRID_COUNTS == 5 ) {
      calculatorPanelAdapter.update(CalculatorDAO.getLandscapePanelItems());
    } else {
      calculatorPanelAdapter.update(CalculatorDAO.getPortraitPanelItems());
    }
  }

  public void update( String result ) {
    notepad.setText(result);
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    savedInstanceState.putString("NOTEPAD", notepad.getText().toString());
    savedInstanceState.putStringArrayList("HISTORY", (ArrayList<String>) previousOperationAdapter.getPreviousOperations());
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    notepad.setText(savedInstanceState.getString("NOTEPAD"));
    previousOperationAdapter.update(savedInstanceState.getStringArrayList("HISTORY"));
  }
}
