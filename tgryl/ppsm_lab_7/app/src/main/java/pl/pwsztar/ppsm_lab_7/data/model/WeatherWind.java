package pl.pwsztar.ppsm_lab_7.data.model;

public class WeatherWind {
  private double speed;
  private int deg;

  public WeatherWind(double speed, int deg) {
    this.speed = speed;
    this.deg = deg;
  }

  public double getSpeed() {
    return speed;
  }

  public int getDeg() {
    return deg;
  }
}
