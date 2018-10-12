
import java.util.Random;

/*
 * Copyright 2014, Michael T. Goodrich, Roberto Tamassia, Michael H. Goldwasser
 *
 * Developed for use with the book:
 *
 *    Data Structures and Algorithms in Java, Sixth Edition
 *    Michael T. Goodrich, Roberto Tamassia, and Michael H. Goldwasser
 *    John Wiley & Sons, 2014
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * Demonstration of algorithms for computing the prefix averages of an array.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class PrefixAverage {

  /** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
  public static double[] prefixAverage1(double[] x) {
    int n = x.length;
    double[] a = new double[n];    // filled with zeros by default
    for (int j=0; j < n; j++) {
      double total = 0;            // begin computing x[0] + ... + x[j]
      for (int i=0; i <= j; i++)
        total += x[i];
      a[j] = total / (j+1);        // record the average
    }
    return a;
  }

  /** Returns an array a such that, for all j, a[j] equals the average of x[0], ..., x[j]. */
  public static double[] prefixAverage2(double[] x) {
    int n = x.length;
    double[] a = new double[n];    // filled with zeros by default
    double total = 0;              // compute prefix sum as x[0] + x[1] + ...
    for (int j=0; j < n; j++) {
      total += x[j];               // update prefix sum to include x[j]
      a[j] = total / (j+1);        // compute average based on current sum
    }
    return a;
  }
  
  public static void main(String arg[]) {	 
	
	 System.out.println(" TESTING PREFIXAVERAGE1......");
	 int initial = 100;
	 long elapsedAlgo1[] = testAlgorithm("prefixAverage1", 5, initial);
	 
	 System.out.println(" TESTING PREFIXAVERAGE2......");
	 int initial2 = 100000;
	 long elapsedAlgo2[] = testAlgorithm("prefixAverage2", 5, initial2);
	 
	 LogPlot plot = new LogPlot();
	 plot.drawGraph(5, elapsedAlgo1, "Prefix Average 1");
	 plot.drawGraph(5, elapsedAlgo2, "Prefix Average 2");
  
  }
  
  public static long[] testAlgorithm(String algo, int numTests, int initial) { //numTests = 5; 10^1, 10^2.....10^5	
	 
	  long elapsedArr[] = new long[numTests];
		
	  for(int i=1; i<=numTests; i++) {
		  int arrSize =  (100*i)*initial;
			 
		  double arr[] = new double[arrSize];
		  for(int j=0; j<arrSize; j++) {//create array
			Random rand = new Random();
			arr[j] = rand.nextDouble();
		  }
	
		  long startTime = 0, endTime = 0, elapsed = 0;
		  if(algo.equals("prefixAverage1")) {
			  startTime =  System.currentTimeMillis();
			  prefixAverage1(arr);
			  endTime = System.currentTimeMillis();
			  elapsed = endTime - startTime;
			  elapsedArr[i-1] = elapsed;
		  }else if(algo.equals("prefixAverage2")){
			  startTime =  System.currentTimeMillis();
			  prefixAverage2(arr);
			  endTime = System.currentTimeMillis();
			  elapsed = endTime - startTime;
			  elapsedArr[i-1] = elapsed;
		  }
			
		  System.out.println("ArrSize=" + arrSize + "----StartTime: " + startTime+ "     EndTime: " + endTime + "     Elapsed Time:" + elapsed);
			 
	  }
	  return elapsedArr;
  }

}
