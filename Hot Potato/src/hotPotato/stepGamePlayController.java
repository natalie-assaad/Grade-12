// Natalie Assaad
// November 22, 2018
// This runs the hot potato game in step mode
// meaning the player shuffles the queue manually

package hotPotato;

// imports
import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import stacksAndQueues.Queue;
import stacksAndQueues.Stack;

public class stepGamePlayController {
	
	@FXML
	private TextArea currentPlayers;
	
	@FXML
	private TextArea playersOut;
	
	@FXML
	private TextArea holdingPotato;
	
	@FXML
	private Button enterButton;
	
	@FXML
	private Button playAgain;
	
	@FXML
	private Button endGame;
	
	@FXML
	private Button start;
		
	// create a new instance of the Queue class (in this case type String)
	Queue<String> playerQueue = new Queue<String>();
	Stack<String> playerOutStack = new Stack<>(); // creates new instance  of stack
	
	static Stage secondaryStage;
	public String game = "OFF";
	
	// loads music
	String song = "wigglesJam.wav";
	Media media = new Media(new File(song).toURI().toString());
	
	// initializes enter button
	public void initialize() {
		enterButton.setDefaultButton(true);
	}
	
	// handles button click events
	public void buttonClickHandler (ActionEvent evt) throws InterruptedException {
		
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();

		if ("Quit".equals(buttonLabel)) {
			closeCurrentWindow(evt);
		} else if ("Step".equals(buttonLabel)) {
			passPotato();
		} else if ("Play Again".equals(buttonLabel)) {
			restart();
		} else if ("End Game".equals(buttonLabel)) {
			playerOutStack.push(currentPlayers.getText());
			endGame();
			closeCurrentWindow(evt);
		} else if ("Start".equals(buttonLabel)) {
			startGame();
		} 
	}

	// method to “get” the existing queue
	public void getQueue (Queue<String> queue) {
		playerQueue = queue;
		for (int i = 0; i < playerQueue.size(); i++) {
			currentPlayers.setText(currentPlayers.getText() + playerQueue.get(i) + "\n");
		}
	}
	
	// starts game by giving a player the potato
	private void startGame() throws InterruptedException { 
		// starts game
		game = "ON";
		
		// plays song
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.play();
		
		// passes potato to first player to get game started
		holdingPotato.setText(playerQueue.peek());
		playerQueue.dequeue(playerQueue.size() - 1);
		refreshQueue();
		start.setDisable(true);
		
		// timer
		new Thread (new Runnable() {
			// finishes game after 12 seconds (length of song)
			public void run() {
				try {
					for (int i=12; i>=0; i--) {
						Thread.sleep(1000);
						if (i==0) {
							playerOut();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// shuffles through playerQueue when user clicks button or enter key
	private void passPotato() {
		// only allows potato to be passed if start button is pressed
		if (game == "ON") {
			playerQueue.enqueue(holdingPotato.getText());
			holdingPotato.setText(playerQueue.peek());
			playerQueue.dequeue(playerQueue.size() - 1);
			refreshQueue();
		}
	}
	
	// removes player who is holding potato from queue
	public void playerOut() {
		playerOutStack.push(holdingPotato.getText());
		playersOut.setText(holdingPotato.getText() + "\n" + playersOut.getText());
		holdingPotato.setText("");
		game = "OFF";
		start.setDisable(false);
		
		// ends game if there is only one player left
		if (playerQueue.isOnePlayer() == true && holdingPotato.getText() != "") {
			playAgain.setVisible(true);
			endGame.setVisible(true);
			start.setDisable(true);
			enterButton.setDisable(true);
		}
	}
	
	// refreshes queue to show which players are currently in the game
	public void refreshQueue () {
		currentPlayers.setText("");
		for (int i = 0; i < playerQueue.size(); i++) {
			currentPlayers.setText(currentPlayers.getText() + playerQueue.get(i) + "\n");
		}
	}

	// resets game
	private void restart() {
		int stackSize = playerOutStack.size();
		for (int i = 0; i < stackSize; i++) {
			playerQueue.enqueue(playerOutStack.peek());
			playerOutStack.pop();
		}
		refreshQueue();
		playersOut.setText("");
		playAgain.setVisible(false);
		endGame.setVisible(false);
		start.setDisable(false);
		enterButton.setDisable(false);
	}
	
	// opens end game screen
	private void endGame() {
		try {
			// loads step game mode fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource("endGame.fxml"));
			Pane stepGameRoot = (Pane)loader.load(); 

			// creates a new scene
			Scene gameScene = new Scene(stepGameRoot,473,338);
	
			// adds css to the new scene		
			gameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// sends stack to other screen
			endGameController endGame = loader.getController();
			endGame.getStack(playerOutStack); // this calls the method to get the playerQueue
	
			//creates new stage to put scene in
			secondaryStage = new Stage();
			secondaryStage.setScene(gameScene);
			secondaryStage.setResizable(false);
			secondaryStage.show();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// closes secondStage
	public void closeWindowButtonClickHandler(ActionEvent evt) {
		// closes stage once button is clicked
		secondaryStage.close();
	}
	
	// closes  current Window
	private void closeCurrentWindow(ActionEvent evt) {
		final Node source = (Node) evt.getSource();
		final Stage stage =(Stage)source.getScene().getWindow();
		stage.close();
	}
}
