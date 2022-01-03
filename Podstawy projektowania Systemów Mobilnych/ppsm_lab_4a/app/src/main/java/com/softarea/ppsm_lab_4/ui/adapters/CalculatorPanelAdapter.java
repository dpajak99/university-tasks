package com.softarea.ppsm_lab_4.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.softarea.ppsm_lab_4.R;
import com.softarea.ppsm_lab_4.data.model.CalculatorPanelItem;
import com.softarea.ppsm_lab_4.ui.views.CalculatorController;
import com.softarea.ppsm_lab_4.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CalculatorPanelAdapter extends RecyclerView.Adapter<CalculatorPanelAdapter.ViewHolder> {

  private List<CalculatorPanelItem> panelItems = new ArrayList<>();
  private Context context;
  private CalculatorController controller;

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textOperation;
    public LinearLayout layout;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      textOperation = itemView.findViewById(R.id.text_operation);
      layout = itemView.findViewById(R.id.layout);
    }
  }

  public void update(List<CalculatorPanelItem> panelItems) {
    this.panelItems.clear();
    this.panelItems.addAll(panelItems);
    this.notifyDataSetChanged();
  }


  public CalculatorPanelAdapter(Context context, CalculatorController controller) {
    this.context = context;
    this.controller = controller;
  }

  @NonNull
  @Override
  public CalculatorPanelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View listItem = layoutInflater.inflate(R.layout.item_calculator_panel, parent, false);
    return new ViewHolder(listItem);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    CalculatorPanelItem panelItem = panelItems.get(position);
    holder.textOperation.setText(panelItem.getName());
    if( (position+1) % 4 == 0) {
      holder.layout.setBackgroundColor(context.getColor(R.color.colorPrimary));
    }
    holder.layout.setOnClickListener( view -> {
      controller.updateNotepad( panelItem );
    });

  }

  @Override
  public int getItemCount() {
    return panelItems.size();
  }
}
