// Natalie Assaad
// September 7, 2018

package NumericInput;

import java.io.*;

public class NumericInput {
	
	public static void main (String[] args) throws IOException {
	
	BufferedReader myInput = new BufferedReader (new InputStreamReader(System.in));
	
	String stringNum;
	int convertToNum, square; // declare two int variables
	
	System.out.println("Please enter the length of the side: ");
	stringNum = myInput.readLine ();
	
	convertToNum = Integer.parseInt(stringNum); // convert input to Integer
	square = convertToNum * convertToNum;		// compute the square
	
	System.out.println("The square area of " + stringNum + " is " + square);
	
 	} 
}