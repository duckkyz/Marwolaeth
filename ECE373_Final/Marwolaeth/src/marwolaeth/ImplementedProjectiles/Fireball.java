package marwolaeth.ImplementedProjectiles;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Projectile;
import marwolaeth.DrawableClasses.Sprite;

public class Fireball extends Projectile{

	public Fireball(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY, heroProjectile);
		try{
			setGraphic(rotate((ImageIO.read(new File("Drawable_Images/Fireball.png"))), 90));
		}
		catch(IOException ex){
			
		}
		
		setActionSequence(0);
		setActionStep(0);
		setIsMoving(true);

		
		if(direction == 0) {
			setTileWidth(22);
			setTileHeight(64);
			setXPos(getXPos()-getTileWidth()/2);			//centers projectile
			setYPos(getYPos()-getTileHeight());
		}
		else if(direction == 90) {
			setTileWidth(64);
			setTileHeight(22);
			setYPos(getYPos()-getTileHeight()/2);
		}
		else if(direction == 180) {
			setTileWidth(22);
			setTileHeight(64);
			setXPos(getXPos()-getTileWidth()/2);
		}
		else if(direction == 270) {
			setTileWidth(64);
			setTileHeight(22);
			setXPos(getXPos()-getTileWidth());	
			setYPos(getYPos()-getTileHeight()/2);
			
		}
		
		setDamage(20);
		setSpeed(30);
	}

	public void doLogic() {
		if(getDirection() == 0) {
			setActionStep(0);
			if(getActionSequence()!=7)
				setActionSequence(getActionSequence()+1);
			else 
				setActionSequence(0);
		}
		else if(getDirection() == 90) {
			setActionSequence(0);
			if(getActionStep()!=7)
				setActionStep(getActionStep()+1);
			else 
				setActionStep(0);
		}
		else if(getDirection() == 180) {
			setActionStep(0);
			if(getActionSequence()!=7)
				setActionSequence(getActionSequence()+1);
			else 
				setActionSequence(0);
		}
		else if(getDirection() == 270) {
			setActionSequence(0);
			if(getActionStep()!=7)
				setActionStep(getActionStep()+1);
			else 
				setActionStep(0);
		}
	}
	
	public void attack(Sprite beingAttacked){
		int newHealth = beingAttacked.getHealth() - getDamage();
		if(newHealth < 0){
			System.out.println(beingAttacked.getClass().getSimpleName() + " died to a fireball!!!!");
		}
		beingAttacked.setHealth(newHealth);
	}
}
