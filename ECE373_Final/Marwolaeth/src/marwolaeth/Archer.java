package marwolaeth;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Archer extends Hero{
	
	public Archer(int direction, int xPos, int yPos) {
		super(direction, xPos, yPos);
		setTileWidth(64);
		setTileHeight(64);
		try {
			setGraphic(ImageIO.read(new File("DrawableImages/Archer.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics imageGraphics) {
		
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		
	}
}