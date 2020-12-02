package pl.pwsztar.ppsm_lab_7.data.model;

public class WeatherWeather {
  private int id;
  private String main;
  private String description;
  private String icon;

  public WeatherWeather(int id, String main, String description, String icon) {
    this.id = id;
    this.main = main;
    this.description = description;
    this.icon = icon;
  }

  public int getId() {
    return id;
  }

  public String getMain() {
    return main;
  }

  public String getDescription() {
    return description;
  }

  public String getIcon() {
    return icon;
  }
}
