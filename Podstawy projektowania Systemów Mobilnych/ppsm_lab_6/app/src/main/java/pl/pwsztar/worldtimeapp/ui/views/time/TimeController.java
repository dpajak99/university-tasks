package pl.pwsztar.worldtimeapp.ui.views.time;

import java.util.Calendar;
import java.util.List;

import pl.pwsztar.worldtimeapp.dao.TimeLocalizationsDAO;
import pl.pwsztar.worldtimeapp.model.TimeHolder;
import pl.pwsztar.worldtimeapp.model.TimeLocalization;

public class TimeController {
  private int currentTimeZone;
  private List<TimeLocalization> baseTimeLocalizations;

  public TimeController() {
    currentTimeZone = getTimeZoneFromMiliseconds(Calendar.getInstance().get(Calendar.ZONE_OFFSET));
    baseTimeLocalizations = TimeLocalizationsDAO.getTimeLocalizations();
  }

  public TimeHolder getCurrentTime() {
    int hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    int min = Calendar.getInstance().get(Calendar.MINUTE);
    int sec = Calendar.getInstance().get(Calendar.SECOND);

    return new TimeHolder(hours, min, sec);
  }

  public int getTimeZoneFromMiliseconds(int timeZoneInMilsec) {
    return timeZoneInMilsec / 3600 / 1000;
  }

  public List<TimeLocalization> getTimeLocalizations() {
    TimeHolder currentTime = getCurrentTime();
    for( TimeLocalization timeLocalization : baseTimeLocalizations ) {
      int hours = currentTime.getHours() - currentTimeZone + timeLocalization.getTimeZoneValue();
      if( hours < 0 ) {
        hours = 24 + hours;
      } else if( hours > 23 ) {
        hours = hours - 24;
      }
      timeLocalization.setCountryTime(new TimeHolder(
        hours,
        currentTime.getMin(),
        currentTime.getSec()
      ));
    }
    return baseTimeLocalizations;
  }
}
