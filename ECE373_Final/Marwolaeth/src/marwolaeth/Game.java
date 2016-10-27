package marwolaeth;

import java.util.ArrayList;
import java.util.Set;

public class Game {

	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private Hero hero;
	
	public Game() {
		hero = new Wizard(200,200);
		
		//This makes the walls around the edge
		for(int i=0;i<35;++i){
			drawables.add(new Wall(32,(i*64) + 32));
		}
		for(int i=0;i<35;++i){
			drawables.add(new Wall(1920-32,(i*64) + 32));
		}
	}
	
	public Hero getHero() {
		return hero;
	}
	
	public ArrayList<Drawable> getDrawables() {
		return drawables;
	}
	
	public void doGameLogic(Set keySet) {
		//Check for colisions
		//ArrayList<Drawable> tempList = new ArrayList<Drawable>();
		//for(Drawable entity : this.getDrawables()){

		//}
		
		
		/*
		 * Colision is when the new coord will equal another sprites current location (image wise)
		 * So it should call each sprite to draw, if the new coords will result in colision 
		 * change the coords to their original placement
		 * temps:
		 * 	xpos, ypos
		 * 
		 * Sequence:
		 * 		1. Move to new coords
		 * 		2. Check to see if this matches any other objects coords
		 * 		3. if true : set to old coords
		 * 			  else : leave it and move on to next object
		 * 
		 */
		
		//Do movements for things that arent colliding
		hero.doLogic(keySet);
		for(int x = 0;x < drawables.size();x++) {
			drawables.get(x).doLogic();
		}
		
		//Do attacks for everything
		
	}

}
