package pl.pwsztar.ppsm_lab_7.data.model;

public class WeatherCoords {
  private double lon;
  private double lat;

  public WeatherCoords(double lon, double lat) {
    this.lon = lon;
    this.lat = lat;
  }

  public double getLon() {
    return lon;
  }

  public double getLat() {
    return lat;
  }
}
