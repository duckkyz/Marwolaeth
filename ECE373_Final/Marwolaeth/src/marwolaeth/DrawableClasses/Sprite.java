package marwolaeth.DrawableClasses;

public class Sprite extends Drawable{
	//TODO : Hitboxes
	private boolean moveCasting = false;
	private boolean isMoving = false;
	private boolean completeingSequence = false;	//if the sprite needs to finish performing current action before performing others
	private int invokedAbility = 0;					//used to keep track of which ability was issued for cases when multiple abilities use the same actionSequence
	//private int speed = 12;						//make only divisible by 2
	private int speed = 30;							//make only divisible by 2
	private int direction = 0;
	private int health = 100;
	private int mana = 100;
	
	public Sprite(int direction, int spawnX, int spawnY) {
		setDirection(direction);
		setXPos(spawnX);
		setYPos(spawnY);
	}
	
	public boolean getMoveCasting() {
		return moveCasting;
	}
	
	public boolean getIsMoving() {
		return isMoving;
	}
	
	public boolean getCompleteingSequence() {
		return completeingSequence;
	}
	
	public int getInvokedAbility() {
		return invokedAbility;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMana() {
		return mana;
	}
	
	public void setMoveCasting(boolean moveCasting) {
		this.moveCasting = moveCasting;
		this.isMoving = moveCasting;					//this line is intentional
	}
	
	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public void setCompleteingSequence(boolean completeingSequence) {
		this.completeingSequence = completeingSequence;
	}
	
	public void setInvokedAbility(int invokedAbility) {
		this.invokedAbility = invokedAbility;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public void move() {
		if(isMoving == true) {
			switch(direction){
			case 0:
				this.setYPos(getYPos()+(-1)*this.speed);
				break;
			case 45:
				this.setXPos(getXPos()+((int)Math.floor(this.speed/1.414)));
				this.setYPos(getYPos()+(-1)*((int)Math.floor(this.speed/1.414)));
				break;
			case 90:
				this.setXPos(getXPos()+this.speed);
				break;
			case 135:
				this.setXPos(getXPos()+((int)Math.floor(this.speed/1.414)));
				this.setYPos(getYPos()+((int)Math.floor(this.speed/1.414)));
				break;
			case 180:
				this.setYPos(getYPos()+this.speed);
				break;
			case 225:
				this.setXPos(getXPos()+(-1)*((int)Math.floor(this.speed/1.414)));
				this.setYPos(getYPos()+((int)Math.floor(this.speed/1.414)));
				break;
			case 270:
				this.setXPos(getXPos()+(-1)*this.speed);
				break;
			case 315:
				this.setXPos(getXPos()+(-1)*((int)Math.floor(this.speed/1.414)));
				this.setYPos(getYPos()+(-1)*((int)Math.floor(this.speed/1.414)));
				break;	
			}
		}
	}
}