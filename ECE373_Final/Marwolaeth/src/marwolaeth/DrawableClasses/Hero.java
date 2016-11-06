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
		if(keySet.contains(KeyEvent.VK_SPACE)){ 
			setStaticMovement(true);
		}
		else{
			setStaticMovement(false);
		}
		
		
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

}