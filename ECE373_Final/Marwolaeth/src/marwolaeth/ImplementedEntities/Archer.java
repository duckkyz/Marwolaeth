/*Get more sprites at : http://gaurav.munjal.us/Universal-LPC-Spritesheet-Character-Generator/
 * 
 * 
 * 
 * 
 * 
 */


package marwolaeth.ImplementedEntities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Hero;
import marwolaeth.DrawableClasses.Modifier;
import marwolaeth.ImplementedModifiers.BearTrap;
import marwolaeth.ImplementedModifiers.SlowDown;
import marwolaeth.ImplementedProjectiles.Arrow;
import marwolaeth.ImplementedProjectiles.Fireball;


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
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(4);
	}
	
	public void ability2Setup() {							
		setActionStep(0);
		setMoveCasting(false);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(4);
	}
	
	public void ability3Setup() {	
		setActionStep(0);
		Game.addDrawable(new BearTrap(1, getXPos()+60, getYPos(), false));
	}
	
	public void ability4Setup() {
		//TODO implement this
	}
	
	
	public void ability1Execute(int direction) {
		if(this == Game.getHero()){
			Game.addDrawable(new Arrow(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));	//math gives the created object a reference to the center of the hero
		}
		else{
			Game.addDrawable(new Arrow(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));	//math gives the created object a reference to the center of the enemy
		}
	}

	public void ability2Execute(int direction) {
		if(this.getMana() > 15){

			if(this == Game.getHero()){
				Game.addDrawable(new Arrow(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
				Game.addDrawable(new Arrow(direction+45, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
				Game.addDrawable(new Arrow(direction-45, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true));
			}
			else{
				Game.addDrawable(new Arrow(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
				Game.addDrawable(new Arrow(direction+45, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
				Game.addDrawable(new Arrow(direction-45, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false));
			}
			this.setMana(this.getMana() - 15);
		}
	}
	
	public void ability3Execute(int direction) {
		//TODO implement this
	}
	
	public void ability4Execute(int direction) {
		//TODO implement this
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
