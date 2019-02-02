// Natalie Assaad
// November 13, 2018
// This program generates a tic tac toe game

package tictactoe;

import java.io.File;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class TicTacToeController {
	
	@FXML Button b1;
	@FXML Button b2;
	@FXML Button b3;
	@FXML Button b4;
	@FXML Button b5;
	@FXML Button b6;
	@FXML Button b7;
	@FXML Button b8;
	@FXML Button b9;
	
	@FXML TextArea modeID;
	
	@FXML GridPane gameBoard;
	
	// initialize variables / arrays
	private boolean isFirstPlayer = true;
	static Stage secondaryStage;
	public int mode = 0;
	public boolean gameOver = false;
	public int xCounter = 0;
	
	public Button[] btnArr;
	public Button[][] winCombo;
	
	// initialize array
	public void initializeWinCombo() {
		winCombo = new Button [3][3];
		winCombo[0][0] = b1;
		winCombo[0][1] = b2;
		winCombo[0][2] = b3;
		winCombo[1][0] = b4;
		winCombo[1][1] = b5;
		winCombo[1][2] = b6;
		winCombo[2][0] = b7;
		winCombo[2][1] = b8;
		winCombo[2][2] = b9;
	}
	
	// initialize button
	public void initializeButton() {
		btnArr = new Button [9];
		btnArr[0] = b1;
		btnArr[1] = b2;
		btnArr[2] = b3;
		btnArr[3] = b4;
		btnArr[4] = b5;
		btnArr[5] = b6;
		btnArr[6] = b7;
		btnArr[7] = b8;
		btnArr[8] = b9;
	}
	
	@FXML
	public void initialize() {
		initializeButton();
		initializeWinCombo();
	}
	
	// handles menu click event
	public void menuClickHandler(ActionEvent evt) {
		
		MenuItem clickedMenu = (MenuItem) evt.getTarget();
		String menuLabel = clickedMenu.getText();
		
		// if play is quite it will reset the board
		if ("Play".equals(menuLabel)){
			ObservableList<Node> buttons = 
					gameBoard.getChildren();
			
			buttons.forEach(btn -> {
				((Button) btn).setText("");
				 btn.getStyleClass().remove("winning-button");
			});
			
			isFirstPlayer = true;
			gameOver = false;
			xCounter = 0;
			
		}
		
		// player(s) can select the mode they want
		if ("2 Player".equals(menuLabel)) {
			mode = 0;
			modeHandler(mode);
		}
		
		if ("Hard Mode".equals(menuLabel)) {
			mode = 1;
			modeHandler(mode);
		}
		
		if ("Easy Mode".equals(menuLabel)) {
			mode = 2;
			modeHandler(mode);
		}
		 
		// opens secondaryStage and displays information
		if ("How To Play".equals(menuLabel)) {
			openHowToWindow();
		}
		
		if ("About".equals(menuLabel)) {
			openAboutWindow();
		}
		
		// quits game
		if ("Quit".equals(menuLabel)) {
			Main.quitWindow();
		}
	
	}
	
	// displays text informing the player(s) which mode they are playing in
	public void modeHandler (int modeNum) {
		if (mode == 0) {
			modeID.setText("Mode: 2 Player");
		} 
		if (mode == 1) {
			modeID.setText("Mode: Hard");
		}
		if (mode == 2) {
			modeID.setText("Mode: Easy");
		}
	}
	
	// handles button click events based on the mode the player(s) chose
	public void buttonClickHandler (ActionEvent evt) {
		
		Button clickedButton = (Button) evt.getTarget();
		String buttonLabel = clickedButton.getText();
		
		if (gameOver == false) {
			if (mode == 0)  { // 2 Player
				if ("".equals(buttonLabel) && isFirstPlayer) {
					clickedButton.setText("X");
					isFirstPlayer = false;
					xCounter += 1; // keeps track of how many times X played
				} else if ("".equals(buttonLabel) && !isFirstPlayer) {
					clickedButton.setText("O");
					isFirstPlayer = true;
				}
				
			} else if (mode == 1) { // Hard Mode
				
				if ("".equals(buttonLabel)) {
					clickedButton.setText("X");
					xCounter += 1;
				}

				boolean empty = true;
				while (empty != false) {
					// checks for computer win in rows / columns
					for (int i = 0; i < 3; i++) {
						if (winCombo[i][0].getText() == "O" && winCombo[i][2].getText() == "O" && winCombo[i][1].getText() == "") {
							winCombo[i][1].setText("O");
							empty = false;
						}  else if (winCombo[i][1].getText() == "O" && winCombo[i][2].getText() == "O" && winCombo[i][0].getText() == "") {
							winCombo[i][0].setText("O");
							empty = false;
						} else if (winCombo[i][0].getText() == "O" && winCombo[i][1].getText() == "O" && winCombo[i][2].getText() == "") {
							winCombo[i][2].setText("O");
							empty = false;
						} else if (winCombo[0][i].getText() == "O" && winCombo[1][i].getText() == "O" && winCombo[2][i].getText() == "") {
							winCombo[2][i].setText("O");
							empty = false;
	 					} else if (winCombo[1][i].getText() == "O" && winCombo[2][i].getText() == "O" && winCombo[0][i].getText() == "") {
	 						winCombo[0][i].setText("O");
	 						empty = false;
	 					} else if (winCombo[0][i].getText() == "O" && winCombo[2][i].getText() == "O" && winCombo[1][i].getText() == "") {
	 						winCombo[1][i].setText("O");
	 						empty = false;
	 					}
					}
					
					// checks for computer win in diagonals
					if (winCombo[0][0].getText() == "O" && winCombo[2][2].getText() == "O" && b5.getText() == ""
							|| winCombo[0][2].getText() == "O" && winCombo[2][0].getText() == "O" && b5.getText() == "") {
						winCombo[1][1].setText("O");
						empty = false;
					} else if (winCombo[0][2].getText() == "O" && winCombo[1][1].getText() == "O" && winCombo[2][0].getText() == "") {
						winCombo[2][0].setText("O");
						empty = false;
					} else if (winCombo[0][0].getText() == "O" && winCombo[1][1].getText() == "O" && winCombo[2][2].getText() == "") {
						winCombo[2][2].setText("O");
						empty = false;
					} else if (winCombo[2][0].getText() == "O" && winCombo[1][1].getText() == "O" && winCombo[0][2].getText() == "") {
						winCombo[0][2].setText("O");
						empty = false;
					} else if (winCombo[2][2].getText() == "O" && winCombo[1][1].getText() == "O" && winCombo[0][0].getText() == "") {
						winCombo[0][0].setText("O");
						empty = false;
					}
					
					// blocks  user if the try to do 3 in a row / column
					for (int i = 0; i < 3; i++) {
						if (winCombo[i][0].getText() == "X" && winCombo[i][2].getText() == "X" && winCombo[i][1].getText() == "") {
							winCombo[i][1].setText("O");
							empty = false;
						}  else if (winCombo[i][1].getText() == "X" && winCombo[i][2].getText() == "X" && winCombo[i][0].getText() == "") {
							winCombo[i][0].setText("O");
							empty = false;
						} else if (winCombo[i][0].getText() == "X" && winCombo[i][1].getText() == "X" && winCombo[i][2].getText() == "") {
							winCombo[i][2].setText("O");
							empty = false;
						} else if (winCombo[0][i].getText() == "X" && winCombo[1][i].getText() == "X" && winCombo[2][i].getText() == "") {
							winCombo[2][i].setText("O");
							empty = false;
	 					} else if (winCombo[1][i].getText() == "X" && winCombo[2][i].getText() == "X" && winCombo[0][i].getText() == "") {
	 						winCombo[0][i].setText("O");
	 						empty = false;
	 					} else if (winCombo[0][i].getText() == "X" && winCombo[2][i].getText() == "X" && winCombo[1][i].getText() == "") {
	 						winCombo[1][i].setText("O");
	 						empty = false;
	 					}
					}
					
					// blocks user if they try to do 3 diagonally
					if (winCombo[0][0].getText() == "X" && winCombo[2][2].getText() == "X" && b5.getText() == ""
							|| winCombo[0][2].getText() == "X" && winCombo[2][0].getText() == "X" && b5.getText() == "") {
						winCombo[1][1].setText("O");
						empty = false;
					} else if (winCombo[0][2].getText() == "X" && winCombo[1][1].getText() == "X" && winCombo[2][0].getText() == "") {
						winCombo[2][0].setText("O");
						empty = false;
					} else if (winCombo[0][0].getText() == "X" && winCombo[1][1].getText() == "X" && winCombo[2][2].getText() == "") {
						winCombo[2][2].setText("O");
						empty = false;
					} else if (winCombo[2][0].getText() == "X" && winCombo[1][1].getText() == "X" && winCombo[0][2].getText() == "") {
						winCombo[0][2].setText("O");
						empty = false;
					} else if (winCombo[2][2].getText() == "X" && winCombo[1][1].getText() == "X" && winCombo[0][0].getText() == "") {
						winCombo[0][0].setText("O");
						empty = false;
					}
					
					// if X played 5 times and there is no more empty spots
					if (xCounter == 5 && empty) {
						gameOver = true;
						empty = false;
						find3InARow();
						modeID.setText("Cat's Game!");
					}
					
					// if X only played one move or computer still hasn't played a move
					if (xCounter == 1 || empty) {
						while (empty) {
							Button choice = btnArr[(int)(Math.random()*8)+1];
							if (choice.getText() == "") {
								choice.setText("O");
								empty = false;
							}
						}
						
						// if X played 4 times and there is no winner
						if (xCounter == 4) {
							gameOver = true;
							empty = false;
							modeID.setText("Cat's Game!");
						}
					}
				}
			
			} else if (mode == 2) { // Easy Mode
				// sets user clicked button to X
				if ("".equals(buttonLabel)) {
					clickedButton.setText("X");
					xCounter += 1;
				}
				// randomly selects computer button click
				boolean empty = true;
				while (empty) {
					Button choice = btnArr[(int)(Math.random()*8)+1];
					if (choice.getText() == "") {
						choice.setText("O");
						empty = false;
					} else if (xCounter > 4) {
						empty = false;
					}
				}
			}
			find3InARow(); // checks for winner
			if (xCounter > 5) { // if X played more than 5 times and there is no winner
				gameOver = true;
				modeID.setText("Cat's Game!");
			}
		} // gameOver
	} // class  
	
	private boolean find3InARow() {
		
		// checks rows and columns for three X's or O's
		for (int i = 0; i < 3; i++) {
			if (winCombo[i][0].getText() != "" && winCombo[i][0].getText() == winCombo[i][1].getText() 
					&& winCombo[i][1].getText() == winCombo[i][2].getText()) {
				highlightWinningCombo (winCombo[i][0],winCombo[i][1],winCombo[i][2]);
				return true;
			}
			if (winCombo[0][i].getText() != "" && winCombo[0][i].getText() == winCombo[1][i].getText() 
					&& winCombo[1][i].getText() == winCombo[2][i].getText()) {
				highlightWinningCombo (winCombo[0][i],winCombo[1][i],winCombo[2][i]);
				return true;
			}
		}

		//checks first diagonal for three X's or O's in a row
		if (""!=b1.getText() && b1.getText() == b5.getText() 
				&& b5.getText() == b9.getText()) {
			highlightWinningCombo (b1,b5,b9);
			return true;
		}

		//checks second diagonal for three X's or O's in a row
		if (""!=b3.getText() && b3.getText() == b5.getText() 
				&& b5.getText() == b7.getText()) {
			highlightWinningCombo (b3,b5,b7);
			return true;
		}
		// if there is no winner the game continues
		return false; 
	}

	private void highlightWinningCombo(Button first, Button second, Button third) {
		
		// loads winner sound effect
		String tada = "tada.mp3";
		Media media = new Media(new File(tada).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		// plays song
		mediaPlayer.play();
		
		gameOver = true; // ends game and stops user from clicking additional buttons
		
		// adds specific css to winning buttons
		first.getStyleClass().add("winning-button");
		second.getStyleClass().add("winning-button");
		third.getStyleClass().add("winning-button");
		
		// remove specific css from winning buttons
		first.getStyleClass().remove("button:pressed"); // removes button press css
		second.getStyleClass().remove("button:pressed");
		third.getStyleClass().remove("button:pressed");
		first.getStyleClass().remove("button:hover"); // removes button hover css
		second.getStyleClass().remove("button:hover");
		third.getStyleClass().remove("button:hover");
		
		// prints who won
		if (mode == 1 || mode == 2) { // if the game was between a human the the computer
			if (first.getText() == "X") {
				modeID.setText("X wins, good job! "+"\n"+" You have defeated the computer.");
			} else {
				modeID.setText("O wins, better luck next time!" +"\n"+" The computer has bested you.");
			}
		} else if (mode == 0) { // if a game was between two human's
			if (first.getText() == "X") {
				modeID.setText("X wins!" +"\n"+"Good job Player 1.");
			} else {
				modeID.setText("O wins!" +"\n"+ "Excellent work Player 2.");
			}
		}
	}
	
	// opens instructions for game
	private void openHowToWindow() {
		try {
			
			// loads the instructions pop up
			Pane howTo = (Pane)FXMLLoader.load(getClass().getResource("howToPlay.fxml"));
				
			// creates a new scene
			Scene howToScene = new Scene(howTo,600,293);

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
	
	// opens window with information about game creator
	private void openAboutWindow() {
		try {	
			// loads about window pop up
			Pane About = (Pane)FXMLLoader.load(getClass().getResource("about.fxml"));
				
			// creates a new scene
			Scene aboutScene = new Scene(About,381,159);

			// adds css to the new scene		
			aboutScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			//creates new stage to put scene in
			secondaryStage = new Stage();
			secondaryStage.setScene(aboutScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeWindowButtonClickHandler(ActionEvent evt) {
		// closes stage once button is clicked
		secondaryStage.close();
	}
}