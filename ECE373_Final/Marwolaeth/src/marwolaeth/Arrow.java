package marwolaeth;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Arrow extends Projectile{

	public Arrow(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY, heroProjectile);
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/Arrow.png")));
		}
		catch(IOException ex){
			
		}
		setIsMoving(true);
		if(direction == 0) {
			setTileWidth(5);
			setTileHeight(33);
			setActionSequence(1);
			setActionStep(0);
			setXPos(getXPos()-getTileWidth()/2);			//centers projectile
		}
		else if(direction == 90) {
			setTileWidth(33);
			setTileHeight(5);
			setActionSequence(0);
			setActionStep(1);
			
			setXPos(getXPos()-getTileWidth());				//accounts for image drawing from top left
			setYPos(getYPos()-getTileHeight()/2);
		}
		else if(direction == 180) {
			setTileWidth(5);
			setTileHeight(33);
			setActionSequence(1);
			setActionStep(1);
			setXPos(getXPos()-getTileWidth()/2);
			setYPos(getYPos()-getTileHeight());
		}
		else if(direction == 270) {
			setTileWidth(33);
			setTileHeight(5);
			setActionSequence(0);
			setActionStep(0);
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
		if(getActionSequence() == 0)		//Left and Right
			imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		else if(getActionSequence() == 1)	//Up and Down
			imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getTileWidth(), getActionStep()*getTileWidth()+getTileWidth(), getTileWidth()+getTileHeight(), null);

	}

}
