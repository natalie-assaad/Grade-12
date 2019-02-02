// Natalie Assaad
// November 22, 2018
// This code gets the names of the players and then allows the user to choose a mode

package hotPotato;

//imports
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import stacksAndQueues.*;

public class playerDataController {
	
	static Stage secondaryStage;
	// queue player names are added to
	Queue<String> queue = new Queue<String>(); // creates instance of queue
	
	@FXML
	private TextField textField;
	
	@FXML
	private TextArea textArea;
	
	@FXML 
	private Text error;
	
	@FXML
	private Button enterButton;
	
	@FXML
	private FXMLLoader loader;
	
	@FXML
	private Button removePlayer;
	
	// makes error message invisible upon initialize
	public void initialize() {
		error.setVisible(false); // allows user to click enter on keyboard to enter name
		enterButton.setDefaultButton(true);
		// when user hovers over Remove Player button it will display this text to instruct them of the requirements
		Tooltip tooltipInstruct = new Tooltip("Enter Index You Want To Remove In Text Field");
		removePlayer.setTooltip(tooltipInstruct);
		removePlayer.setCancelButton(true); // allows user to click esc on keyboard to remove name
	}
	
	public void buttonClickHandler (ActionEvent evt) {
		
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		
		if ("Add Player".equals(buttonLabel)) {
			addPlayer();
		} else if ("Remove Player".equals(buttonLabel)) {
			removePlayer();
		} else if ("Quit".equals(buttonLabel)) {
			closeCurrentWindow(evt);
		} else if ("Step".equals(buttonLabel)) {
			if (queue.isEnoughPlayers() != false) { // only allows user to continue if player requirements are met
				closeCurrentWindow(evt);
			}
			openStepGameWindow();
		} else if ("Automatic".equals(buttonLabel)) { // only allows user to continue if player requirements are met
			if (queue.isEnoughPlayers() != false) {
				closeCurrentWindow(evt);
			}
			openAutoGameWindow();
		}
	}
	
	// adds player name to queue
	private void addPlayer() {
		try {
			error.setVisible(false);
			queue.enqueue(textField.getText());	// enqueues player name
			textArea.setText(textField.getText() + "\n" + textArea.getText()); // prints name to textArea
			textField.setText(""); // sets textField blank
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// removes last entered name from queue
	private void removePlayer() {
		try {
			int nameRemoved = Integer.parseInt(textField.getText());
			queue.dequeue(nameRemoved); // dequeues name
			textField.setText("");	// sets textField blank
			refreshQueue();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// displays new queue
	public void refreshQueue () {
		textArea.setText("");
		for (int i = 0; i < queue.size(); i++) {
			textArea.setText(textArea.getText() + queue.get(i) + "\n");
		}
	}
	
	// opens game window when user chooses the mode
	// they choose to play in: either step or automatic
	private void openStepGameWindow() {
		try {
			if (queue.isEnoughPlayers() == true) {
				
				// loads step game mode fxml
				FXMLLoader loader = new FXMLLoader(getClass().getResource("stepGamePlay.fxml"));
				Pane stepGameRoot = (Pane)loader.load(); 
				
				// loads step game mode fxml
				//Pane game = (Pane)FXMLLoader.load(getClass().getResource("stepGamePlay.fxml"));
							
				// creates a new scene
				Scene gameScene = new Scene(stepGameRoot,473,338);
	
				// adds css to the new scene		
				gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				// sends queue to other screen
				stepGamePlayController stepGamePage = loader.getController();
				stepGamePage.getQueue(queue); // this calls the method to get the playerQueue
	
				//creates new stage to put scene in
				secondaryStage = new Stage();
				secondaryStage.setScene(gameScene);
				secondaryStage.setResizable(false);
				secondaryStage.show();
			} else {
				error.setVisible(true); // makes error message visible if players !> 1
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void openAutoGameWindow() {
		try {
			if (queue.isEnoughPlayers() == true) {
				
				// loads step game mode fxml
				FXMLLoader loader = new FXMLLoader(getClass().getResource("autoGamePlay.fxml"));
				Pane autoGameRoot = (Pane)loader.load(); 
				
				// creates a new scene
				Scene gameScene = new Scene(autoGameRoot,473,338);
	
				// adds css to the new scene		
				gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				
				// sends queue to other screen
				autoGamePlayController autoGamePage = loader.getController();
				autoGamePage.getQueue(queue); // this calls the method to get the playerQueue
	
				//creates new stage to put scene in
				secondaryStage = new Stage();
				secondaryStage.setScene(gameScene);
				secondaryStage.setResizable(false);
				secondaryStage.show();
			} else {
				error.setVisible(true); // makes error message visible if players !> 1
			}
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
