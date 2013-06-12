/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package travelingsalesman;

import java.util.Scanner;

/**
 *
 * @author PraveenNK
 */

public class TravelingSalesman {
private static int numcities; 

 
public static void main(String[] args) {
System.out.println("Enter the number of cities: ");
Scanner sc = new Scanner(System.in);
numcities =sc.nextInt();
    
    RoadMap mymap = new RoadMap(numcities);
		mymap.Print();
		
		BruteForce alg1 = new BruteForce(mymap);
		alg1.CalcBestRoute();
		alg1.Print();
    }
}
