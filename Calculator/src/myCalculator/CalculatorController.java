// Natalie Assaad
// October 26, 2018
// Calculator controller handles button click events

package myCalculator;

// imports
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CalculatorController {
	
	// initializes variables
	public static Float data = 0f;
	public static Float saveData = 0f;
	public static int operation = 0;
	public static int operationM = 0;
	public static int del = 0;
	
	@FXML
	private TextField displayField;
	
	public void buttonClickHandler(ActionEvent evt) {
		
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
				
		// Tells digits apart operations/functions
		switch(buttonLabel) {
		case "0": case "1": case "2": case "3":  case "4": case "5": 
		case "6": case "7": case "8": case "9": case ".":
			processDigit(buttonLabel);
			break;
		case "C":
			displayField.setText(""); // clears display field
			break;
		case "+ -":
			negative(buttonLabel); // converts number in display field to negative
			break;
		case "+":
			processOperation(buttonLabel); // handles + operation
			operation = 1;
			displayField.setText(" ");
			break;
		case "-":
			processOperation(buttonLabel); // handles - operation
			operation = 2;
			displayField.setText(" ");
			break;
		case "X":
			processOperation(buttonLabel); // handles X operation
			operation = 3;
			displayField.setText(" ");
			break;
		case "/":
			processOperation(buttonLabel); // handles / operation
			operation = 4;
			displayField.setText(" ");
			break;
		case "=":
			equals(operation, data, buttonLabel); // handles = operation
			break;
		case "MS": 
			processMS(buttonLabel); // handles MS operation
			break;
		case "M+":
			operationM = 1;
			equalsM(data, buttonLabel); // handles M+ operation
			break;
		case "M-":
			operationM = 2;
			equalsM(data, buttonLabel); // handles M- operation
			break;
		case "MR":
			operationM = 3;
			equalsM(data, buttonLabel); // handles MR operation
			break;
		case "Del":
			deleteDigit(displayField.getText());	
			break;
		case "^2": // handles square
			CalculatorController.data = Float.parseFloat(displayField.getText());
			operation = 5;
			equals(operation, data, buttonLabel);
			break;
		case "%": // converts value in displayField to percentage
			CalculatorController.data = Float.parseFloat(displayField.getText());
			operation = 6;
			equals(operation, data, buttonLabel);
			break;
		case "!": // find factorial of value in textField
			CalculatorController.data = Float.parseFloat(displayField.getText());
			operation = 7;
			equals(operation, data, buttonLabel);
			break;
		}
			
	} // buttonClickHandler

	// processes integer chosen by user
	public void processDigit (String buttonLabel) {
		displayField.setText(displayField.getText() + buttonLabel);
	}
	
	// deletes last value from display
	public void deleteDigit (String str) {
		str = str.substring(0, str.length() - 1);
		displayField.setText(str);
	} 
	
	// processes operation chosen by user
	public void processOperation (String buttonLabel) {
		CalculatorController.data = Float.parseFloat(displayField.getText()); //sets data to the value in the text field
		displayField.setText(" ");	
	}
	
	// converts integer to a negative/positive integer
	private void negative (String buttonLabel) {
		Float temp = Float.parseFloat(displayField.getText());
		Float convert = temp * -1;
		displayField.setText(String.valueOf(convert));
	}
	
	// does operation specified by user
	private void equals (int operation, Float data, String buttonLabel) {
		Float ans = 0f;
		if (operation < 5) {
			if (displayField.getText().length() > 0) { // prevents error if operation is clicked before any input
				Float secondOperand = Float.parseFloat(displayField.getText());
				switch (CalculatorController.operation) {
					case 1: // Addition
						ans = data + secondOperand;
						break;
					case 2: // Subtraction
						ans = data - secondOperand;
						break;
					case 3: // Multiplication
						ans = data * secondOperand;
						break;
					case 4: // Division
						ans = data / secondOperand;
						break;
					case 5: // Square
						ans = data * data;
					default:
						ans = Float.parseFloat(displayField.getText());
					}
				}
			} else {
				switch (CalculatorController.operation) {
					case 5: // Square
						ans = data * data;
						break;
					case 6: // Find Percentage
						ans = data / 100;
						break;
					case 7: // Find Factorial
					    ans = 1f;
					    for (int i = 1; i <= data; i++) {
					    	ans = ans * i;
					    }
				}
			}
				
			displayField.setText(String.valueOf(ans));
		} // equals
	
	// saves value on displayField
	private void processMS (String buttonLabel) { // MS
		try { // attempts to save data from displayField
			CalculatorController.saveData = Float.parseFloat(displayField.getText());
			displayField.setText(String.valueOf(CalculatorController.saveData));
		} catch (RuntimeException e){ // if there is no data in the display field it catches the error and saves 0.0
			CalculatorController.saveData = 0.0f;
			displayField.setText(String.valueOf(CalculatorController.saveData));
		}
	} // processMS
	
	// processes other M operations
	private void equalsM (Float data, String buttonLabel) {
		Float ans = 0f;
		if (displayField.getText().length() > 0 && operationM != 3) { // prevent error if any M button is clicked before there is any input
			switch (operationM) {
				case 1: // M+
					ans = CalculatorController.saveData + Float.parseFloat(displayField.getText());
					displayField.setText(String.valueOf(ans));
					processMS(buttonLabel);
					break;
				case 2: // M-
					ans = CalculatorController.saveData - Float.parseFloat(displayField.getText());
					displayField.setText(String.valueOf(ans));
					processMS(buttonLabel);
					break;
			}
		} else if (operationM == 3){ // MR
			CalculatorController.data = CalculatorController.saveData;
			displayField.setText(String.valueOf(CalculatorController.data));
			}
	
	} // equalsM
	
} // CalculatorController
