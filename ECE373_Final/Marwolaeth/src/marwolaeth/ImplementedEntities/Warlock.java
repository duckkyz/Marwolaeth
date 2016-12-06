package marwolaeth.ImplementedEntities;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Hero;
import marwolaeth.ImplementedProjectiles.*;

public class Warlock extends Hero{
	public Warlock(int direction, int spawnX, int spawnY) {
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
			setGraphic(ImageIO.read(new File("Drawable_Images/Warlock.png")));
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
		abilitySetupHelper(1);
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

	public void ability1Execute(int direction) {
		if(this.getMana() > 10){
			boolean isHero = false;
			if(this == Game.getHero()){
				isHero = true;
			}
			Game.addDrawable(new Fearball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, isHero));	//math gives the created object a reference to the center of the enemy

			this.setMana(this.getMana() - 10);
		}
	}

	@Override
	public void ability2Execute(int direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ability3Execute(int direction) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ability4Execute(int direction) {
		// TODO Auto-generated method stub

	}	
}
