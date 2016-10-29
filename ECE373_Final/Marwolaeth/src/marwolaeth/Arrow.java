package marwolaeth;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Arrow extends Projectile{

	public Arrow(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		try{
			setGraphic(ImageIO.read(new File("DrawableImages/Arrows.png")));
		}
		catch(IOException ex){
			
		}
		setIsMoving(true);
		if(direction == 0) {
			setTileWidth(5);
			setTileHeight(33);
			setActionSequence(1);
			setActionStep(0);
		}
		else if(direction == 90) {
			setTileWidth(33);
			setTileHeight(5);
			setActionSequence(0);
			setActionStep(1);
		}
		else if(direction == 180) {
			setTileWidth(5);
			setTileHeight(33);
			setActionSequence(1);
			setActionStep(1);
		}
		else if(direction == 270) {
			setTileWidth(33);
			setTileHeight(5);
			setActionSequence(0);
			setActionStep(0);
		}
	}

	public void doLogic() {
		this.move();
	}
	
	public void paint(Graphics imageGraphics) {
		if(getActionSequence() == 0)
			imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		else if(getActionSequence() == 1)
			imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), 5, getActionStep()*getTileWidth()+getTileWidth(), 5+getTileHeight(), null);

	}

}
