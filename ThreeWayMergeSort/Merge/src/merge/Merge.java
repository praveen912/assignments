/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package merge;

import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author PraveenNK
 */
public class Merge {    
    
    public static long start,end;
    private static void merge(Comparable[] a, Comparable[] b, int lo, int mid, int hi)
    {
        int i, j, k;
        for (i = mid + 1; i > lo; i--) {
            b[i-1] = a[i-1];
        }

        for (j = mid; j < hi; j++) {
            b[hi+mid-j] = a[j+1];
        }

        for (k = lo; k <= hi; k++) {
            a[k] = lt(b[j], b[i]) ? b[j--] : b[i++];
        }
    }
    //compare LeftHandSide and RightHandSide
 private static boolean lt(Comparable lhs, Comparable rhs) 
    {
        return lhs.compareTo(rhs) < 0;
    }

    public static void threeWayMergeSort(Comparable[] a)
    {
        start = System.currentTimeMillis();
        Comparable[] b = a.clone();
        threeWayMergeSort(a, b, 0, a.length - 1);
    }

    private static void threeWayMergeSort(Comparable[] a, Comparable[] b, int lo, int hi)
    {
        
        if (hi <= lo) return;
        int mid1 = lo + (hi - lo) / 3;
        int mid2 = mid1 + (hi - lo) / 3;
        threeWayMergeSort(a, b, lo, mid1);
        threeWayMergeSort(a, b, mid1 + 1, mid2);
        threeWayMergeSort(a, b, mid2 + 1, hi);
        merge(a, b, lo, mid1, mid2);
        merge(a, b, lo, mid2, hi);
    }

    public static void main(String[] args)
    {
     System.out.println("No of elements in array");
     Scanner sc = new Scanner(System.in);
     int n = sc.nextInt();
       // int n       = 20;
        int max     = 100;
        Integer[] a = new Integer[n];
        Random gen  = new Random();

        for (int i = 0; i < n; i++) {
            a[i] = gen.nextInt(max);
        }

        threeWayMergeSort(a);

        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
        end = System.currentTimeMillis();
        System.out.println("Time taken: "+(end-start)+" ms");
    }
}
