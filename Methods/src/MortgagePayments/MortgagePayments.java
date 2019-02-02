// Natalie Assaad
// September 19, 2018
// This program calculates the monthly mortgage payments as well as the interest
// based on the principal, term, and monthly interest that the user inputs. 

package MortgagePayments;

import java.util.*;

public class MortgagePayments {

	public static void main(String[] args) {
		
		// Initializes variables
		double monthlyIntrest = 0, principal = 0, term = 0, monthlyIntrestInput = 0, 
				mortgagePayment = 0, intrestTotal = 0, payTotal = 0, termMonth = 0;
		
		// Load the Scanner class
		Scanner myInput = new Scanner(System.in);
		
		// Instruct user how to input values
		System.out.println("Please input the values below without any symbols (eg. $, %):" + "\n");
		
		// Takes in user input
		System.out.println("Please enter the principal: ");
		principal = myInput.nextInt();
		
		System.out.println("Please enter the monthly intrest as a percent: ");
		monthlyIntrestInput = myInput.nextInt();
		monthlyIntrest = (monthlyIntrestInput / 12) / 100;
		
		System.out.println("Please enter the loan's term in years: ");
		term = myInput.nextInt();
		termMonth = term * 12;
		
		
		myInput.close(); // Closes input
		
		// Links function value to a variable
		payTotal = mortgageCalc (monthlyIntrest, mortgagePayment, principal, termMonth); // calls functions with appropriate parameters
		intrestTotal = intrestCalc (intrestTotal, payTotal, principal, termMonth);
		
		// Prints calculated data for user
		System.out.printf("\n" + "If you have a $" + "%.0f" + " mortgage at " + "%.0f" +  "%%, over " 
				+ "%.0f" + " years you will pay $" + "%.2f" + " per month" + "\n" + "and it will cost "
				+ "you $" + "%.2f" + " in interest over the lifetime of your mortgage.", principal, 
				monthlyIntrestInput, term, payTotal, intrestTotal);

	} // Main method
	
	// Calculates monthly mortgage fee
	public static double mortgageCalc ( double monthlyIntrest, double mortgagePayment,
			double principal, double termMonth) {

		mortgagePayment = (monthlyIntrest*principal) / (1 - Math.pow(1 + monthlyIntrest, -termMonth));
		
		return mortgagePayment; // Find monthly mortgage payment value, returns value to main method
		
		} // Mortgage calculation function
	
	// Calculates interest cost over lifetime
	public static double intrestCalc (double intrestTotal, double payTotal, double principal, 
			double termMonth) {
				
		intrestTotal = (payTotal*termMonth) - principal;
		
		return intrestTotal; // Find accumulated interest value over term, returns value to main method
		
		} // Interest calculation function
	
} // Mortgage Payments class
