package pl.pwsztar.worldtimeapp.model;

import pl.pwsztar.worldtimeapp.utils.StringUtils;

public class TimeHolder {
  private int hours;
  private int min;
  private int sec;

  public TimeHolder(int hours, int min, int sec) {
    this.hours = hours;
    this.min = min;
    this.sec = sec;
  }

  public int getHours() {
    return hours;
  }

  public int getMin() {
    return min;
  }

  public int getSec() {
    return sec;
  }

  public String getTimeString() {
    return StringUtils.join(
      StringUtils.fillZerosOnStart(getHours(),2),
      ":",
      StringUtils.fillZerosOnStart(getMin(), 2),
      ":",
      StringUtils.fillZerosOnStart(getSec(), 2));
  }

}
