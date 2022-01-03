package pl.pwsztar.ppsm_lab_7.ui.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import pl.pwsztar.ppsm_lab_7.R;
import pl.pwsztar.ppsm_lab_7.ui.databinding.MainDataBinding;

public class MainActivity extends AppCompatActivity {

  private MainDataBinding binding;
  private String selectedCityName;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    selectedCityName = getSavedCity();
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
