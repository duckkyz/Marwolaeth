package marwolaeth.ImplementedEntities;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Hero;
import marwolaeth.ImplementedProjectiles.Arrow;


public class Archer extends Hero{
	
	public Archer(int direction, int xPos, int yPos) {
		super(direction, xPos, yPos);
		setTileWidth(64);
		setTileHeight(64);
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/Archer.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ability1Setup() {							//sets the ActionSequence that ability_1 invokes (based off which direction hero is currently facing)
		setActionStep(0);
		setMoveCasting(false);								//whether the hero can move while using this ability
		setCompleteingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(4);
	}
	
	public void ability2Setup() {							
		setActionStep(0);
		setMoveCasting(false);								//whether the hero can move while using this ability
		setCompleteingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(4);
	}
	
	public void ability3Setup() {	
		//TODO implement this
	}
	
	public void ability4Setup() {
		//TODO implement this
	}
	
	
	public void ability1Execute(int direction) {
		Arrow arrow = new Arrow(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true);	//math gives the created object a reference to the center of the hero
		Game.addDrawable(arrow);
	}

	public void ability2Execute(int direction) {
		Arrow arrow1 = new Arrow(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true);	//math gives the created object a reference to the center of the hero
		Game.addDrawable(arrow1);
		Arrow arrow2 = new Arrow(direction-45, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true);	//math gives the created object a reference to the center of the hero
		Game.addDrawable(arrow2);
		Arrow arrow3 = new Arrow(direction+45, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true);	//math gives the created object a reference to the center of the hero
		Game.addDrawable(arrow3);
	}
	
	public void ability3Execute(int direction) {
		//TODO implement this
	}
	
	public void ability4Execute(int direction) {
		//TODO implement this
	}
	
	public void paint(Graphics imageGraphics) {
		
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		
	}

}