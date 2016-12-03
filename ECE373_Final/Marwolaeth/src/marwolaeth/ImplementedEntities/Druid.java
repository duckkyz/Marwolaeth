package marwolaeth.ImplementedEntities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Hero;
import marwolaeth.ImplementedProjectiles.*;

public class Druid extends Hero{
	private int animationCounter = 0;
	
	public Druid(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		setTileWidth(64);
		setTileHeight(64);
		setSpeed(12);
		setTopHitBox(15);
		setBotHitBox(2);
		setLeftHitBox(17);
		setRightHitBox(17);
		setAttackRange(16);
		setAttackDamage(25);
		
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/Druid.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ability1Setup() {
		setActionStep(0);
		setMoveCasting(true);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(0);
	}

	@Override
	public void ability2Setup() {
		setActionStep(0);
		setMoveCasting(true);								//whether the hero can move while using this ability
		setcompletingSequence(true);
		// (0)Spell-cast, (1)Thrusting, (2)NA, (3)Slashing, (4)Shooting
		abilitySetupHelper(3);
	}

	@Override
	public void ability3Setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ability4Setup() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ability1Execute(int direction) {
		setHealth(getHealth() + 10);
		Leaf tempLeaf = new Leaf(0, this.getXPos() - 10, this.getYPos() - 10, true);
		tempLeaf.setIsMoving(false);
		Game.addDrawable(tempLeaf);
	}

	@Override
	public void ability2Execute(int direction) {
		Leaf leafBolt = new Leaf(0,0,0,false);
		if(this == Game.getHero()){
			leafBolt = new Leaf(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true);	//math gives the created object a reference to the center of the hero
		}
		else{
			leafBolt = new Leaf(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, false);	//math gives the created object a reference to the center of the enemy
		}
		Game.addDrawable(leafBolt);	
	}

	@Override
	public void ability3Execute(int direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ability4Execute(int direction) {
		// TODO Auto-generated method stub

	}	

	public void move(){
		super.move();
		if((getIsMoving() == true) & (animationCounter%7 == 0)){
			Leaf leafTrailLeft = new Leaf(0, this.getXPos() + (this.getLeftHitBox()), this.getYPos() + (this.getTileHeight() - this.getBotHitBox()), true);
			Leaf leafTrailRight = new Leaf(0, this.getXPos() + (this.getTileWidth() - this.getRightHitBox()), this.getYPos() + (this.getTileHeight() - this.getBotHitBox()), true);
			leafTrailLeft.setIsMoving(false);
			leafTrailRight.setIsMoving(false);
			Game.addDrawable(leafTrailLeft);
			Game.addDrawable(leafTrailRight);
			animationCounter++;
		}
		else if(animationCounter > 100){
			animationCounter = 0;
		}
		else{
			animationCounter++;
		}
	}
	
	public void doLogic(){
		int xDistFromHero = getXPos() - Game.getHero().getXPos();
		int yDistFromHero = getYPos() - Game.getHero().getYPos();
		
		switch(getEffectiveDirection()){
			case 0:
				if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero > 0) & (yDistFromHero < (50 + getAttackRange())))){
					setIsAttacking(true);
				}
				else if((xDistFromHero > 0) & ((yDistFromHero > 0) & (yDistFromHero < getAttackRange()))){
					setDirection(270);
				}
				else if((xDistFromHero < 0) & ((yDistFromHero > 0) & (yDistFromHero < getAttackRange()))){
					setDirection(90);
				}
				else if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero < 0))){
					setDirection(180);
				}
				break;
			case 90:
				if(((xDistFromHero > -(50 + getAttackRange())) & (xDistFromHero < 0)) & ((yDistFromHero > -50) & (yDistFromHero < 50))){
					setIsAttacking(true);
				}
				else if((yDistFromHero > 0) & ((xDistFromHero > 0) & (xDistFromHero < getAttackRange()))){
					setDirection(0);
				}
				else if((yDistFromHero < 0) & ((xDistFromHero > 0) & (xDistFromHero < getAttackRange()))){
					setDirection(180);
				}
				else if(((yDistFromHero > -50) & (yDistFromHero < 50)) & ((xDistFromHero > 0))){
					setDirection(270);
				}
				break;
			case 180:
				if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero > -(50 + getAttackRange())) & (yDistFromHero < 0))){
					setIsAttacking(true);
				}
				else if((xDistFromHero > 0) & ((yDistFromHero > 0) & (yDistFromHero < getAttackRange()))){
					setDirection(270);
				}
				else if((xDistFromHero < 0) & ((yDistFromHero > 0) & (yDistFromHero < getAttackRange()))){
					setDirection(90);
				}
				else if(((xDistFromHero > -50) & (xDistFromHero < 50)) & ((yDistFromHero > 0))){
					setDirection(0);
				}
				break;
			case 270:
				if(((xDistFromHero > 0) & (xDistFromHero < (50 + getAttackRange()))) & ((yDistFromHero > -50) & (yDistFromHero < 50))){
					setIsAttacking(true);
				}
				else if((yDistFromHero > 0) & ((xDistFromHero > 0) & (xDistFromHero < getAttackRange()))){
					setDirection(0);
				}
				else if((yDistFromHero < 0) & ((xDistFromHero > 0) & (xDistFromHero < getAttackRange()))){
					setDirection(180);
				}
				else if(((yDistFromHero > -50) & (yDistFromHero < 50)) & ((xDistFromHero < 0))){
					setDirection(90);
				}
				break;
		}
		
		super.doLogic();
		if(getIsMoving() == false){
			setDirection((int) (45 * (Math.floor(((Math.random() * 360) / 45)))));
		}
		if(getIsAttacking() == true){
			setIsMoving(false);
		}
	}
	

}
