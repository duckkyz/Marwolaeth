package marwolaeth;

import java.util.ArrayList;
import java.util.Set;

import marwolaeth.DrawableClasses.Drawable;
import marwolaeth.DrawableClasses.Hero;
import marwolaeth.DrawableClasses.Sprite;
import marwolaeth.ImplementedEntities.Wall;

public class Game {

	private static ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private static Hero hero;
	
	public Game() {
		
		//This makes the walls around the edge.
		for(int i=0;i<35;++i){
			drawables.add(new Wall(0,(i*64)));
			drawables.add(new Wall((2*1920)-64,(i*64)));
		}
		for(int i=0; i<60; ++i){
			drawables.add(new Wall((i*64),0));
			drawables.add(new Wall((i*64),(2*1080)-64));
		}
	}
	
	public static Hero getHero() {
		return hero;
	}
	
	public ArrayList<Drawable> getDrawables() {
		return drawables;
	}
	
	public static void setHero(Hero newHero) {
		hero = newHero;
	}
	
	public static void addDrawable(Drawable drawable) {
		drawables.add(drawable);
	}
	
	public static void removeDrawable(Drawable drawable) {
		drawables.remove(drawable);
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
		for(int x=0;drawables.size()>x;x++) {					//arrow can remove itself with this type of for loop without exploding the program
			drawables.get(x).doLogic();
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
		int newMinX = newX;	//Left
		int newMaxX = newX + 64;	//Right
		int newMinY = newY;	//Top
		int newMaxY = newY + 64;	//Bottom
		
		//Check for collision
		for(int x=0;drawables.size()>x;x++) {					//arrow can remove itself with this type of for loop without exploding the program
			drawables.get(x).doLogic();						//temp added for arrowmovement. needs replaced
			//Collision types:
			//top: newMinX < (d.getXPos() + 32)
			//topLeft: (newMinX < (d.getXPos() + 32)) & (newMinY > 
			//
			//
			
			switch(hero.getDirection()){
			case 0:		//Moving up
				//TODO: Fix this shit, its kind of hard and then make it look pretty
				if(newMinY == (drawables.get(x).getYPos() + 64) & ((newMinX == drawables.get(x).getXPos()) & (newMaxX == (drawables.get(x).getXPos() + 64)))){
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
		}
		
		
		//Move if not colliding
		hero.move();
		for(int x=0;drawables.size()>x;x++) {
			Sprite dummy = new Sprite(0, 0, 0);
			if(drawables.get(x).getClass() == dummy.getClass()){
				dummy = (Sprite) drawables.get(x);
				dummy.move();
			}
		}
		
		//Do attacks for everything
		
	}

}
