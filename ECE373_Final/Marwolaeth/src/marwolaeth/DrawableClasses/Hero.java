package marwolaeth.DrawableClasses;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import marwolaeth.Interfaces.willAttack;

public abstract class Hero extends Sprite implements willAttack{
	
	private boolean spaceBar = false;																							//while held, space prevents the hero from changing directional actionSequences. Note the direction is still changing.
	
	public Hero(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		switch(direction) {									//draws the hero in the direction it was created in
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
	
	public abstract void ability1Setup();
	
	public abstract void ability1Execute(int direction);
	
	public abstract void ability2Setup();
	
	public abstract void ability2Execute(int direction);
	
	public abstract void ability3Setup();
	
	public abstract void ability3Execute(int direction);
	
	public abstract void ability4Setup();
	
	public abstract void ability4Execute(int direction);
	
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
	
	public void doLogic(Set keySet) {
		if(keySet.contains(KeyEvent.VK_SPACE)) 
			spaceBar = true;
		else
			spaceBar = false;
		
		
		if(keySet.contains(KeyEvent.VK_Q) & getCompleteingSequence() != true) {
			setInvokedAbility(1);							//records that the current ability being used is Q
			ability1Setup();
		}
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
		
		
		if(getCompleteingSequence() == true) {				//prevents other actions while performing ability
			continueSequence();
		}
		else
			setIsMoving(true);
		
		if(getMoveCasting() == true)
			setIsMoving(true);
		
		
		if ((keySet.contains(KeyEvent.VK_UP)) && (!keySet.contains(KeyEvent.VK_DOWN))) {
			if ((keySet.contains(KeyEvent.VK_LEFT)) && (!keySet.contains(KeyEvent.VK_RIGHT))) {
				doMovementLogic45(9, 8, 315);
			}
			else if ((!keySet.contains(KeyEvent.VK_LEFT)) && (keySet.contains(KeyEvent.VK_RIGHT))) {
				doMovementLogic45(11, 8, 45);
			}
			else {
				doMovementLogic90(8, 0);
			}
		}
		else if ((!keySet.contains(KeyEvent.VK_UP)) && (keySet.contains(KeyEvent.VK_DOWN))) {
			if ((keySet.contains(KeyEvent.VK_LEFT)) && (!keySet.contains(KeyEvent.VK_RIGHT))) {
				doMovementLogic45(10, 9, 225);
			}
			else if ((!keySet.contains(KeyEvent.VK_LEFT)) && (keySet.contains(KeyEvent.VK_RIGHT))) {
				doMovementLogic45(11, 10, 135);
				
			}
			else {
				doMovementLogic90(10, 180);
			}
		}
		else if ((keySet.contains(KeyEvent.VK_LEFT)) && (!keySet.contains(KeyEvent.VK_RIGHT))) {
			doMovementLogic90(9, 270);
		}
		else if ((!keySet.contains(KeyEvent.VK_LEFT)) && (keySet.contains(KeyEvent.VK_RIGHT))) {
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
			if(spaceBar == false) {
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
			if(spaceBar == false) {
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
	
	//public void paint(Graphics imageGraphics) {
	//	imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
	//}

}