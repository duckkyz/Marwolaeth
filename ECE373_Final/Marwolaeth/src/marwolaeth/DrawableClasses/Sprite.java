package marwolaeth.DrawableClasses;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import marwolaeth.Game;
import marwolaeth.Interfaces.willAttack;

public class Sprite extends Drawable implements willAttack{
	//TODO : Hitboxes
	private boolean moveCasting = false;
	private boolean staticMovement = false;
	private boolean isMoving = false;
	private boolean completeingSequence = false;	//if the sprite needs to finish performing current action before performing others
	private int invokedAbility = 0;					//used to keep track of which ability was issued for cases when multiple abilities use the same actionSequence
	private int speed = 12;							//make only divisible by 2
	private int direction = 0;
	
	private boolean isAttacking = false;
	
	private int health = 100;
	private int mana = 100;
	
	public Sprite(int direction, int spawnX, int spawnY) {
		setDirection(direction);
		setXPos(spawnX);
		setYPos(spawnY);
		switch(direction) {									//draws the sprite in the direction it was created in
		case 0:
			setActionSequence(8);
			break;
		case 90:
			setActionSequence(11);
			break;
		case 180:
			setActionSequence(10);
			break;
		case 270:
			setActionSequence(9);
			break;
		}
	}
	
	public boolean getMoveCasting() {
		return moveCasting;
	}
	
	public boolean getIsMoving() {
		return isMoving;
	}
	
