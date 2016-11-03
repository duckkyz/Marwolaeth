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
		
		//Testing walls
		//for(int i=0;i<10;++i){
		//	drawables.add(new Wall((2*250)-64,(i*64)));
		//	drawables.add(new Wall((i*64),(2*250)-64));
		//}
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
	
	public void stopUpMovement(){
		if(hero.getDirection() == 0){
			hero.setIsMoving(false);
		}	
		else if(hero.getDirection() == 45){
			hero.setDirection(90);
		}
		else if(hero.getDirection() == 315){
			hero.setDirection(270);
		}
	}

	public void stopDownMovement(){
		if(hero.getDirection() == 180){
			hero.setIsMoving(false);
		}	
		else if(hero.getDirection() == 135){
			hero.setDirection(90);
		}
		else if(hero.getDirection() == 225){
			hero.setDirection(270);
		}
	}
	
	public void stopLeftMovement(){
		if(hero.getDirection() == 270){
			hero.setIsMoving(false);
		}	
		else if(hero.getDirection() == 315){
			hero.setDirection(0);
		}
		else if(hero.getDirection() == 225){
			hero.setDirection(180);
		}
	}
	
	public void stopRightMovement(){
		if(hero.getDirection() == 90){
			hero.setIsMoving(false);
		}	
		else if(hero.getDirection() == 45){
			hero.setDirection(0);
		}
		else if(hero.getDirection() == 135){
			hero.setDirection(180);
		}
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
			Drawable d = drawables.get(x);
			d.doLogic();
		}
		
		int newX = (hero.getXPos() + (int)(Math.round(Math.sin(Math.toRadians(hero.getDirection())) * hero.getSpeed())));
		int newY = (hero.getYPos() + (int)(Math.round(Math.cos(Math.toRadians(hero.getDirection())) * hero.getSpeed() * (-1))));
		int newMaxX = newX + 64;	//Right
		int newMaxY = newY + 64;	//Bottom
		System.out.println("x: " + newX + " nX: " + newMaxX);
		System.out.println("y: " + newY + " nY: " + newMaxY);
		System.out.println();

		
		//Check for collision
		for(int x=0;drawables.size()>x;x++) {					//arrow can remove itself with this type of for loop without exploding the program
			Drawable d = drawables.get(x);
			//Collision types:
			//top: newMinX < (d.getXPos() + 32)
			//topLeft: (newMinX < (d.getXPos() + 32)) & (newMinY > 
			//
			//
			
			//TODO: Fix this shit, its kind of hard and then make it look pretty			
			
			//Implements top colision
			if((newY <= (d.getYPos() + 64)) & (newY > d.getYPos())){
				if((newX < d.getXPos()) & (newMaxX > d.getXPos())){
					stopUpMovement();
				}	
				else if((newX < (d.getXPos() + 64)) & (newMaxX > (d.getXPos() + 64))){
					stopUpMovement();
				}
				else if((newX == (d.getXPos())) & (newMaxX == (d.getXPos() + 64))){
					stopUpMovement();
				}
			}
			
			//Bot collision
			if((newMaxY >= d.getYPos()) & (newY < d.getYPos())){
				if((newX < d.getXPos()) & (newMaxX > d.getXPos())){
					stopDownMovement();
				}	
				else if((newX < (d.getXPos() + 64)) & (newMaxX > (d.getXPos() + 64))){
					stopDownMovement();
				}
				else if((newX == (d.getXPos())) & (newMaxX == (d.getXPos() + 64))){
					stopDownMovement();
				}
			}
			
			//Right Collision
			if((newMaxX >= d.getXPos()) & (newX < d.getXPos())){
				if((newY < d.getYPos()) & (newMaxY > d.getYPos())){
					stopRightMovement();
				}	
				else if((newY < (d.getYPos() + 64)) & (newMaxY > (d.getYPos() + 64))){
					stopRightMovement();
				}
				else if((newY == (d.getYPos())) & (newMaxY == (d.getYPos() + 64))){
					stopRightMovement();
				}
			}
			
			//Left Collision
			if((newX <= (d.getXPos() + 64)) & (newMaxX > (d.getXPos() + 64))){
				if((newY < d.getYPos()) & (newMaxY > d.getYPos())){
					stopLeftMovement();
				}	
				else if((newY < (d.getYPos() + 64)) & (newMaxY > (d.getYPos() + 64))){
					stopLeftMovement();
				}
				else if((newY == (d.getYPos())) & (newMaxY == (d.getYPos() + 64))){
					stopLeftMovement();
				}
			}			
		}
		
		
		//Move if not colliding
		hero.move();
		for(int x=0;drawables.size()>x;x++) {
			Drawable d = drawables.get(x);
			Sprite dummy = new Sprite(0, 0, 0);
			if(d.getClass() == dummy.getClass()){
				dummy = (Sprite) d;
				dummy.move();
			}
		}
		
		//Do attacks for everything
		hero.attack();
		for(int x=0;drawables.size()>x;x++) {
			Drawable d = drawables.get(x);
			Sprite dummy = new Sprite(0, 0, 0);
			if(d.getClass() == dummy.getClass()){
				dummy = (Sprite) d;
				dummy.attack();
			}
		}
	}
}
