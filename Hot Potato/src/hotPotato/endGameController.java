// Natalie Assaad
// November 22, 2018
// This code displays the players standings

package hotPotato;

//imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stacksAndQueues.*;

public class endGameController {
	
	Stage secondaryStage;
	Stack<String> standingsStack = new Stack<>(); // creates new instance  of stack
	
	@FXML
	private TextArea playerStandings;
	
	public void buttonClickHandler (ActionEvent evt) {
		
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		
		if ("Play Again".equals(buttonLabel)) {
			openPlayerDataWindow();
			closeCurrentWindow(evt);
		} else if ("Quit".equals(buttonLabel)) {
			closeCurrentWindow(evt);
		} 
	}
	
	// method to “get” the existing stack
	public void getStack (Stack<String> stack) {
		standingsStack = stack;
		for (int i = 0; i < standingsStack.size(); i++) {
			playerStandings.setText(standingsStack.get(i) + "\n" + playerStandings.getText());
		}
	}

	// opens playerData window if person wants to play again
	private void openPlayerDataWindow() {
		try {				
			// loads fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource("playerData.fxml"));
			Pane playerDataRoot = (Pane)loader.load(); 
			
			// creates a new scene
			Scene gameScene = new Scene(playerDataRoot,473,338);

			// adds css to the new scene		
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//creates new stage to put scene in
			secondaryStage = new Stage();
			secondaryStage.setScene(gameScene);
			secondaryStage.setResizable(false);
			secondaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// closes secondStage
	public void closeWindowButtonClickHandler(ActionEvent evt) {
		// closes stage once button is clicked
		secondaryStage.close();
	}
	
	// closes window
	private void closeCurrentWindow(ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		final Stage stage =(Stage)source.getScene().getWindow();
		stage.close();
	}

}
