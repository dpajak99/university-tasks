package pl.pwsztar;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

public class Coordinate {

  public double[][] station_coords = null;
  public double[][] customer_coords = null;


  public void randomGenerator(double width_x, double height_y, int m, int n) {
    Random rnd = new Random();
    NumberFormat formatter = new DecimalFormat("#0.0");


    station_coords = new double[n][2];
    customer_coords = new double[m][2];


    for (int i = 0; i < n; i++) {
      station_coords[i][0] = (1 + rnd.nextDouble() * width_x);//X coord
      station_coords[i][1] = (1 + rnd.nextDouble() * height_y); //Y coord
      System.out.println("Układ podstawowy " + i + " (" + formatter.format(station_coords[i][0]).replace(",", ".") + ", " + formatter.format(station_coords[i][1]).replace(",", ".") + ")");

    }
    System.out.println();

    for (int i = 0; i < m; i++) {
      customer_coords[i][0] = (1 + rnd.nextDouble() * width_x);//X coord
      customer_coords[i][1] = (1 + rnd.nextDouble() * height_y); //Y coord
      System.out.println("Klient " + i + " (" + formatter.format(customer_coords[i][0]).replace(",", ".") + ", " + formatter.format(customer_coords[i][1]).replace(",", ".") + ")");

    }
  }
}