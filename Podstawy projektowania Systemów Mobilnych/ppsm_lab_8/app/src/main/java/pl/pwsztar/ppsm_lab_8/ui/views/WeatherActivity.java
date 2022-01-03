package pl.pwsztar.ppsm_lab_8.ui.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;


import pl.pwsztar.ppsm_lab_8.R;
import pl.pwsztar.ppsm_lab_8.data.model.WeatherDetails;
import pl.pwsztar.ppsm_lab_8.ui.databinding.WeatherActivityDataBinding;
import pl.pwsztar.ppsm_lab_8.ui.viewmodel.WeatherActivityViewModel;
import pl.pwsztar.ppsm_lab_8.ui.views.MainActivity;
import pl.pwsztar.ppsm_lab_8.utils.NetworkUtils;
import pl.pwsztar.ppsm_lab_8.utils.StringUtils;


public class WeatherActivity extends AppCompatActivity implements Observer {
    private WeatherActivityViewModel viewModel;
    private WeatherActivityDataBinding binding;
    private SwipeRefreshLayout swipeRefresh;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        binding = new WeatherActivityDataBinding(view);
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("CITY_NAME");
        viewModel = new WeatherActivityViewModel(getApplicationContext(), cityName);
        setupObserver(viewModel);
        setUpChangeCityButton();

        if( !NetworkUtils.isNetworkAvailable( getApplicationContext()) ) {
            NetworkUtils.buildNetworkAlert(this);
        }
        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(() -> viewModel.update());
    }

    private void setUpChangeCityButton() {
        binding.btnChangeCity.setOnClickListener(view -> {
            SharedPreferences sharedPref = getSharedPreferences("shared preferences", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove("CITY_NAME");
            editor.apply();

            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        if (observable instanceof WeatherActivityViewModel) {
            swipeRefresh.setRefreshing(false);
            Calendar rightNow = Calendar.getInstance();
            WeatherActivityViewModel weatherActivityViewModel = (WeatherActivityViewModel) observable;
            WeatherDetails weatherDetails = weatherActivityViewModel.getWeatherDetails();
            Log.i("TEST", "Test: " + weatherDetails.toString());
            binding.tvCityName.setText(weatherDetails.getName());
            binding.tvTime.setText(rightNow.get(Calendar.HOUR_OF_DAY) + ":" + rightNow.get(Calendar.MINUTE));
            binding.tvTemp.setText(weatherDetails.getMain().getTemp() + "°C");
            binding.tvPressure.setText(weatherDetails.getMain().getPressure() + "hPa");
            binding.tvHumidity.setText(weatherDetails.getMain().getHumidity() + "%");
            binding.tvTempMin.setText(weatherDetails.getMain().getTemp_min() + "°C");
            binding.tvTempMax.setText(weatherDetails.getMain().getTemp_max() + "°C");

            String link = StringUtils.join("https://openweathermap.org/img/wn/",weatherDetails.getWeather().get(0).getIcon(),"@2x.png");
            Picasso.get().setLoggingEnabled(true);
            Picasso.get()
              .load(link)
              .resize(100, 100)
              .into(binding.imageWeather);

        }
    }
}
