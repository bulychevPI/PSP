package utilitiesBulychevPI;

import java.awt.Color;
import java.io.Serializable;

/** Class to represent a single body part of the snake.
 */ 

public class BodyPart implements Serializable{

	private int x, y;
	private int width, height;
	private Color col;
	
	public BodyPart( int x, int y, int width, int height, Color col ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.col = col;
	}
	
	// Accessor and modifier methods.
	public int getX() {
		return x;
	}
	public void setX( int x ) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY( int y ) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public Color getCol() {
		return col;
	}
	public void setCol( Color col ) {
		this.col = col;
	}
}