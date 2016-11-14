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
		setTileWidth(64);
		setTileHeight(64);
		//setSpeed(6);
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
		setActionStep(0);
		setMoveCasting(true);
		setCompleteingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(3);	}
	
	public void ability3Setup() {	
		setActionStep(0);
		setMoveCasting(true);
		setCompleteingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(0);
	}
	
	public void ability4Setup() {
		setActionStep(0);
		setMoveCasting(true);
		setCompleteingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(0);
	}

	public void ability1Execute(int direction) {
		Fireball fireball = new Fireball(0,0,0,false);
		if(this == Game.getHero()){
			fireball = new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true);	//math gives the created object a reference to the center of the hero
		}
		else{
			fireball = new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false);	//math gives the created object a reference to the center of the enemy
		}
		Game.addDrawable(fireball);		
	}

	public void ability2Execute(int direction) {
		//TODO implement this
	}
	
	public void ability3Execute(int direction) {
		if(this == Game.getHero()){
			Game.addDrawable(new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
			Game.addDrawable(new Fireball(direction+30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
			Game.addDrawable(new Fireball(direction-30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
		}
		else{
			Game.addDrawable(new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
			Game.addDrawable(new Fireball(direction+30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
			Game.addDrawable(new Fireball(direction-30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
		}
	}
	
	public void ability4Execute(int direction) {
		if(this == Game.getHero()){
			Game.addDrawable(new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
			Game.addDrawable(new Fireball(direction+30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
			Game.addDrawable(new Fireball(direction-30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
			Game.addDrawable(new Fireball(direction-60, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
			Game.addDrawable(new Fireball(direction+60, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
			Game.addDrawable(new Fireball(direction-90, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
			Game.addDrawable(new Fireball(direction+90, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
		}
		else{
			Game.addDrawable(new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
			Game.addDrawable(new Fireball(direction+30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
			Game.addDrawable(new Fireball(direction-30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
			Game.addDrawable(new Fireball(direction-60, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
			Game.addDrawable(new Fireball(direction+60, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
			Game.addDrawable(new Fireball(direction-90, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
			Game.addDrawable(new Fireball(direction+90, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
		}
		
	}

	public void doLogic(){
		int xDistFromHero = getXPos() - Game.getHero().getXPos();
		int yDistFromHero = getYPos() - Game.getHero().getYPos();
		
		switch(getEffectiveDirection()){
			case 0:
				if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero > 0) & (yDistFromHero < 2000))){
					setIsAttacking(true);
				}
				break;
			case 90:
				if(((xDistFromHero > -2000) & (xDistFromHero < 0)) & ((yDistFromHero > -50) & (yDistFromHero < 50))){
					setIsAttacking(true);
				}
				break;
			case 180:
				if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero > -2000) & (yDistFromHero < 0))){
					setIsAttacking(true);
				}
				break;
			case 270:
				if(((xDistFromHero > 0) & (xDistFromHero < 2000)) & ((yDistFromHero > -50) & (yDistFromHero < 50))){
					setIsAttacking(true);
				}
				break;
		}
		
		super.doLogic();
		if(getIsAttacking() == true){
			setIsMoving(false);
		}
	}
}
