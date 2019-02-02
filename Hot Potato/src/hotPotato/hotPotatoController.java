// Natalie Assaad
// November 22, 2018
// This is the introductory screen to the hot potato game

package hotPotato;

// imports
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import hotPotato.Main;

public class hotPotatoController {
		
	static Stage secondaryStage;
	
	public void buttonClickHandler (ActionEvent evt) {
		
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		
		if ("Instructions".equals(buttonLabel)) {
			openInstructWindow();
		} else if ("Quit".equals(buttonLabel)) {
			Main.quitWindow();
		} else if ("PLAY".equals(buttonLabel)) {
			closeCurrentWindow(evt);
			openPlayerDataWindow();
		} 
	}
	
	// opens instructions for game
	private void openInstructWindow() {
		try {
				
			// loads the instructions pop up
			Pane howTo = (Pane)FXMLLoader.load(getClass().getResource("Instructions.fxml"));
					
			// creates a new scene
			Scene howToScene = new Scene(howTo,321,238);

			// adds css to the new scene		
			howToScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//creates new stage to put scene in
			secondaryStage = new Stage();
			secondaryStage.setScene(howToScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// opens playerDataWindow and closes introductory screen window
	private void openPlayerDataWindow() {
		try {
								
			// loads playerData screen
			Pane playerData = (Pane)FXMLLoader.load(getClass().getResource("playerData.fxml"));

			// creates a new scene
			Scene howToScene = new Scene(playerData,435,317);

			// adds css to the new scene		
			howToScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//creates new stage to put scene in
			secondaryStage = new Stage();
			secondaryStage.setScene(howToScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
						
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// closes secondStage
	public void closeWindowButtonClickHandler(ActionEvent evt) {
		// closes stage once button is clicked
		secondaryStage.close();
	}
	
	// closes current window
	private void closeCurrentWindow(ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		final Stage stage =(Stage)source.getScene().getWindow();
		stage.close();
	}

}
