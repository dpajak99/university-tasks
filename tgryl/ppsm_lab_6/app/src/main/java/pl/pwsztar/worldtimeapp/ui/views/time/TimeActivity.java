package pl.pwsztar.worldtimeapp.ui.views.time;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pl.pwsztar.worldtimeapp.R;
import pl.pwsztar.worldtimeapp.ui.adapters.LocalizationsAdapter;

public class TimeActivity extends AppCompatActivity {
  private TextView tvMobileTime;
  private RecyclerView rvLocalizations;
  private LocalizationsAdapter localizationsAdapter;
  private Handler handler;
  private TimeController timeController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_time);
    initViews();
    setUpRecyclerView();

    startUpdating();
  }

  private void initViews() {
    tvMobileTime = findViewById(R.id.tv_mobile_time);
    rvLocalizations = findViewById(R.id.rv_localizations);
    timeController = new TimeController();
  }

  private void setUpRecyclerView() {
    localizationsAdapter = new LocalizationsAdapter();
    rvLocalizations.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    rvLocalizations.setAdapter(localizationsAdapter);
    rvLocalizations.setHasFixedSize(true);
  }

  public void startUpdating() {
    handler = new Handler();
    int delay = 1000;

    handler.postDelayed(new Runnable() {
      public void run() {
        update(); // Do your work here
        handler.postDelayed(this, delay);
      }
    }, 0);
  }

  public void update() {
    tvMobileTime.setText(timeController.getCurrentTime().getTimeString());
    localizationsAdapter.update(timeController.getTimeLocalizations());
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    handler.removeCallbacksAndMessages(null);
  }
}
