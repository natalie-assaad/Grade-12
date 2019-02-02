// Natalie Assaad
// September 7, 2018
// User inputs name and it gets outputted

package InputOutput;

import java.io.*;

import java.io.BufferedReader;

public class CurrentAge {

	public static void main (String[] args) throws IOException { // to catch errors
		
		// load the BufferedReader and InputStreamReader classes
		BufferedReader myInput = new BufferedReader (new InputStreamReader (System.in));
		
		System.out.print("Please enter your name: "); // prompt
		String myName = myInput.readLine();           // output line
		
		System.out.println("Hello " + myName + ", how are you?");
	} // main method
	
} // Greetings class