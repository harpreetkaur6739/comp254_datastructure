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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Demonstration of algorithms for testing element uniqueness.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
class Uniqueness {

  /** Returns true if there are no duplicate elements in the array. */
  public static boolean unique1(int[] data) {
    int n = data.length;
    System.out.println("Array Size in Unique 1: " + n);
    for (int j=0; j < n-1; j++)
      for (int k=j+1; k < n; k++)
        if (data[j] == data[k])
          {
        	System.out.println("Item found:" + data[j]);
        	return false;}                    // found duplicate pair
    return true;                           // if we reach this, elements are unique
  }

  /** Returns true if there are no duplicate elements in the array. */
  public static boolean unique2(int[] data) {
    int n = data.length;
    System.out.println("Array Size in Unique 2: " + n);
    int[] temp = Arrays.copyOf(data, n);   // make copy of data
    Arrays.sort(temp);                     // and sort the copy
    for (int j=0; j < n-1; j++)
      if (temp[j] == temp[j+1])            // check neighboring entries
        return false;                      // found duplicate pair
    return true;                           // if we reach this, elements are unique
  }
  
  
  public static void main(String args[]) {
	 int minSize = 0;
	 int maxSize1 = 1000000;//Maximum Size for Unique1
	 int maxSize2 = 100000000;//Maximum Size for Unique2
	 int timeLimit = 1000; //1 second = 1000 millisecond
	  int nSize = searchHighestSizeUnique1(minSize, maxSize1, timeLimit);
	  int nSize2 = searchHighestSizeUnique2(minSize, maxSize2, timeLimit);
	  System.out.println("Highest value of n for Unique1: " + nSize);
	  System.out.println("Highest value of n for Unique2: " + nSize2);
  }
  
  public static int searchHighestSizeUnique1(int minValue, int maxValue, int timeLimit) {	  
	  
	  int n = (minValue + maxValue)/2;
	  int arr[] = generateArray(n);
	  if(n<maxValue && minValue != maxValue) {	  
		  
		  long startTime, endTime;
		  long elapsed;

			 startTime = System.currentTimeMillis();
			 unique1(arr);
			 endTime = System.currentTimeMillis();
			 elapsed = endTime - startTime;
			 System.out.println("Array Size: n = " + n + "------Start Time: " + startTime + "    End Time: " + endTime + "     Elapsed: " + elapsed);  
			 if(elapsed == timeLimit) {
				  return n;
			  }
			 if(elapsed < timeLimit) {
				  return searchHighestSizeUnique1(n+1, maxValue, timeLimit);
			  }else if(elapsed > timeLimit){
				  return searchHighestSizeUnique1(minValue, n-1, timeLimit);
			  }
	  }	  
	  return n;
  }
  
 public static int searchHighestSizeUnique2(int minValue, int maxValue, int timeLimit) {	  
	  
	  int n = (minValue + maxValue)/2;
	  int arr[] = generateArray(n);
	  if(n<maxValue && minValue != maxValue) {	  
		  
		  long startTime, endTime;
		  long elapsed;

			 startTime = System.currentTimeMillis();
			 unique2(arr);
			 endTime = System.currentTimeMillis();
			 elapsed = endTime - startTime;
			 System.out.println("Array Size: n = " + n + "------Start Time: " + startTime + "    End Time: " + endTime + "     Elapsed: " + elapsed);  
			 if(elapsed == timeLimit) {
				  return n;
			  }
			 if(elapsed < timeLimit) {
				  return searchHighestSizeUnique2(n+1, maxValue, timeLimit);
			  }else if(elapsed > timeLimit){
				  return searchHighestSizeUnique2(minValue, n-1, timeLimit);
			  }
	  }
	  
	  return n;
  }
  
  public static int [] generateArray(int n) {
	 
	  Set<Integer> arr = new HashSet<Integer>();
		 
		  while(arr.size()<n) {//create array
			  Random rand = new Random();
			  arr.add(rand.nextInt());
		  }
		  int arrInt[] = arr.stream().mapToInt(i->i).toArray();
		  
		  return arrInt;
  }

}
