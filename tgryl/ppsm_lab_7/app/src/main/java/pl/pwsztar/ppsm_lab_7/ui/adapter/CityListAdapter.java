package pl.pwsztar.ppsm_lab_7.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import pl.pwsztar.ppsm_lab_7.R;
import pl.pwsztar.ppsm_lab_7.data.model.City;
import pl.pwsztar.ppsm_lab_7.utils.StringUtils;


public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> implements Filterable {

  private List<City> cities = new ArrayList<>();
  private List<City> filteredCities = new ArrayList<>();
  private Context context;
  private FragmentActivity activity;

  public static class ViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ImageView ivStatus;
    public LinearLayout layout;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      name = itemView.findViewById(R.id.tv_city_name);
      ivStatus = itemView.findViewById(R.id.status);
      layout = itemView.findViewById(R.id.layout);
    }
  }

  public void update(List<City> cities) {
    Collections.reverse(cities);
    this.cities = cities;
    this.filteredCities = cities;
    this.notifyDataSetChanged();
  }

  public CityListAdapter(FragmentActivity activity) {
    this.activity = activity;
  }

  @NonNull
  @Override
  public CityListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    context = parent.getContext();
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View listItem = layoutInflater.inflate(R.layout.item_city, parent, false);
    return new ViewHolder(listItem);
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    City city = filteredCities.get(position);
    holder.name.setText(city.getName());
    if (city.isAvailable()) {
      holder.ivStatus.setImageResource(R.drawable.ic_dot_success);
    } else {
      holder.ivStatus.setImageResource(R.drawable.ic_dot_error);
    }

    holder.layout.setOnClickListener(view -> {
      if (city.isAvailable()) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("CITY_NAME", city.getName());
        activity.setResult(Activity.RESULT_OK, returnIntent);
        activity.finish();
      } else {
        Toast.makeText(context, "Miasto nie odnalezione", Toast.LENGTH_SHORT).show();
      }
    });
  }

  @Override
  public int getItemCount() {
    return filteredCities.size();
  }

  @Override
  public Filter getFilter() {
    return exampleFilter;
  }

  private final Filter exampleFilter = new Filter() {
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
      List<City> filteredList = new ArrayList<>();

      String filterPattern = StringUtils.normalize(constraint.toString().toLowerCase()).trim();
      String[] arrOfStr = filterPattern.split(" ", 5);

      if (constraint.length() == 0) {
        filteredList.addAll(cities);
      } else {

        for (City item : cities) {
          boolean status = false;
          for (String pattern : arrOfStr) {
            if (item.getName().toLowerCase().contains(pattern)) {
              status = true;
            } else {
              status = false;
              break;
            }
          }

          if (status) {
            filteredList.add(item);
          }
        }
      }
      FilterResults results = new FilterResults();
      results.values = filteredList;
      return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
      filteredCities = (ArrayList<City>) results.values;
      notifyDataSetChanged();
    }
  };
}
