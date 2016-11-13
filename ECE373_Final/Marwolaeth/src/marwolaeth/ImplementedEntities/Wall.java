package marwolaeth.ImplementedEntities;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.DrawableClasses.Drawable;

public class Wall extends Drawable {
	private static BufferedImage img;
	
	public Wall(int x, int y){
		if(img == null){
			try {
				img = (ImageIO.read(new File("Background_Images/MapTiles.png")));
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setTopHitBox(0);
		setBotHitBox(0);
		setLeftHitBox(0);
		setRightHitBox(0);
		this.setXPos(x);
		this.setYPos(y);
		setActionStep((int) (12 + Math.floor(Math.random() * 5)));		//column
		setActionSequence((int) (14 + Math.floor(Math.random() * 0)));	//row
		setGraphic(img);
	}
	
	public void paint(Graphics imageGraphics){
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos() + getTileWidth(), getYPos() + getTileHeight(), getActionStep() * getTileWidth(), getActionSequence() * getTileHeight(), getActionStep() * getTileWidth() + getTileWidth(), getActionSequence() * getTileHeight() + getTileHeight(), null);
	}
}
