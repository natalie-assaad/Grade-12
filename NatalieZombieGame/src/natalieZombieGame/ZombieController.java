package natalieZombieGame;

import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ZombieController {
	
	@FXML
	Canvas gameCanvas;
	
	@FXML
	GraphicsContext gc;
	
	@FXML
	Scene gameScene;
	
	ArrayList<String> input = new ArrayList<String>(); // key input arrayList
	ArrayList<Zombie> zombieList = new ArrayList<Zombie>();
	
	private boolean addZombie = false;
	int timesRun = 0;
	
	private long startTime = 0;
	private long currentTime = 0;
	private long elapsedTime = 0;
	
	boolean gameOver = false;
	
	public void getScene(Stage primaryStage) {
		gameScene = primaryStage.getScene();
	}
	
    public void gameLoop() {

    	// handles key input
    	gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (!input.contains(code))
                    input.add(code);
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (input.contains(code))
                    input.remove(code);
            }
        });
        
		gc = gameCanvas.getGraphicsContext2D(); // initialize the canvas for 2D drawing
		
		Image background = new Image("images/background.jpg"); // loads background image
		Player player = new Player(gc, gameCanvas);
		Zombie zombie = new Zombie(gc, gameCanvas);
		Score score = new Score(gc, gameCanvas);
		
		// creates 4 cacti in arrayList
		for (int i = 0; i < zombie.numZombies; i++) {
			zombieList.add(new Zombie(gc, gameCanvas));
		}
				
		new AnimationTimer() {
					
			// actual game loop that repeats
			@Override
			public void handle(long currentNanoTime) { // repeat at 60 frames per second
				
				// clear the whole canvas each frame
				gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
								
				gc.drawImage(background, 0, 0);
								
				if (!gameOver) {
					player.move(input);
									
					for (int i = 0; i < zombie.numZombies; i++) {
						zombieList.get(i).move(player.getX(), player.getY());
						boolean collide = player.hitZombie(zombieList.get(i));
						if (collide) {
							player.setScore(-100);
							player.setX((int)(Math.random()*900) + 200);
							player.setY((int)(Math.random()*400) + 200);
						}
					}
	
					if (timesRun == 0) {
						startTime = System.currentTimeMillis();
						
						new Thread (new Runnable() {
							public void run() {
								try {
									for (int i=5; i>=0; i--) {
										Thread.sleep(5000);
										player.setScore(100);
										addZombie = true;
										if (i == 0 && !gameOver) {
											i = 5;
										}
									}
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
						
						}).start();				
						
						timesRun += 1;
					}
					
					if (addZombie) {
						zombie.numZombies += 1;
						zombieList.add(new Zombie(gc, gameCanvas));
						addZombie = false;
					}
					
					score.displayScore(player);
					
					if (player.getScore() <= 0) {
						currentTime = System.currentTimeMillis();
						gameOver = true;
					}
					
				} else {
					gameOver = true;
					player.setScore(0);
					elapsedTime = (currentTime - startTime) / 1000;
					System.out.println(elapsedTime);
					score.displayTime(elapsedTime);
				}
			}
			
		}.start();
		
	}
	
}
