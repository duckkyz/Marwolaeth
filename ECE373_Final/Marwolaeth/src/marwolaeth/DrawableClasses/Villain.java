package marwolaeth.DrawableClasses;

import java.awt.event.KeyEvent;

import marwolaeth.Interfaces.willAttack;

public abstract class Villain extends Sprite  implements willAttack{

	public Villain(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
	}

	public abstract void ability1Setup();
	
	public abstract void ability2Setup();
	
	public abstract void ability3Setup();
	
	public abstract void ability4Setup();
	
	public abstract void ability1Execute(int direction);

	public abstract void ability2Execute(int direction);

	public abstract void ability3Execute(int direction);
	
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
}
