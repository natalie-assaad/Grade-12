package natalieZombieGame;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Zombie {
	
    int speed = (int)(Math.random()*4) + 1;
	double dx = 1;
	double dy = 1;
    double x = (int)(Math.random()*800) + 100;
    double y = (int)(Math.random()*500) + 100;
    String imageName = "images/zombie.png";
    Image image = new Image(imageName);
    GraphicsContext gc;
    
	public int numZombies = 4;
    
    @FXML
    Canvas gameCanvas;

    public Zombie (GraphicsContext gc, Canvas canvas) {
        super();
        this.gc = gc;
        this.gameCanvas = canvas;
    }
    
    public int getSpeed() {
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

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getWidth() {
    	return image.getWidth();
    }

    public double getHeight() {
    	return image.getHeight();
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getNumZombiess() {
		return numZombies;
    }

    public int setNumZombies(int numZombies) {
    	return this.numZombies = numZombies;
    }
    
    public void move(double pX, double pY) {

    	double playerX = pX;
    	double playerY = pY;
        
        if (this.x < playerX) {
        	this.x += this.dx;
        } else if (this.x > playerX) {
        	this.x -= this.dx;
        }
        
        if (this.y < playerY) {
        	this.y += this.dy;
        } else if (this.y > playerY) {
        	this.y -= this.dy;
        }
        
        if (this.x <= 0 ||this.x >= this.gameCanvas.getWidth() - this.image.getWidth()) {
            this.dx = -this.dx;
        }
        if (this.y <= 0||this.y >= this.gameCanvas.getHeight() - this.image.getHeight()) {
            this.dy = -this.dy;
        }
        this.gc.drawImage(this.image, this.x, this.y);
    }

    public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
    }
}
