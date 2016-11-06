package marwolaeth.DrawableClasses;

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
}
