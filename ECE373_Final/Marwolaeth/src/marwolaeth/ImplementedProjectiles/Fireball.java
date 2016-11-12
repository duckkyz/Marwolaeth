package marwolaeth.ImplementedProjectiles;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Projectile;
import marwolaeth.DrawableClasses.Sprite;

public class Fireball extends Projectile{
	
	private int xRef = 0;	//Needs to know which corner the first fireball is in the png
	private int yRef = 0;	//Needs to know which corner the first fireball is in the png
	private int xSlope = 1;				//Needs to know if there is a positive or negative slope when transitioning frames
	private int ySlope = 1;
	
	public Fireball(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY, heroProjectile);
		try{
			setGraphic(rotate((ImageIO.read(new File("Drawable_Images/Fireball.png"))), 90));
			//File outputfile = new File("image.jpg");
			//ImageIO.write(getGraphic(), "jpg", outputfile);
		}
		catch(IOException ex){
			
		}
		
		setActionSequence(0);
		setActionStep(0);
		setTileWidth((int)(22+42*Math.abs(Math.sin(Math.toRadians(direction)))));
		setTileHeight((int)(22+42*Math.abs(Math.cos(Math.toRadians(direction)))));
		setIsMoving(true);
		//setXPos(getXPos()-getTileWidth()/2);
		//setYPos(getYPos()-getTileHeight()/2);
		
		System.out.println(getDirection()+" "+getTileWidth()+" "+getTileHeight());
		
		if(direction >= 0 & direction <= 90) {
			xRef = getGraphic().getWidth();
			yRef = 0;
			xSlope = -1;
			ySlope = 1;
		}
		else if(direction >= 90 & direction <= 180) {
			xRef = getGraphic().getWidth();
			yRef = getGraphic().getHeight();
			xSlope = -1;
			ySlope = -1;
		}
		else if(direction >= 180 & direction <= 270) {
			xRef = 0;
			yRef = getGraphic().getHeight();
			xSlope = 1;
			ySlope = -1;
		}
		else if(direction >= 270 & direction <= 360) {
			xRef = 0;
			yRef = 0;
			xSlope = 1;
			ySlope = 1;
		}
		
		setDamage(20);
		setSpeed(30);
	}

	public void doLogic() {
		if(getActionSequence()==7)
			setActionSequence(0);
		else
			setActionSequence(getActionSequence()+1);
		//setActionSequence(3);
	}
	
	public void attack(Sprite beingAttacked){
		int newHealth = beingAttacked.getHealth() - getDamage();
		if(newHealth < 0){
			System.out.println(beingAttacked.getClass().getSimpleName() + " died to a fireball!!!!");
		}
		beingAttacked.setHealth(newHealth);
	}
	
	public void paint(Graphics imageGraphics) {			
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		//imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+xSlope*getTileWidth(), getYPos()+ySlope*getTileHeight(), xRef+xSlope*getActionSequence()*getTileWidth(), yRef+ySlope*getActionSequence()*getTileHeight(), xRef+xSlope*getActionSequence()*getTileWidth()+xSlope*getTileWidth(), yRef+ySlope*getActionSequence()*getTileHeight()+ySlope*getTileHeight(), null);
	}
}
