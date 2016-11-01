package marwolaeth.ImplementedProjectiles;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Projectile;

public class Arrow extends Projectile{

	public Arrow(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY, heroProjectile);
		try{
			setGraphic(rotate((ImageIO.read(new File("Drawable_Images/Arrow.png"))), 90));
		}
		catch(IOException ex){
			
		}
		setIsMoving(true);
		if(direction == 0) {
			setXPos(getXPos()-getTileWidth()/2);			//centers projectile
		}
		else if(direction == 90) {
			setXPos(getXPos()-getTileWidth());				//accounts for image drawing from top left
			setYPos(getYPos()-getTileHeight()/2);
		}
		else if(direction == 180) {
			setXPos(getXPos()-getTileWidth()/2);
			setYPos(getYPos()-getTileHeight());
		}
		else if(direction == 270) {
			setYPos(getYPos()-getTileHeight()/2);
		}
		
	}

	public void doLogic() {
		if(getXPos() > 10000)							//arrow destroys itself if moves too far away
			Game.removeDrawable(this);
		else if(getXPos() < -10000)
			Game.removeDrawable(this);
		else if(getYPos() > 10000)
			Game.removeDrawable(this);
		else if(getYPos() < -10000) {
			Game.removeDrawable(this);
		}
		this.move();									//this should actually be done in game. also needs logic to get deleted
	}
	
	public void paint(Graphics imageGraphics) {		
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), null);
	}

}
