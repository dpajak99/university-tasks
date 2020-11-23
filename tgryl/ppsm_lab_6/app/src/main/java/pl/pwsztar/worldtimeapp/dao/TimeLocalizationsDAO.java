package pl.pwsztar.worldtimeapp.dao;

import java.util.ArrayList;
import java.util.List;

import pl.pwsztar.worldtimeapp.R;
import pl.pwsztar.worldtimeapp.model.TimeHolder;
import pl.pwsztar.worldtimeapp.model.TimeLocalization;

public class TimeLocalizationsDAO {
  public static List<TimeLocalization> getTimeLocalizations() {
    List<TimeLocalization> timeLocalizations = new ArrayList<>();
    timeLocalizations.add(new TimeLocalization(R.drawable.flag_usa, "New York", new TimeHolder(0,0,0), -5));
    timeLocalizations.add(new TimeLocalization(R.drawable.flag_england, "London", new TimeHolder(0,0,0), 0));
    timeLocalizations.add(new TimeLocalization(R.drawable.flag_japan, "Tokyo", new TimeHolder(0,0,0), 8));

    return timeLocalizations;
  }
}
