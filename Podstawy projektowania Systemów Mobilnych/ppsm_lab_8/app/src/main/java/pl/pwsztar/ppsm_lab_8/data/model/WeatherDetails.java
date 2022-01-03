package pl.pwsztar.ppsm_lab_8.data.model;

import java.util.List;

public class WeatherDetails {
  private WeatherCoords coords;
  private List<WeatherWeather> weather;
  private String base;
  private WeatherMain main;
  private int visibility;
  private WeatherWind wind;
  private WeatherClouds clouds;
  private int dt;
  private WeatherSys system;
  private int timezone;
  private int id;
  private String name;
  private int cod;

  public WeatherDetails(WeatherCoords coords, List<WeatherWeather> weather, String base, WeatherMain main, int visibility, WeatherWind wind, WeatherClouds clouds, int dt, WeatherSys system, int timezone, int id, String name, int cod) {
    this.coords = coords;
    this.weather = weather;
    this.base = base;
    this.main = main;
    this.visibility = visibility;
    this.wind = wind;
    this.clouds = clouds;
    this.dt = dt;
    this.system = system;
    this.timezone = timezone;
    this.id = id;
    this.name = name;
    this.cod = cod;
  }

  public WeatherCoords getCoords() {
    return coords;
  }

  public List<WeatherWeather> getWeather() {
    return weather;
  }

  public String getBase() {
    return base;
  }

  public WeatherMain getMain() {
    return main;
  }

  public int getVisibility() {
    return visibility;
  }

  public WeatherWind getWind() {
    return wind;
  }

  public WeatherClouds getClouds() {
    return clouds;
  }

  public int getDt() {
    return dt;
  }

  public WeatherSys getSystem() {
    return system;
  }

  public int getTimezone() {
    return timezone;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getCod() {
    return cod;
  }

  @Override
  public String toString() {
    return "WeatherDetails{" +
      "coords=" + coords +
      ", weather=" + weather +
      ", base='" + base + '\'' +
      ", main=" + main +
      ", visibility=" + visibility +
      ", wind=" + wind +
      ", clouds=" + clouds +
      ", dt=" + dt +
      ", system=" + system +
      ", timezone=" + timezone +
      ", id=" + id +
      ", name='" + name + '\'' +
      ", cod=" + cod +
      '}';
  }
}
