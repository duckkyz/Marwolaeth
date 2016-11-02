package marwolaeth.DrawableClasses;
import java.awt.Graphics;

public abstract class Projectile extends Sprite{
	
	private boolean heroProjectile;					//if projectile belongs to hero
	
	public Projectile(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY);
		this.heroProjectile = heroProjectile;
		
	}
	
	public abstract void doLogic();
	
	public void paint(Graphics imageGraphics) {			//this code works when tileWidth=tileHeight
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		//imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), null);
	}
	
}
