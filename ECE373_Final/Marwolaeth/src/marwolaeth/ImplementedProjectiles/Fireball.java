package marwolaeth.ImplementedProjectiles;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Projectile;
import marwolaeth.DrawableClasses.Sprite;
	
public class Fireball extends Projectile{			//Using the "CUSTOM" label to show what parameters should be changed when creating a similar projectile.
	
	private int xRef = 0;			//Location of first fireball in png. Is set to one of the 4 corners of the png
	private int yRef = 0;			
	private int xSlope = 1;			//As the actionSequence increments, is the next sequence in the positive or negative direction relative to the previous sequence
	private int ySlope = 1;
	private int xStart = 0;			//The top left coordinate in the portion of the png being drawn
	private int xStop = 0;			//The top right coordinate in the portion of the png being drawn
	private int yStart = 0;			//The bottom left coordinate in the portion of the png being drawn	
	private int yStop = 0;			//The bottem right coordinate in the portion of the png being drawn
	
	private int numOfFrames = 8;				//CUSTOM: Number of frames in the actionSequence (ex. 8 fireballs)
	private int limitingDimension = 0;			//The lesser of the width/height from the original png.
	private int nonlimitingDimension = 0;
	
	public Fireball(int direction, int spawnX, int spawnY, boolean heroProjectile) {
		super(direction, spawnX, spawnY, heroProjectile);
		
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/Fireball.png")));		//CUSTOM
			int origionalWidth = getGraphic().getWidth();
			int origionalHeight = getGraphic().getHeight();
			if(origionalWidth <= origionalHeight) {
				limitingDimension = origionalWidth;
				nonlimitingDimension = origionalHeight/numOfFrames-limitingDimension;
			}
			else {
				limitingDimension = origionalHeight;
				nonlimitingDimension = origionalWidth/numOfFrames-limitingDimension;
			}
			setGraphic(rotate((ImageIO.read(new File("Drawable_Images/Fireball.png"))), 90));	//CUSTOM: Make sure to use the second parameter in the method to rotate the image to the 0th degree as a default
			//File outputfile = new File("image.jpg");
			//ImageIO.write(getGraphic(), "jpg", outputfile);
		}
		catch(IOException ex){
			
		}
		
		setActionSequence(0);
		setActionStep(0);
		setTileWidth((int)(limitingDimension+nonlimitingDimension*Math.abs(Math.sin(Math.toRadians(direction)))));
		setTileHeight((int)(limitingDimension+nonlimitingDimension*Math.abs(Math.cos(Math.toRadians(direction)))));
		setIsMoving(true);
		setXPos(getXPos()-getTileWidth()/2);
		setYPos(getYPos()-getTileHeight()/2);
		
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
		
		setDamage(20);											//CUSTOM
		setSpeed(30);											//CUSTOM
	}

	public void doLogic() {
		if(getActionSequence()==(numOfFrames-1))
			setActionSequence(0);
		else
			setActionSequence(getActionSequence()+1);
		//setActionSequence(0);
	}
	
	public void attack(Sprite beingAttacked){
		int newHealth = beingAttacked.getHealth() - getDamage();
		if(newHealth < 0){
			System.out.println(beingAttacked.getClass().getSimpleName() + " died to a fireball!!!!");
		}
		beingAttacked.setHealth(newHealth);
	}
	
	public void paint(Graphics imageGraphics) {			
		if(getDirection() >= 0 & getDirection() <= 90) {
			xStart = xRef+xSlope*getTileWidth()+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames;
			xStop = xRef+xSlope*getTileWidth()+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames+getTileWidth();
			yStart = yRef+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames;
			yStop = yRef+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames+getTileHeight();
		}
		else if(getDirection() >= 90 & getDirection() <= 180) {
			xStart = xRef+xSlope*getTileWidth()+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames;
			xStop = xRef+xSlope*getTileWidth()+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames+getTileWidth();
			yStart = yRef+ySlope*getTileHeight()+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames;
			yStop = yRef+ySlope*getTileHeight()+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames+getTileHeight();
		}
		else if(getDirection() >= 180 & getDirection() <= 270) {
			xStart = xRef+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames;
			xStop = xRef+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames+getTileWidth();
			yStart = yRef+ySlope*getTileHeight()+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames;
			yStop = yRef+ySlope*getTileHeight()+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames+getTileHeight();
		}
		else if(getDirection() >= 270 & getDirection() <= 360) {
			xStart = xRef+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames;
			xStop = xRef+xSlope*getActionSequence()*(getGraphic().getWidth()-getTileWidth()*(int)Math.abs(Math.cos(Math.toRadians(getDirection()))))/numOfFrames+getTileWidth();
			yStart = yRef+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames;
			yStop = yRef+ySlope*getActionSequence()*(getGraphic().getHeight()-getTileHeight()*(int)Math.abs(Math.sin(Math.toRadians(getDirection()))))/numOfFrames+getTileHeight();
		}
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), xStart, yStart, xStop, yStop, null);
	}
}
