package pl.pwsztar.ppsm_lab_7.ui.databinding;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import pl.pwsztar.ppsm_lab_7.R;
import pl.pwsztar.ppsm_lab_7.ui.adapter.CityListAdapter;


public class WeatherActivityDataBinding {
  private View view;
  public Button btnChangeCity;
  public TextView tvCityName;
  public TextView tvTime;
  public TextView tvTemp;
  public TextView tvPressure;
  public TextView tvHumidity;
  public TextView tvTempMin;
  public TextView tvTempMax;
  public ImageView imageWeather;


  public WeatherActivityDataBinding(View view) {
    this.view = view;
    this.btnChangeCity = view.findViewById(R.id.button_change_city);
    this.tvCityName = view.findViewById(R.id.tv_city_name);
    this.tvTime = view.findViewById(R.id.tv_time);
    this.tvTemp = view.findViewById(R.id.tv_temp);
    this.tvPressure = view.findViewById(R.id.tv_pressure);
    this.tvHumidity = view.findViewById(R.id.tv_humidity);
    this.tvTempMin = view.findViewById(R.id.tv_tempMin);
    this.tvTempMax = view.findViewById(R.id.tv_tempMax);
    this.imageWeather = view.findViewById(R.id.image_weather);
  }

  public View getView() {
    return view;
  }
}
