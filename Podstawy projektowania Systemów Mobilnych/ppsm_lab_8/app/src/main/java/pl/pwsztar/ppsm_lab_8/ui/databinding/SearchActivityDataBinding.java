package pl.pwsztar.ppsm_lab_8.ui.databinding;


import android.view.View;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;


import pl.pwsztar.ppsm_lab_8.R;
import pl.pwsztar.ppsm_lab_8.ui.adapter.CityListAdapter;


public class SearchActivityDataBinding {
  private View view;
  public RecyclerView recyclerCities;
  public CityListAdapter cityListAdapter;
  public EditText searchView;

  public SearchActivityDataBinding(View view) {
    this.searchView = view.findViewById(R.id.edittext_search);
    this.view = view;
    this.recyclerCities = view.findViewById(R.id.rv_city_list);
  }

  public View getView() {
    return view;
  }
}
