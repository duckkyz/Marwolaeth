package marwolaeth.ImplementedEntities;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.DrawableClasses.Drawable;

public class Wall extends Drawable {
	
	
	public Wall(int x, int y){
		try{
			this.setXPos(x);
			this.setYPos(y);
			setGraphic(ImageIO.read(new File("Background_Images/MapTiles.png")));
			setActionStep((int) (15 + Math.floor(Math.random() * 0)));
			setActionSequence((int) (15 + Math.floor(Math.random() * 5)));		}
		catch(IOException ex){
			
		}
	}
	
	public void paint(Graphics imageGraphics){
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), null);
	}
}
