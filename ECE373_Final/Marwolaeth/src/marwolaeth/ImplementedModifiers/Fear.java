package marwolaeth.ImplementedModifiers;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Modifier;

public class Fear extends Modifier {
	
	private int speedBoost = 6;
	private boolean doneEffected = false;
	private int effectCounter = 0;

	public Fear(int wavesDuration, int xPos, int yPos, boolean heroOnly) {						//Uses default -8 speed modifier
		super(wavesDuration, xPos, yPos, heroOnly);
		try{
			setGraphic(ImageIO.read(new File("Drawable_Images/Modifiers.png")));
		}
		catch(IOException ex){
			
		}
		setTileWidth(32);
		setTileHeight(32);
		setActionStep(0);
		setActionSequence(3);
	}

	public void addModifier() {
		getTarget().setSpeed(getTarget().getSpeed() + speedBoost);
	}

	public void removeModifier() {
		if(getTarget() != null){
			getTarget().setSpeed(getTarget().getSpeed() - speedBoost);
			getTarget().setIsMoving(true);
			getTarget().setDirection(180);
		}
		target = null;
		Game.removeDrawable(this);
	}

	public void doLogic(){
		if(getIsActivated()){
			if(getTarget() == null){
				Game.removeDrawable(this);
				return;
			}
			getTarget().setIsMoving(true);
			getTarget().setDirection(((int)(Math.random() * 8)) * 45);

			if(effectCounter > 45){
				removeModifier();
				Game.removeDrawable(this);
				return;
			}
			++effectCounter;
		}
	}
}
