package marwolaeth;

import java.util.ArrayList;
import java.util.Set;

public class Game {

	private ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private static Hero hero;
	
	public Game() {
		
		//This makes the walls around the edge
		for(int i=0;i<35;++i){
			drawables.add(new Wall(32,(i*64) + 32));
		}
		for(int i=0;i<35;++i){
			drawables.add(new Wall(1920-32,(i*64) + 32));
		}
	}
	
	public static Hero getHero() {
		return hero;
	}
	
	public ArrayList<Drawable> getDrawables() {
		return drawables;
	}
	
	public static void setHero(Hero hero) {
		Game.hero = hero;
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
		
		//Set up movements
		hero.doLogic(keySet);
		for(Drawable d : drawables) {
			d.doLogic();
		}
		
		int newX = hero.getXPos();
		int newY = hero.getYPos();
		
		switch(hero.getDirection()){
		case 0:		//Moving up
			newY -= 1;
			break;
		case 45:	//Moving up right
			newX += ((int)Math.floor(hero.getSpeed()/1.414));
			newY -= ((int)Math.floor(hero.getSpeed()/1.414));
			break;
		case 90:	//Moving right
			newX += 1;
			break;
		case 135:	//Moving down right
			newX += ((int)Math.floor(hero.getSpeed()/1.414));
			newY += ((int)Math.floor(hero.getSpeed()/1.414));
			break;
		case 180:	//Moving down
			newY += 1;
			break;
		case 225:	//Moving down left
			newX -= ((int)Math.floor(hero.getSpeed()/1.414));
			newY += ((int)Math.floor(hero.getSpeed()/1.414));
			break;
		case 270:	//Moving left
			newX -= 1;
			break;
		case 315:	//Moving up left
			newX -= ((int)Math.floor(hero.getSpeed()/1.414));
			newY -= ((int)Math.floor(hero.getSpeed()/1.414));
			break;	
		}
		int newMinX = newX - 32;	//Left
		int newMaxX = newX + 32;	//Right
		int newMinY = newY - 32;	//Top
		int newMaxY = newY + 32;	//Bottom
		
		//Check for collision
		for(Drawable d : drawables) {
			//Collision types:
			//top: newMinX < (d.getXPos() + 32)
			//topLeft: (newMinX < (d.getXPos() + 32)) & (newMinY > 
			//
			//
			
			switch(hero.getDirection()){
			case 0:		//Moving up
				//TODO: Fix this shit, its kind of hard and then make it look pretty
				if(newMinY < (d.getYPos() + 32) & ((newMinX < (d.getYPos() - 32)) | (newMaxX > (d.getYPos() + 32)))){
					hero.setIsMoving(false);
				}
				break;
			case 45:	//Moving up right
				break;
			case 90:	//Moving right
				break;
			case 135:	//Moving down right
				break;
			case 180:	//Moving down
				break;
			case 225:	//Moving down left
				break;
			case 270:	//Moving left
				break;
			case 315:	//Moving up left
				break;	
			}
			if(newMinX < (d.getXPos() + 32) & (newMinY == d.getYPos())){
				hero.setIsMoving(false);
			}
		}
		
		
		//Move if not colliding
		hero.move();
		for(Drawable d : drawables) {
			Sprite dummy = new Sprite();
			if(d.getClass() == dummy.getClass()){
				dummy = (Sprite) d;
				dummy.move();
			}
		}
		
		//Do attacks for everything
		
	}

}
