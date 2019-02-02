// Natalie Assaad
// October 26, 2018
// This program uses JavaFX to create a functioning calculator

package myCalculator;
	
import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		// loads music
		String song = "song.mp3";
		Media media = new Media(new File(song).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		// plays song
		mediaPlayer.play();
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Calculator.fxml"));
			Scene scene = new Scene(root,300,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
} 
