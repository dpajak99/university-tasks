package pl.pwsztar.worldtimeapp.ui.views.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import pl.pwsztar.worldtimeapp.R;
import pl.pwsztar.worldtimeapp.ui.views.time.TimeActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button buttonNavigateToTimeActivity = findViewById(R.id.button_navigate_to_time_activity);
    buttonNavigateToTimeActivity.setOnClickListener((view -> {
      this.startTimeActivity();
    }));
  }

  private void startTimeActivity() {
    Intent intent = new Intent(MainActivity.this, TimeActivity.class);
    MainActivity.this.startActivity(intent);
  }
}
