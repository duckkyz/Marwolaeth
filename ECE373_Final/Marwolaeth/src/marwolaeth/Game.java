package marwolaeth;

import java.util.ArrayList;
import java.util.Set;

public class Game {

	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private Hero hero;
	
	public Game() {
		hero = new Wizard();
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public ArrayList<Drawable> getDrawables() {
		return drawables;
	}
	
	public void doGameLogic(Set keySet) {
		hero.doLogic(keySet);
		
		
		for(int x = 0;x < drawables.size();x++) {
			drawables.get(x).doLogic();
		}
		
	}

}
