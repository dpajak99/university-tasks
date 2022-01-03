package pl.pwsztar;

import org.apache.commons.math4.optim.InitialGuess;
import org.apache.commons.math4.optim.MaxEval;
import org.apache.commons.math4.optim.PointValuePair;
import org.apache.commons.math4.optim.SimpleBounds;
import org.apache.commons.math4.optim.nonlinear.scalar.GoalType;
import org.apache.commons.math4.optim.nonlinear.scalar.ObjectiveFunction;
import org.apache.commons.math4.optim.nonlinear.scalar.noderiv.CMAESOptimizer;
import org.apache.commons.rng.core.source32.MersenneTwister;

import java.util.Arrays;
import java.util.Random;

public class Main {

  private static final int POP_SIZE = 10;
  private static final int DIMENSIONS = 100;
  private static final double LOWERBOUND = 0;
  private static final double UPPERBOUND = 500;
  private static final int MAX_FITNESS_EVALUTIONS = 1000;
  private static final int MAX_ITERATIONS = 1000;

  public static void main(String[] args) {
    int NUM_OF_RUNS = 25;
    double[] fValues = new double[NUM_OF_RUNS];

    for (int run = 0; run < NUM_OF_RUNS; run++) {


      Random rng = new Random(run);

      double[] sigmas = new double[DIMENSIONS];
      Arrays.fill(sigmas, rng.nextDouble() * (UPPERBOUND - LOWERBOUND));

      double[] initialValues = new double[DIMENSIONS];
      for (int i = 0; i < initialValues.length; i++) {
        initialValues[i] = rng.nextDouble();
        initialValues[i] *= (UPPERBOUND - LOWERBOUND) + LOWERBOUND;
      }

      double[] lowerBounds = new double[DIMENSIONS];
      Arrays.fill(lowerBounds, LOWERBOUND);

      double[] upperBounds = new double[DIMENSIONS];
      Arrays.fill(upperBounds, UPPERBOUND);

      CMAESOptimizer es = new CMAESOptimizer(MAX_ITERATIONS, 0, true, 0, 0,
        new MersenneTwister(new int[]{run}), false, null);

      PointValuePair result = es.optimize(
        new MaxEval(MAX_FITNESS_EVALUTIONS),
        new CMAESOptimizer.PopulationSize(POP_SIZE),
        new CMAESOptimizer.Sigma(sigmas),
        new InitialGuess(initialValues),
        new SimpleBounds(lowerBounds, upperBounds),
        new ObjectiveFunction(new DistanceFunction()),
        GoalType.MAXIMIZE
      );


      System.out.println("Best points (solution vector):");
      final double[] resultingValues = result.getPoint(); //getPoint ile gercek cozum degerlerini double arraye atiyoruz
      System.out.print("[");
      for (double v : resultingValues) {
        System.out.print(v + ", ");
      }
      System.out.println("]");

      System.out.println("Punkty rozliczeniowe stacji:");
      for(int i=0; i<resultingValues.length; i=i+2){
        System.out.println("("+resultingValues[i]+","+resultingValues[i+1]+")");
      }

      System.out.println();
      fValues[run] = new DistanceFunction().value(resultingValues);
      System.out.println("Run" +run+", CMAES best fitness:" +  fValues[run]);


    }

    //ISTATISTIKSEL VERILER

    double bestF = 0, worstF = 0, totalF = 0, mean, median;;
    for (int run = 0; run < NUM_OF_RUNS; run ++) {
      if (run == 0) {
        bestF = fValues[run];
        worstF = fValues[run];
      } else {
        if (fValues[run] > bestF) {
          bestF = fValues[run];
        }

        if (fValues[run] < worstF) {
          worstF = fValues[run];
        }

      }
      totalF += fValues[run];
    }
    mean = totalF / NUM_OF_RUNS;

    //Standart Sapma
    double sapma = 0;
    for(int i=0; i<NUM_OF_RUNS; i++){
      sapma += (Math.pow((fValues[i] - mean), 2));
    }
    double karelerToplamiOrtalamasi = (sapma) / (fValues.length);
    double standartSapma = (Math.sqrt(karelerToplamiOrtalamasi));

    //Median
    Arrays.sort(fValues);
    if (fValues.length % 2 == 0)
      median = ((double)fValues[fValues.length/2] + (double)fValues[fValues.length/2 - 1])/2;
    else
      median = (double) fValues[fValues.length/2];


    System.out.println("Najlepiej dopasowana: " + bestF);
    System.out.println("Najgorsze dopasowanie: " + worstF);
    System.out.println("Średnia sprawność: " + mean);
    System.out.println("Mediana wartości sprawności: " + median);
    System.out.println("Odchylenie standardowe wartości przydatności: " + standartSapma);
  }
}
