package marwolaeth;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public abstract class Hero extends Sprite{
	
	public Hero() {
		// TODO Auto-generated constructor stub
	}
	
	public void doLogic(Set keySet) {
		//Movement logic
		if ((keySet.contains(KeyEvent.VK_W)) && (!keySet.contains(KeyEvent.VK_S))) {
			if ((keySet.contains(KeyEvent.VK_A)) && (!keySet.contains(KeyEvent.VK_D))) {
				setDirection(315);
				setIsMoving(true);
			}
			else if ((!keySet.contains(KeyEvent.VK_A)) && (keySet.contains(KeyEvent.VK_D))) { 
				setDirection(45);
				setIsMoving(true);
			}
			else {
				setDirection(0);
				setIsMoving(true);
			}
		}
		else if ((!keySet.contains(KeyEvent.VK_W)) && (keySet.contains(KeyEvent.VK_S))) {
			if ((keySet.contains(KeyEvent.VK_A)) && (!keySet.contains(KeyEvent.VK_D))) {
				setDirection(225);
				setIsMoving(true);
			}
			else if ((!keySet.contains(KeyEvent.VK_A)) && (keySet.contains(KeyEvent.VK_D))) {
				setDirection(135);
				setIsMoving(true);
			}
			else {
				setDirection(180);
				setIsMoving(true);
			}
		}
		else if ((keySet.contains(KeyEvent.VK_A)) && (!keySet.contains(KeyEvent.VK_D))) {
			setDirection(270);
			setIsMoving(true);
		}
		else if ((!keySet.contains(KeyEvent.VK_A)) && (keySet.contains(KeyEvent.VK_D))) {
			setDirection(90);
			setIsMoving(true);
		}
		else
			setIsMoving(false);
		
		//AttackLogic
		if (keySet.contains(KeyEvent.VK_U)) {

		}
		else if (keySet.contains(KeyEvent.VK_I)) {

		}
		else if (keySet.contains(KeyEvent.VK_O)) {

		}
		else if (keySet.contains(KeyEvent.VK_P)) {
			
		}
		else
			//setIsMoving(false);
		
		
		
		move();
		
		
	}
	

}