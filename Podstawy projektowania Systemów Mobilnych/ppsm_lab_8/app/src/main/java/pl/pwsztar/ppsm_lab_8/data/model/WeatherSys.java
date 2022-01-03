package pl.pwsztar.ppsm_lab_8.data.model;

public class WeatherSys {
  private int type;
  private int id;
  private String country;
  private int sunrise;
  private int sunset;

  public WeatherSys(int type, int id, String country, int sunrise, int sunset) {
    this.type = type;
    this.id = id;
    this.country = country;
    this.sunrise = sunrise;
    this.sunset = sunset;
  }

  public int getType() {
    return type;
  }

  public int getId() {
    return id;
  }

  public String getCountry() {
    return country;
  }

  public int getSunrise() {
    return sunrise;
  }

  public int getSunset() {
    return sunset;
  }
}
