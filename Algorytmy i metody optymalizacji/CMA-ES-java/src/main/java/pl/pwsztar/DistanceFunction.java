package pl.pwsztar;

import org.apache.commons.math4.analysis.MultivariateFunction;

public class DistanceFunction implements MultivariateFunction {

  double range=35;

  double distance;
  int total=0;
  Coordinate c2 = new Coordinate();


  @Override
  public double value(double[] point) {
    c2.randomGenerator(500, 500, 300, 0);
    for (int i = 0; i < c2.customer_coords.length; i++){
      for(int j=0; j<point.length; j=j+2){
        double x1 = (double) c2.customer_coords[i][0];
        double y1 = (double) c2.customer_coords[i][1];
        double x2 = (double) point[j];
        double y2 = (double) point[j+1];
        distance = Math.sqrt(Math.pow(x2 - x1, 2)+Math.pow(y2 - y1, 2));

        if (distance <= range) {
          total++;
          break;
        }
      }
    }
    return total;

  }
}