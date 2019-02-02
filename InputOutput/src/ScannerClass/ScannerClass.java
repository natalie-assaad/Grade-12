// Natalie Assaad
// September 7, 2018
// This program uses the scanner class to take input from the user
// (the input radius of a circle) and then the computer calculates
// the area of a circle

package ScannerClass;

import java.util.*;

public class ScannerClass {

	public static void main(String[] args) {
		
		int radius;
		final double PI = 3.14159;
		double area; 	// calculated area of a circle
		
		Scanner myInput = new Scanner(System.in); // Load the Scanner class
		
		System.out.print("Please enter the Radius: ");
		radius = myInput.nextInt();
		
		myInput.close();
		
		area = PI * (radius * radius);
		System.out.println("Area of the circle is: " + area);
		
	}

}
