package pl.pwsztar.worldtimeapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import pl.pwsztar.worldtimeapp.R;
import pl.pwsztar.worldtimeapp.model.TimeLocalization;

public class LocalizationsAdapter extends RecyclerView.Adapter<LocalizationsAdapter.ViewHolder> {

  private List<TimeLocalization> timeLocalizations = new ArrayList<>();

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView tvCountry;
    public TextView tvTime;
    public ImageView imageFlag;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      tvCountry = itemView.findViewById(R.id.tv_country);
      tvTime = itemView.findViewById(R.id.tv_time);
      imageFlag = itemView.findViewById(R.id.image_flag);
    }
  }

  public void update(List<TimeLocalization> timeLocalizations) {
    this.timeLocalizations.clear();
    this.timeLocalizations.addAll(timeLocalizations);
    this.notifyDataSetChanged();
  }


  public LocalizationsAdapter() {
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View listItem = layoutInflater.inflate(R.layout.item_localization_timebox, parent, false);
    return new ViewHolder(listItem);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    TimeLocalization timeLocalization = timeLocalizations.get(position);
    holder.tvCountry.setText(timeLocalization.getCityName());
    holder.tvTime.setText(timeLocalization.getCountryTime().getTimeString());
    holder.imageFlag.setImageResource(timeLocalization.getFlagImage());
  }

  @Override
  public int getItemCount() {
    return timeLocalizations.size();
  }
}
