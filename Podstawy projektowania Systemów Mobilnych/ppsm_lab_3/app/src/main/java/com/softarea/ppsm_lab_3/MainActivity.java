package com.softarea.ppsm_lab_3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.softarea.ppsm_lab_3.dao.JokesFactory;

public class MainActivity extends AppCompatActivity {
  private TextView toolbarTitle;
  private TextView tvJoke;
  private Button buttonGenerateJoke;
  private JokesFactory jokesFactory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
    getSupportActionBar().setCustomView(R.layout.toolbar);

    setUpViews();
    toolbarTitle.setText(getString(R.string.app_name));
    setUpJoke();

    buttonGenerateJoke.setOnClickListener(view -> {
      setUpJoke();
    });

  }

  private void setUpViews() {
    toolbarTitle = findViewById(R.id.toolbar_title);
    tvJoke = findViewById(R.id.tv_joke_text);
    buttonGenerateJoke = findViewById(R.id.button_generate_joke);
    jokesFactory = new JokesFactory(getApplicationContext());
  }

  private void setUpJoke() {
    tvJoke.setText(jokesFactory.getRandomJoke().getText());
  }
}
