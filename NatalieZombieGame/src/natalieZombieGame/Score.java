package natalieZombieGame;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Score {
	@FXML
	GraphicsContext gc;
	
	@FXML
    Canvas gameCanvas;
	
    public Score(GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
    }
	
	public void displayScore(Player player){
		try {
		   // String scoreStr = "Score: "+ String.valueOf(zombie.atePlayer);
		    gc.setFont(Font.font("ComicSansMS",FontWeight.BOLD,36));
		    gc.setFill(Color.RED);
		   // gc.fillText(scoreStr, 20, 50);
		        
		    String scoreStr = "Score: " + String.valueOf(player.getScore());
		    gc.setFill(Color.RED);
		    gc.fillText(scoreStr, 20, 50);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayTime(long time){
		try {
		   // String scoreStr = "Score: "+ String.valueOf(zombie.atePlayer);
		    gc.setFont(Font.font("ComicSansMS",FontWeight.BOLD,85));
		    gc.setFill(Color.RED);
		   // gc.fillText(scoreStr, 20, 50);
		        
		    String scoreStr = "TOTAL TIME: " + String.valueOf(time) + " SECONDS";
		    gc.setFill(Color.RED);
		    gc.fillText(scoreStr, 50, 720 / 2);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
