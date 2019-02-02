package natalieZombieGame;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Player {

	private String left = "LEFT";
	private String right = "RIGHT";
	private String up = "UP";
	private String down = "DOWN";
	private double dx = 1;
	private double dy = 1;
	private double speed = 2; 
	private double x = 10;
	private double y = 10;
	private String imageName = "images/player.png";
	private Image image = new Image(imageName);
	
	public int score = 300;
	
	@FXML
	GraphicsContext gc;

    @FXML
    Canvas gameCanvas;
	
	// key input arrayList
	ArrayList<String> input = new ArrayList<String>();
	
    public Player (GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
    }
    
    public double getWidth() {
    	return image.getWidth();
    }

    public double getHeight() {
    	return image.getHeight();
    }
    
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
    
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score += score;
	}

    public void move(ArrayList<String> input) {
		
		this.input = input;
		
		// for left button
	    if (this.input.contains(this.left) && this.x >= 0){
	       this.dx = -this.speed;
	    } else if (this.input.contains(this.right) && this.x <= 1280 - this.image.getWidth()){ // for right button
	       this.dx = this.speed;
	    } else {
	    	this.dx = 0; // sets it to 0 if nothing is pressed
	    }
	    
	    // for up button
	    if (this.input.contains(this.up) && this.y >= 0){
	       this.dy = -this.speed;
	    } else if (this.input.contains(this.down) && this.y <= 720 - this.image.getHeight()){ // for down button
	       this.dy = this.speed;
	    } else {
	    	this.dy = 0; // sets it to 0 if nothing is pressed
	    }
	    
	    // move image
	    this.x += this.dx;
	    this.y += this.dy;

	    // redraws image
	    this.gc.drawImage(this.image, this.x, this.y);
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
	
	public boolean hitZombie(Zombie z) {
		boolean collide = z.getBoundary().intersects(this.getBoundary());
        return collide;
	}

	
}
