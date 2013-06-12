/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package queens;

import java.util.Scanner;
/**
 * @author PraveenNK
 */
public class Queens {
    public static int h=0; 
    int[] x;
    // constructor constructs an array x of size N 
    public Queens(int N) {
        x = new int[N];
    }
  
    public boolean validPosition(int r, int c) {
        /**
         * Returns TRUE if a queen can be placed in row r and column c.
         * Otherwise it returns FALSE. x[] is a global array whose first (r-1)
         * values have been set.
         */
        for (int i = 0; i < r; i++) {
            if (x[i] == c || (i - r) == (x[i] - c) ||(i - r) == (c - x[i])) 
            {
                return false;
            }
        }
        return true;
    }
    //Each unique solution is printed
    public void printQueens(int[] x) {
        h = h+1;
        int N = x.length;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (x[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
 
    public void placeNqueens(int r, int n) {
        /**
         * Using backtracking this method prints all possible placements of n
         * queens on an n x n chessboard so that they are non-attacking.
         */
        for (int c = 0; c < n; c++) {
            if (validPosition(r, c)) {
                x[r] = c;
                
                if (r == n - 1) {
                    printQueens(x);
                } else {
                    placeNqueens(r + 1, n);
                }
            }
        }
    }
    // placeNqueens function is invoked with initial row number as 0
    public void callplaceNqueens() {
        placeNqueens(0, x.length);
    }
 
    public static void main(String args[]) {
        // Number of queens is taken as input and is passed to function callplaceNqueens 
        // Total number of unique solutions is printed
        System.out.println("Enter the number of Queens");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        Queens Q = new Queens(i);
        Q.callplaceNqueens();
        System.out.println("Number of Unique solutions: " + h);
    }
    
}
