package marwolaeth;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public abstract class Hero extends Sprite{
	
	private boolean spaceBar = false;																							//while held, space prevents the hero from changing directional actionSequences. Note the direction is still changing.
	
	public Hero(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
	}
	
	public void doLogic(Set keySet) {
		if(keySet.contains(KeyEvent.VK_SPACE)) 
			spaceBar = true;
		else
			spaceBar = false;
		
		if(getActionSequence()==16 | getActionSequence()==17 | getActionSequence()==18 | getActionSequence()==19) {				//prevents other actions while performing ability
			setActionStep(getActionStep()+1);
			setIsMoving(false);
			if(getActionStep() == 9) {
				int actionSequenceToDirection = 0;
				switch(getActionSequence()) {
					case 16:
						actionSequenceToDirection = 0;
						break;
					case 17:
						actionSequenceToDirection = 270;
						break;
					case 18:
						actionSequenceToDirection = 180;
						break;
					case 19:
						actionSequenceToDirection = 90;
						break;
				}
				Arrow arrow = new Arrow(actionSequenceToDirection, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2);	//math gives the created object a reference to the center of the hero
				Game.addDrawable(arrow);
			}
			if(getActionStep()>12) {
				switch(getActionSequence()){																					//returns to idle in the correct direction
					case 16:
						setActionSequence(8);
						setActionStep(0);
						break;
					case 17:
						setActionSequence(9);
						setActionStep(0);
						break;
					case 18:
						setActionSequence(10);
						setActionStep(0);
						break;
					case 19:
						setActionSequence(11);
						setActionStep(0);
						break;
				}
			}

		}
		else if(keySet.contains(KeyEvent.VK_Q)) {
			setActionStep(0);
			if(this instanceof Archer)																				//this should check the class of the hero and assign an ActionSequence appropriate of the attack
				switch(getActionSequence()){																						
					case 8:
						setActionSequence(16);
						break;
					case 9:
						setActionSequence(17);
						break;
					case 10:
						setActionSequence(18);
						break;
					case 11:
						setActionSequence(19);
						break;
				}
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
	

}