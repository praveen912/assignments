/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yodle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author PraveenNK
 */
public class MthFromLast {
       public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //Read the input text file   
        BufferedReader br = new BufferedReader(new FileReader("textfile.txt"));
    try {
        //Read the file line by line
        String line = br.readLine();
        //a is the character that is Mth element from the end of the list
        char a;
        //k is the index value of each line
        int k;
        while (line != null) {
                              
                               //reverse the line
                               String reverse = new StringBuffer(line).reverse().toString();
                               
                               k = Integer.parseInt(Character.toString(line.charAt(line.length()-1)));
               //Check if the index is larger than the list size, if it is larger then ignore that input.
              // If the index is less than the list size, get the Mth element from the end of the list.
                               if(k <= (((line.length()-1)/2)))
                                        {
                                        a = reverse.charAt(2*k);
                                        System.out.println(a);
                                        }
                                line = br.readLine();
                                }
        } 
    catch(Exception e) {
        e.printStackTrace();
        }
    finally {
        br.close();
        }
    }
}
