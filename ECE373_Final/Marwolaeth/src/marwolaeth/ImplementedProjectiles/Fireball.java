package marwolaeth.ImplementedProjectiles;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Projectile;

public class Fireball extends Projectile{

	public Fireball(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY, heroProjectile);
		try{
			setGraphic(rotate((ImageIO.read(new File("Drawable_Images/Fireball.png"))), 0));
		}
		catch(IOException ex){
			
		}
		setIsMoving(true);
		setTileWidth(64);
		setTileHeight(64);
		if(direction == 0) {
			setActionSequence(2);
			setActionStep(0);
			setXPos(getXPos()-getTileWidth()/2);			//centers projectile
		}
		else if(direction == 90) {
			setActionSequence(4);
			setActionStep(1);
			
			setXPos(getXPos()-getTileWidth());				//accounts for image drawing from top left
			setYPos(getYPos()-getTileHeight()/2);
		}
		else if(direction == 180) {
			setActionSequence(6);
			setActionStep(1);
			setXPos(getXPos()-getTileWidth()/2);
			setYPos(getYPos()-getTileHeight());
		}
		else if(direction == 270) {
			setActionSequence(0);
			setActionStep(0);
			setYPos(getYPos()-getTileHeight()/2);
		}
		
	}

	public void doLogic() {
		if(getActionStep()!=7)
			setActionStep(getActionStep()+1);
		else 
			setActionStep(0);
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
	
	public void paint(Graphics imageGraphics) {			//this code works when tileWidth=tileHeight
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		//imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), null);
	}
}
