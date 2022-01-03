package pl.pwsztar.ppsm_lab_7.data.model;

public class WeatherMain {
  private String temp;
  private String feelsLike;
  private String temp_min;
  private String temp_max;
  private int pressure;
  private int humidity;

  public WeatherMain(String temp, String feelsLike, String temp_min, String temp_max, int pressure, int humidity) {
    this.temp = temp;
    this.feelsLike = feelsLike;
    this.temp_min = temp_min;
    this.temp_max = temp_max;
    this.pressure = pressure;
    this.humidity = humidity;
  }

  public String getTemp() {
    return temp;
  }

  public String getFeelsLike() {
    return feelsLike;
  }

  public String getTemp_min() {
    return temp_min;
  }

  public String getTemp_max() {
    return temp_max;
  }

  public int getPressure() {
    return pressure;
  }

  public int getHumidity() {
    return humidity;
  }

  @Override
  public String toString() {
    return "WeatherMain{" +
      "temp=" + temp +
      ", feelsLike=" + feelsLike +
      ", tempMin=" + temp_min +
      ", tempMax=" + temp_max +
      ", pressure=" + pressure +
      ", humidity=" + humidity +
      '}';
  }
}