	public boolean getIsAttacking(){
		return isAttacking;
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
	
	public boolean getStaticMovement(){ 
		return this.staticMovement;
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
	
	public void setIsAttacking(boolean isAttacking){
		this.isAttacking = isAttacking;
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
	
	public void setStaticMovement(boolean staticMovement){
		this.staticMovement = staticMovement;
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
	
	@Override
	public void ability1Setup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ability2Setup() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
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
		
	public void executeAbility(int direction){
		switch(getInvokedAbility()){
			case 1: 
				ability1Execute(direction);
				break;
			case 2:
				ability2Execute(direction);
				break;
			case 3:
				ability3Execute(direction);
				break;
			case 4:
				ability4Execute(direction);
				break;
			}
	}
	
	public void continueSequence() {
		setActionStep(getActionStep()+1);												//goes to the next step of the animation
		
		if(getActionSequence() >= 0 & getActionSequence() <= 3) {						//spell-casting
			if(getActionStep()>6) {
				setSequenceWalking();
			}
			if(getActionStep() == 5) {
				executeAbility(getEffectiveDirection());
			}
		}
		
		else if(getActionSequence() >= 4 & getActionSequence() <= 7) {					//thrusting 
			if(getActionStep()>7) {
				setSequenceWalking();
			}
		}
		
		else if(getActionSequence() >= 12 & getActionSequence() <= 15) {				//slash
			if(getActionStep()>5) {
				setSequenceWalking();
			}
		}
		
		else if(getActionSequence() >= 16 & getActionSequence() <= 19) {				//currently shooting
			if(getActionStep()>12) {
				setSequenceWalking();
			}
			if(getActionStep() == 9) {													//frame 9 matches arrow release
				executeAbility(getEffectiveDirection());
			}
		}
		
		else if(getActionSequence() == 20) {											//death
			if(getActionStep()>5) {
				//game ends
				Game.removeDrawable(this);
			}
		}
	}
	
	public int getEffectiveDirection() {			//gets the direction the hero is facing
		switch(getActionSequence()%4) {
			case 0:
				return 0;
			case 1:
				return 270;
			case 2:
				return 180;
			case 3:
				return 90;
			default:
				return 0;
			}
	}
	
	public void setSequenceWalking() {				//changes the actionSequence to walking based on current effectiveDirection
		setActionStep(0);
		setCompleteingSequence(false);
		switch(getActionSequence()%4) {
			case 0:
				setActionSequence(8);
				break;
			case 1:
				setActionSequence(9);
				break;
			case 2:
				setActionSequence(10);
				break;
			case 3:
				setActionSequence(11);
				break;
			default:
				setActionSequence(8);
				break;
			}
	}
	
	public void doMovementLogic90(int actionSequence, int direction) {			//contains the logic for moving at directions 0, 90, 180, 270
		if(getCompleteingSequence() == false) {
			setDirection(direction);
			if(getStaticMovement() == false) {
				if(getActionSequence()==actionSequence) {
					if(getActionStep()<8)
						setActionStep(getActionStep()+1);
					else
						setActionStep(0);
				}
				else {
					setActionSequence(actionSequence);
					setActionStep(0);
				}
			}
			else {
				if(getActionStep()<=0)
					setActionStep(8);
				else
					setActionStep(getActionStep()-1);
			}
		}
		if(getMoveCasting() == true)
			setDirection(direction);
	}
	
	public void doMovementLogic45(int seq1, int seq2, int direction) {			//contains the logic for moving at directions 45, 135, 225, 315
		if(getCompleteingSequence() == false) {
			setDirection(direction);
			if(getStaticMovement() == false) {
				if(getActionSequence()!=seq1 & getActionSequence()!=seq2)												//if both keys are pressed at the same time, choose one of the directions
					setActionSequence(seq2);
				if(getActionStep()<8)
					setActionStep(getActionStep()+1);
				else
					setActionStep(0);
			}
			else {
				if(getActionStep()<=0)
					setActionStep(8);
				else
					setActionStep(getActionStep()-1);
			}
		}
		if(getMoveCasting() == true)
			setDirection(direction);
	}
		
	public void move() {
		if(isMoving == true) {
			setXPos(getXPos() + (int)(Math.round(Math.sin(Math.toRadians(direction)) * speed)));
			setYPos(getYPos() + (int)(Math.round(Math.cos(Math.toRadians(direction)) * speed * (-1))));
		}
	}
	
	public void abilitySetupHelper(int actionSelection) {
		switch(getActionSequence()){																						
			case 8:
				setActionSequence(4*actionSelection);
				break;
			case 9:
				setActionSequence(4*actionSelection+1);
				break;
			case 10:
				setActionSequence(4*actionSelection+2);
				break;
			case 11:
				setActionSequence(4*actionSelection+3);
				break;
			default:
				setActionSequence(4*actionSelection);
				break;
		}
		if(actionSelection == 5) {
			setActionSequence(20);
		}
	}
		
	public void attack(){
		
	}
	
	public BufferedImage rotate(BufferedImage image, float degreeOffset) 
	{
		float angle = getDirection()+degreeOffset;
	    float radianAngle = (float) Math.toRadians(angle) ; 

	    float sin = (float) Math.abs(Math.sin(radianAngle));
	    float cos = (float) Math.abs(Math.cos(radianAngle));

	    int w = image.getWidth() ;
	    int h = image.getHeight();

	    int neww = (int) Math.round(w * cos + h * sin);
	    int newh = (int) Math.round(h * cos + w * sin);

	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    GraphicsConfiguration gc = gd.getDefaultConfiguration();

	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();

	    //-----------------------MODIFIED--------------------------------------
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON) ;
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC) ;
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY) ;

	    AffineTransform at = AffineTransform.getTranslateInstance((neww-w)/2, (newh-h)/2);
	    at.rotate(radianAngle, w/2, h/2);
	    //---------------------------------------------------------------------

	    g.drawRenderedImage(image, at);
	    g.dispose();

	    return result;
	}
	
	//For AI
	public void doLogic() {
		//Check to see if dead
		if(getHealth() <= 0){
			if(getActionSequence() == 20){
			//TODO this should set the actionstep to 20 and action sequence to 0, but then it should never reset this again
				System.out.println(getClass().getSimpleName() + " is dead, will remove from game.");
				setActionSequence(20);
				setActionStep(0);
			}
		}
		
		//randomize direction 
		if(!(Game.getHero() == this)){
			if(getIsMoving() == false){
				setDirection((int) (45 * (Math.floor(((Math.random() * 360) / 45)))));
			}
		}
		
		if(getIsAttacking() & getCompleteingSequence() != true) {
			setInvokedAbility(1);							//records that the current ability being used is Q
			ability1Setup();
		}
		/*
		if(keySet.contains(KeyEvent.VK_W) & getCompleteingSequence() != true) {
			setInvokedAbility(2);							//records that the current ability being used is W
			ability2Setup();
		}
		if(keySet.contains(KeyEvent.VK_E) & getCompleteingSequence() != true) {
			setInvokedAbility(3);							//records that the current ability being used is E
			ability3Setup();
		}
		if(keySet.contains(KeyEvent.VK_R) & getCompleteingSequence() != true) {
			setInvokedAbility(4);							//records that the current ability being used is R
			ability4Setup();
		}
		*/
		
		if(getCompleteingSequence() == true) {				//prevents other actions while performing ability
			continueSequence();								//Moved to Sprite to generalize movement
			//Below stuff remains because sprites dont have execute ability methods
			if(getActionSequence() >= 0 & getActionSequence() <= 3) {						//spell-casting
				if(getActionStep() == 5) {
					executeAbility(getEffectiveDirection());
				}
			}
			else if(getActionSequence() >= 16 & getActionSequence() <= 19) {				//currently shooting
				if(getActionStep() == 9) {													//frame 9 matches arrow release
					executeAbility(getEffectiveDirection());
				}
			}
		}
		else
			setIsMoving(true);
		
		if(getMoveCasting() == true)
			setIsMoving(true);
		
		if (getDirection() == 315) {
			doMovementLogic45(9, 8, 315);
		}
		else if (getDirection() == 45) {
			doMovementLogic45(11, 8, 45);
		}
		else if (getDirection() == 0) {
			doMovementLogic90(8, 0);
		}
		else if (getDirection() == 225) {
			doMovementLogic45(10, 9, 225);
		}
		else if (getDirection() == 135) {
			doMovementLogic45(11, 10, 135);
		}
		else if (getDirection() == 180){
			doMovementLogic90(10, 180);
		}
		else if (getDirection() == 270) {
			doMovementLogic90(9, 270);
		}
		else if (getDirection() == 90) {
			doMovementLogic90(11, 90);
		}
		else {
			setIsMoving(false);
			if(getCompleteingSequence() == false) {
				if(getActionStep()>0)
					setActionStep(0);
			}
		}
	}
	
	public void paint(Graphics imageGraphics) {
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
	}
}