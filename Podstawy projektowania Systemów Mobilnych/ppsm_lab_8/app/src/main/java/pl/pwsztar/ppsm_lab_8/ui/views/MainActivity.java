package pl.pwsztar.ppsm_lab_8.ui.views;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import pl.pwsztar.ppsm_lab_8.R;
import pl.pwsztar.ppsm_lab_8.ui.databinding.MainDataBinding;
import pl.pwsztar.ppsm_lab_8.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

  private MainDataBinding binding;
  private String selectedCityName;

  @RequiresApi(api = Build.VERSION_CODES.R)
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    selectedCityName = getSavedCity();

    if( !NetworkUtils.isNetworkAvailable( getApplicationContext()) ) {
      NetworkUtils.buildNetworkAlert(this);
    }
    if (selectedCityName != null) {
      openWeatherActivity();
    } else {
      setContentView(R.layout.activity_main);
      ViewGroup view = (ViewGroup) getWindow().getDecorView();
      binding = new MainDataBinding(view);

      setUpSearchButton();
      setUpMainButton();
    }
  }

  private void setUpSearchButton() {
    binding.buttonSearch.setOnClickListener(view -> {
      Intent intent = new Intent(MainActivity.this, SearchActivity.class);
      startActivityForResult(intent, 0);
    });
  }

  private String getSavedCity() {
    SharedPreferences sharedPref = getSharedPreferences("shared preferences", MODE_PRIVATE);
    return sharedPref.getString("CITY_NAME", null);
  }

  private void setUpMainButton() {
    binding.btnSendCity.setOnClickListener(view -> {
      if (selectedCityName == null) {
        Toast.makeText(getApplicationContext(), "Najpierw musisz podać miasto", Toast.LENGTH_SHORT).show();
      } else {
        openWeatherActivity();
      }
    });
  }

  private void openWeatherActivity() {
    SharedPreferences sharedPref = getSharedPreferences("shared preferences", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putString("CITY_NAME", selectedCityName);
    editor.apply();

    Intent intent = new Intent(this, WeatherActivity.class);
    intent.putExtra("CITY_NAME", selectedCityName);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(intent);
    finish();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == 0) {
      if (resultCode == Activity.RESULT_OK) {
        selectedCityName = data.getStringExtra("CITY_NAME");
        binding.buttonSearch.setText(selectedCityName);
      }
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    selectedCityName = null;
  }
}
