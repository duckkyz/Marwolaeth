package marwolaeth;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Wizard extends Hero{
	
	public Wizard(int direction, int xPos, int yPos) {
		super(direction, xPos, yPos);
		setTileWidth(64);
		setTileHeight(64);
		try {
			setGraphic(ImageIO.read(new File("DrawableImages/Wizard.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics imageGraphics) {
		//The image is drawn at an offset of 1/2 image size so that the center of the image will correspond with the center of the screen.
		//Collision checks should keep in mind that XPos is a reference to the top left corner and not the center.
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), null);
		
	}
}
