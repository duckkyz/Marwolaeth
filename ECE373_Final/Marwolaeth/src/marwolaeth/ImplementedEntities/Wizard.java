package marwolaeth.ImplementedEntities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Drawable;
import marwolaeth.DrawableClasses.Hero;
import marwolaeth.DrawableClasses.Sprite;
import marwolaeth.ImplementedProjectiles.*;


public class Wizard extends Hero{
	
	public Wizard(int direction, int xPos, int yPos) {
		super(direction, xPos, yPos);
		setTileWidth(64);
		setTileHeight(64);
		setAbility1Cooldown(10);
		setAbility2Cooldown(10);
		setAbility3Cooldown(20);
		setAbility4Cooldown(15);
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
		abilitySetupHelper(3);
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
		setMoveCasting(false);
		setCompleteingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(0);
	}

	public void ability1Execute(int direction) {
		if(this.getMana() > 10){
			boolean isHero = false;
			if(this == Game.getHero()){
				isHero = true;
			}
			int temp = (int) (Math.random() * 100);
			if(temp%3 == 0){
				Game.addDrawable(new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy
			}
			else if(temp%3 == 1){
				Game.addDrawable(new Icicle(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy
			}
			else if(temp%3 == 2){
				Game.addDrawable(new Shock(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy
			}
			this.setMana(this.getMana() - 10);
		}
	}

	public void ability2Execute(int direction) {
		if(this.getMana() > 10){
			boolean isHero = false;
			if(this == Game.getHero()){
				isHero = true;
			}
			Game.addDrawable(new Icicle(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy
			this.setMana(this.getMana() - 10);
		}		
	}
	
	public void ability3Execute(int direction) {
		if(this.getMana() > 30){
			boolean isHero = false;
			if(this == Game.getHero()){
				isHero = true;
			}
			Game.addDrawable(new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Icicle(direction+30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			Game.addDrawable(new Shock(direction-30, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));
			this.setMana(this.getMana() - 30);
		}
	}
	
	public void ability4Execute(int direction) {
		if(this.getMana() > 25){
			int blinkDist = (int) ((Math.random() * 128) + 64);
			int temp = this.getEffectiveDirection();
			//TODO: finish this
			if(temp == 0){
				this.setYPos(this.getYPos() - blinkDist);
				if(this.getYPos() < 128){
					this.setYPos(128);
				}
				while(Game.checkCanSpawn(this) == false){
					this.setYPos(this.getYPos() + 10);
				}
			}
			else if(temp == 90){
				this.setXPos(this.getXPos() + blinkDist);
				if(this.getXPos() > (Game.getMapWidth() - 64)){
					this.setXPos(Game.getMapWidth() - 64);
				}
				while(Game.checkCanSpawn(this) == false){
					this.setXPos(this.getXPos() - 10);
				}
			}
			else if(temp == 180){
				this.setYPos(this.getYPos() + blinkDist);
				if(this.getYPos() > (Game.getMapHeight() - 64)){
					this.setYPos(Game.getMapHeight() - 64);
				}
				while(Game.checkCanSpawn(this) == false){
					this.setYPos(this.getYPos() - 10);
				}
			}
			else if(temp == 270){
				this.setXPos(this.getXPos() - blinkDist);
				if(this.getXPos() < 32){
					this.setXPos(32);
				}
				while(Game.checkCanSpawn(this) == false){
					this.setXPos(this.getXPos() + 10);
				}
			}
			this.setMana(this.getMana() - 25);
		}
	}

	public void doLogic(){
		int xDistFromHero = getXPos() - Game.getHero().getXPos();
		int yDistFromHero = getYPos() - Game.getHero().getYPos();
	
		if(Game.getIsTitleScreen()){
			if(markedForDeath == Game.getHero()){
				Drawable temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
				while(!(temp instanceof Sprite)){
					temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
				}
				markedForDeath = (Sprite)temp;
				markedForDeath.setMarkedForDeath(this);
			}
			else if(!Game.getDrawables().contains(markedForDeath)){
				Drawable temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
				while(!(temp instanceof Sprite)){
					temp = Game.getDrawables().get((int)(Math.random() * Game.getDrawables().size()));
				}
				markedForDeath = (Sprite)temp;
			}
		}
		xDistFromHero = getXPos() - markedForDeath.getXPos();
		yDistFromHero = getYPos() - markedForDeath.getYPos();
		
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
