package marwolaeth;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public abstract class Hero extends Sprite{
	
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
	
	public abstract void ability1_setup();
	public abstract void ability1_execute(int direction);
	
	public void doLogic(Set keySet) {
		if(keySet.contains(KeyEvent.VK_SPACE)) 
			spaceBar = true;
		else
			spaceBar = false;
		
		if(getCompleteingSequence() == true) {				//prevents other actions while performing ability
			continueSequence();
		}
		else if(keySet.contains(KeyEvent.VK_Q)) {
			setInvokedAbility(1);
			ability1_setup();
		}
		else if ((keySet.contains(KeyEvent.VK_UP)) && (!keySet.contains(KeyEvent.VK_DOWN))) {
			if ((keySet.contains(KeyEvent.VK_LEFT)) && (!keySet.contains(KeyEvent.VK_RIGHT))) {
				if(spaceBar == false) {
					if(getActionSequence()!=9 & getActionSequence()!=8)												//if both keys are pressed at the same time, choose one of the directions
						setActionSequence(8);
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
				setDirection(315);
				setIsMoving(true);
			}
			else if ((!keySet.contains(KeyEvent.VK_LEFT)) && (keySet.contains(KeyEvent.VK_RIGHT))) {
				if(spaceBar == false) {
					if(getActionSequence()!=11 & getActionSequence()!=8)												//if both keys are pressed at the same time, choose one of the directions
						setActionSequence(8);
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
				setDirection(45);
				setIsMoving(true);
			}
			else {
				if(spaceBar == false) {
					if(getActionSequence()==8) {
						if(getActionStep()<8)
							setActionStep(getActionStep()+1);
						else
							setActionStep(0);
					}
					else {
						setActionSequence(8);
						setActionStep(0);
					}
				}
				else {
					if(getActionStep()<=0)
						setActionStep(8);
					else
						setActionStep(getActionStep()-1);
				}
				setDirection(0);
				setIsMoving(true);
			}
		}
		else if ((!keySet.contains(KeyEvent.VK_UP)) && (keySet.contains(KeyEvent.VK_DOWN))) {
			if ((keySet.contains(KeyEvent.VK_LEFT)) && (!keySet.contains(KeyEvent.VK_RIGHT))) {
				if(spaceBar == false) {
					if(getActionSequence()!=10 & getActionSequence()!=9)												//if both keys are pressed at the same time, choose one of the directions
						setActionSequence(10);
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
				setDirection(225);
				setIsMoving(true);
			}
			else if ((!keySet.contains(KeyEvent.VK_LEFT)) && (keySet.contains(KeyEvent.VK_RIGHT))) {
				if(spaceBar == false) {
					if(getActionSequence()!=11 & getActionSequence()!=10)												//if both keys are pressed at the same time, choose one of the directions
						setActionSequence(10);
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
				setDirection(135);
				setIsMoving(true);
			}
			else {
				if(spaceBar == false) {
					if(getActionSequence()==10) {
						if(getActionStep()<8)
							setActionStep(getActionStep()+1);
						else
							setActionStep(0);
					}
					else {
						setActionSequence(10);
						setActionStep(0);
					}
				}
				else {
					if(getActionStep()<=0)
						setActionStep(8);
					else
						setActionStep(getActionStep()-1);
				}
				setDirection(180);
				setIsMoving(true);
			}
		}
		else if ((keySet.contains(KeyEvent.VK_LEFT)) && (!keySet.contains(KeyEvent.VK_RIGHT))) {
			if(spaceBar == false) {
				if(getActionSequence()==9) {
					if(getActionStep()<8)
						setActionStep(getActionStep()+1);
					else
						setActionStep(0);
				}
				else {
					setActionSequence(9);
					setActionStep(0);
				}
			}
			else {
				if(getActionStep()<=0)
					setActionStep(8);
				else
					setActionStep(getActionStep()-1);
			}
			setDirection(270);
			setIsMoving(true);
		}
		else if ((!keySet.contains(KeyEvent.VK_LEFT)) && (keySet.contains(KeyEvent.VK_RIGHT))) {
			if(spaceBar == false) {
				if(getActionSequence()==11) {
					if(getActionStep()<8)
						setActionStep(getActionStep()+1);
					else
						setActionStep(0);
				}
				else {
					setActionSequence(11);
					setActionStep(0);
				}
			}
			else {
				if(getActionStep()<=0)
					setActionStep(8);
				else
					setActionStep(getActionStep()-1);
			}
			setDirection(90);
			setIsMoving(true);
		}
		else {
			setIsMoving(false);
			if(getActionStep()>0)
				setActionStep(0);
		}
		
	}
	
	public void continueSequence() {
		setActionStep(getActionStep()+1);										//goes to the next step of the animation
		
		if(getActionSequence() >= 16 & getActionSequence() <= 19) {				//currently shooting
			setIsMoving(false);
			if(getActionStep()>12) {
				switch(getActionSequence()){									//Sequence ends, returns to idle in the correct direction
					case 16:
						setActionSequence(8);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 17:
						setActionSequence(9);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 18:
						setActionSequence(10);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 19:
						setActionSequence(11);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
				}
			}
			if(getActionStep() == 9) {											//frame 9 matches arrow release
				if(getInvokedAbility()==1)
					ability1_execute(getEffectiveDirection());
				/*																//add in ability executes as number of abilities increases
				else if(getInvokedAbility()==2)
					ability2_execute(actionSequenceToDirection);
					*/
			}
		}
		
		else if(getActionSequence() >= 0 & getActionSequence() <= 3) {					//spell-casting
			setIsMoving(false);
			if(getActionStep()>6) {
				switch(getActionSequence()){											//Sequence ends, returns to idle in the correct direction
					case 0:
						setActionSequence(8);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 1:
						setActionSequence(9);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 2:
						setActionSequence(10);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 3:
						setActionSequence(11);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
				}
			}
			if(getActionStep() == 5) {											//frame 9 matches arrow release
				if(getInvokedAbility()==1)
					ability1_execute(getEffectiveDirection());
				/*																//add in ability executes as number of abilities increases
				else if(getInvokedAbility()==2)
					ability2_execute(actionSequenceToDirection);
					*/
			}
		}
		
		else if(getActionSequence() >= 4 & getActionSequence() <= 7) {					//thrusting 
			if(getActionStep()>7) {
				switch(getActionSequence()){											//Sequence ends, returns to idle in the correct direction
					case 4:
						setActionSequence(8);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 5:
						setActionSequence(9);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 6:
						setActionSequence(10);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 7:
						setActionSequence(11);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
				}
			}
		}
		
		else if(getActionSequence() >= 12 & getActionSequence() <= 15) {				//slash
			if(getActionStep()>7) {
				switch(getActionSequence()){											//Sequence ends, returns to idle in the correct direction
					case 12:
						setActionSequence(8);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 13:
						setActionSequence(9);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 14:
						setActionSequence(10);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
					case 15:
						setActionSequence(11);
						setActionStep(0);
						setCompleteingSequence(false);
						break;
				}
			}
		}
		
		else if(getActionSequence() == 20) {				//death
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
	

}