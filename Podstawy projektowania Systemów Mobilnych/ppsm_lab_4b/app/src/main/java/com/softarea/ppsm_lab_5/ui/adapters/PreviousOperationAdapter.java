package com.softarea.ppsm_lab_5.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softarea.ppsm_lab_5.R;

import java.util.ArrayList;
import java.util.List;

public class PreviousOperationAdapter extends RecyclerView.Adapter<PreviousOperationAdapter.ViewHolder> {

  private List<String> previousOperations = new ArrayList<>();

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.tv_history);
    }
  }

  public void update(List<String> panelItems) {
    this.previousOperations.clear();
    this.previousOperations.addAll(panelItems);
    this.notifyDataSetChanged();
  }


  public PreviousOperationAdapter() {
  }

  public List<String> getPreviousOperations() {
    return previousOperations;
  }

  @NonNull
  @Override
  public PreviousOperationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View listItem = layoutInflater.inflate(R.layout.item_previous_operation, parent, false);
    return new ViewHolder(listItem);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    String previousOperation = previousOperations.get(position);
    holder.textView.setText(previousOperation);
  }

  @Override
  public int getItemCount() {
    return previousOperations.size();
  }
}
