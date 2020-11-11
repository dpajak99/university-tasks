package com.softarea.ppsm_lab_4.ui.views;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.softarea.ppsm_lab_4.R;
import com.softarea.ppsm_lab_4.data.dao.CalculatorDAO;
import com.softarea.ppsm_lab_4.ui.adapters.CalculatorPanelAdapter;
import com.softarea.ppsm_lab_4.ui.adapters.PreviousOperationAdapter;

import java.util.ArrayList;
import java.util.List;


public class CalculatorActivity extends Activity {
  private RecyclerView recyclerCalculatorPanel;
  public RecyclerView listPreviousOperation;
  public PreviousOperationAdapter previousOperationAdapter;
  public TextView notepad;
  private CalculatorController calculatorController;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calculator);
    setUpViews();
    setUpCalculatorController();
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

    CalculatorPanelAdapter calculatorPanelAdapter = new CalculatorPanelAdapter(getApplicationContext(), calculatorController);
    recyclerCalculatorPanel.setHasFixedSize(true);
    recyclerCalculatorPanel.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4));
    recyclerCalculatorPanel.setAdapter(calculatorPanelAdapter);
    calculatorPanelAdapter.update(CalculatorDAO.getPanelItems());
  }

  public void update( String result ) {
    notepad.setText(result);
  }
}
