package marwolaeth.DrawableClasses;

import java.awt.Graphics;

import marwolaeth.Game;

public abstract class Modifier extends Drawable{

	private Sprite target;		//The unit the Modifier is modifying
	private int duration;			//The number of waves the target stays modified
	private boolean activated = false;		//The modifier has been given a target
	private boolean heroOnly;		//If true, only the hero should be able to activate the modifier
	private int removalWave;		//The wave that the modifier should end
	
	public Modifier(int wavesDuration, int xPos, int yPos, boolean heroOnly) {
		super(xPos, yPos);
		this.duration = wavesDuration;
		this.heroOnly = heroOnly;
	}
	
	public abstract void addModifier();									//Add modifier to target
	public abstract void removeModifier();								//Reverse modifier to target if necessary
	
	public void activate(Sprite target, int currentWave) {				//Call this method when a Sprite collides with the modifier and satisfies the heroOnly condition
		this.target = target;
		removalWave = currentWave + duration;
		activated = true;
		this.addModifier();
	}
	
	public void doLogic() {
		if(Game.getCurrentWave() == removalWave && activated == true) {
			this.removeModifier();										
			
			//Removes all references so modifier is garbage collected
			target = null;
			Game.removeDrawable(this);
		}
		else if(activated == true){
			this.setXPos(target.getXPos() + (target.getTileWidth()/2));
			this.setYPos(target.getYPos() + (target.getTileHeight()/2));
		}
	}
	
	public void paint(Graphics imageGraphics) {	
		//if(activated==true)
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
	}
	
	public Sprite getTarget() {
		return target;
	}
	
	public boolean getHeroOnly() {										//Used for when Sprite collide with modifier
		return heroOnly;
	}
	
}
