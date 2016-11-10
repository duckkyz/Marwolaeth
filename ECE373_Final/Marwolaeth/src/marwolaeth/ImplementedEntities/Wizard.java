package marwolaeth.ImplementedEntities;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Hero;
import marwolaeth.ImplementedProjectiles.Arrow;
import marwolaeth.ImplementedProjectiles.Fireball;


public class Wizard extends Hero{
	
	public Wizard(int direction, int xPos, int yPos) {
		super(direction, xPos, yPos);
		setXHitBox(30);
		setYHitBox(46);
		setTileWidth(64);
		setTileHeight(64);
		setSpeed(6);
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/Wizard.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ability1Setup() {							//sets the ActionSequence that ability1 invokes (based off which direction hero is currently facing)
		setActionStep(0);
		setMoveCasting(true);
		setCompleteingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(0);
	}

	public void ability2Setup() {							
		//TODO implement this
	}
	
	public void ability3Setup() {	
		//TODO implement this
	}
	
	public void ability4Setup() {
		//TODO implement this
	}

	public void ability1Execute(int direction) {
		Fireball fireball = new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true);	//math gives the created object a reference to the center of the hero
		Game.addDrawable(fireball);		
	}

	public void ability2Execute(int direction) {
		//TODO implement this
	}
	
	public void ability3Execute(int direction) {
		//TODO implement this
	}
	
	public void ability4Execute(int direction) {
		//TODO implement this
	}
}
