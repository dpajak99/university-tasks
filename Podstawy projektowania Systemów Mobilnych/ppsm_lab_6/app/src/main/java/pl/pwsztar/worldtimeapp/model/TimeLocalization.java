package pl.pwsztar.worldtimeapp.model;

public class TimeLocalization {
  private int flagImage;
  private String cityName;
  private TimeHolder countryTime;
  private int zone;

  public TimeLocalization(int flagImage, String cityName, TimeHolder countryTime, int zone) {
    this.flagImage = flagImage;
    this.cityName = cityName;
    this.countryTime = countryTime;
    this.zone = zone;
  }

  public void setCountryTime(TimeHolder countryTime) {
    this.countryTime = countryTime;
  }

  public int getFlagImage() {
    return flagImage;
  }

  public String getCityName() {
    return cityName;
  }

  public TimeHolder getCountryTime() {
    return countryTime;
  }

  public int getTimeZoneValue() {
    return zone;
  }

  @Override
  public String toString() {
    return "TimeLocalization{" +
      "flagImage=" + flagImage +
      ", cityName='" + cityName + '\'' +
      ", countryTime=" + countryTime +
      ", zone=" + zone +
      '}';
  }
}
