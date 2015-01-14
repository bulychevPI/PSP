package utilitiesBulychevPI;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.Random;

// Class to represent the food for the snake.

public class Food implements Serializable{

	private int x, y;
	private int width, height; // temporal variables, change with an image after ensuring it works...
	static private final Image img=Toolkit.getDefaultToolkit().getImage( "src/ImagesBulychevPI/apple.jpg"  );
	
	public Food() { // add img as parameter here...
		x = getRandomX();
		y = getRandomY();
		//img = Toolkit.getDefaultToolkit().getImage( "src/Images/apple.jpg"  );
		width = 8;
		height = 8;
	}
	
	// Accessor and modifier methods.
	public Image getImage() {
		return img;
	}
//	public void setImage( String image ) {
//		img = Toolkit.getDefaultToolkit().getImage( image );
//	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public void generateFood() {
		x = getRandomX();
		y = getRandomY();
	}
	
	private int getRandomY() {
		Random randomGen = new Random();
                int i=randomGen.nextInt( 330 );
		return i-(i%8);
	}
	private int getRandomX() {
		Random randomGen = new Random();
                int i=randomGen.nextInt( 370 );
		return i-(i%8);
	}
}
