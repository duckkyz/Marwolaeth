package marwolaeth.DrawableClasses;

import marwolaeth.Interfaces.willAttack;

public abstract class Villain extends Sprite  implements willAttack{

	public Villain(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		switch(direction) {									//draws the villian in the direction it was created in
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
	
	public abstract void ability2Setup();
	
	public abstract void ability3Setup();
	
	public abstract void ability4Setup();
	
	public abstract void ability1Execute(int direction);

	public abstract void ability2Execute(int direction);

	public abstract void ability3Execute(int direction);
	
	public abstract void ability4Execute(int direction);
}
