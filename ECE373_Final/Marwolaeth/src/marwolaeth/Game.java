package marwolaeth;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import marwolaeth.DrawableClasses.*;
import marwolaeth.FloorTiles.GrassTile;
import marwolaeth.ImplementedEntities.*;

public class Game {
	private static ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private static Hero hero;
	private final int mapHeight = 2*1080;
	private final int mapWidth = 2*1920;
	
	public Game() {
		//Removes all things from game so it starts fresh
		drawables.removeAll(drawables);

		//This makes the walls around the edge.
		for(int i=0;i<35;++i){
			drawables.add(new Wall(0,(i*64)));
			drawables.add(new Wall((mapWidth)-64,(i*64)));
		}
		//for(int i=0; i<60; ++i){
		for(int i=60; i>0; --i){
			drawables.add(new Wall((i*64),0));
			drawables.add(new Wall((i*64),(mapHeight)-64));
		}
		
		//TODO: things shouldnt spawn on top of already existing things 
		//Testing walls
		for(int j = 0; j < 10; ++j){
			for(int i = 0; i < 10; ++i){
				drawables.add(new Wall(((int)(Math.floor((Math.random() * mapWidth)/64) * 64)),((int)(Math.floor((Math.random() * mapWidth)/64) * 64))));
			}
		}
		drawables.add(new Wall(186,186));
		drawables.add(new Wall(186,314));
		drawables.add(new Wall(314,186));
		drawables.add(new Wall(314,314));
		for(int i = 0; i < 30; ++i){
			int orcDirection = (int) (45 * (Math.floor(((Math.random() * 360) / 45))));
			int orcXPos = (int)(Math.floor((Math.random() * mapWidth)/64) * 64);
			int orcYPos = (int)(Math.floor((Math.random() * mapWidth)/64) * 64);
			if(i%3 == 0){
				drawables.add(new Orc(orcDirection, orcXPos, orcYPos));
			}
			else if(i%3 == 1){
				drawables.add(new Wizard(orcDirection, orcXPos, orcYPos));
			}
			else{
				drawables.add(new Archer(orcDirection, orcXPos, orcYPos));
			}
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
		
	public boolean projectileHandling(Sprite movingS, Drawable d){
		if(movingS instanceof Projectile){
			if(d instanceof Wall){
				drawables.remove(movingS);
			}
			else{
				return true;
			}
		}
		else if(d instanceof Projectile){
			System.out.println(movingS.getClass().getSimpleName() + " was hit by a projectile");
			Projectile p = (Projectile) d;
			if(p.getIsFromHero() & (!(movingS == hero))){
				if(d instanceof Sprite){
					p.attack(movingS);
				}
				drawables.remove(d);
			}
			else if(!(p.getIsFromHero()) & (movingS == hero)){
				drawables.remove(d);
			}
			return true;
		}
		return false;
		//end TODO
	}
	
	public void checkForSpriteCollision(ArrayList<Drawable> drawables, Hero hero){
		Collections.sort(drawables, Drawable.PosComparator);				//Sorts drawables so there is no need for spawn order collision casing
		Drawable movingD;													
		
		for(int y = 0; (drawables.size() + 1) > y; y++){					//Iterates through all drawables and hero
			if(y == drawables.size()){										//Un-intrusively inserts hero into check
				movingD = hero;
			}
			else{
				movingD = drawables.get(y);
			}
			
			if(!(movingD instanceof Sprite)){								//If this drawable is not a sprite, it cant move so no need to check if its colliding
				continue;
			}
			
			Sprite movingS = (Sprite) movingD;								//If its a sprite, lets cast it to sprite
			
			//Calculates new position for collision checking
			int newX = (movingS.getXPos() + (int)(Math.round(Math.sin(Math.toRadians(movingS.getDirection())) * movingS.getSpeed())));
			int newY = (movingS.getYPos() + (int)(Math.round(Math.cos(Math.toRadians(movingS.getDirection())) * movingS.getSpeed() * (-1))));
			int newMaxX = newX + 64;	//Right
			int newMaxY = newY + 64;	//Bottom
			
			//Initializes the movement booleans
			boolean canGoUp = true;
			boolean canGoDown = true;
			boolean canGoLeft = true;
			boolean canGoRight = true;
			
			//Used for single collision checking
			int collisionX = 0;
			int collisionY = 0;
			int collision1Count = 0;
			int collision2Count = 0;
			int collision3Count = 0;
			int collision4Count = 0;
			
			//To save need of creating new d every iteration
			Drawable d;
			
			//Iterates through the list of drawables again, this time checking if movingS is hitting this drawable
			for(int x = 0; (drawables.size() + 1) > x; x++){
				if(x == (drawables.size())){					//Insert hero
					d = hero;
				}
				else{
					d = drawables.get(x);
				}
				if(d == movingS){								//Skip yourself because obvious
					continue;
				}
				
				if((newX >= d.getXPos()) & (newX <= (d.getXPos() + 64))){ 			//Collision from the left
					if((newY >= d.getYPos()) & (newY <= (d.getYPos() + 64))){		//Collision from the bottom
						//Projectile handling happens here, if it should continue it will
						if(projectileHandling(movingS, d)){
							continue;
						}
						
						++collision1Count;											//Used for single collision 
						collisionX = d.getXPos() + 64;
						collisionY = d.getYPos() + 64;
						
						if(movingS.getDirection() == 0){
							canGoUp = false;
						}
						
						else if(movingS.getDirection() == 45){
							canGoUp = false;
						}
						
						else if(movingS.getDirection() == 225){
							canGoLeft = false;
						}
						
						else if(movingS.getDirection() == 270){
							canGoLeft = false;
						}
					}
					
					else if((newMaxY >= (d.getYPos())) & (newMaxY <= (d.getYPos() + 64))){	//Collision from top
						//Projectile handling happens here, if it should continue it will
						if(projectileHandling(movingS, d)){
							continue;
						}
						
						++collision2Count;													//Single Collision Detection
						collisionX = d.getXPos() + 64;
						collisionY = d.getYPos();
						
						if(movingS.getDirection() == 135){
							canGoDown = false;
						}
						
						else if(movingS.getDirection() == 270){
							canGoLeft = false;
						}
						
						else if(movingS.getDirection() == 315){
							canGoLeft = false;
						}	
						
						else if(movingS.getDirection() == 180){
							canGoDown = false;
						}
					}
				}
				else if ((newMaxX >= d.getXPos()) & (newMaxX <= (d.getXPos() + 64))){	//Collision from the right
					if((newY >= d.getYPos()) & (newY <= (d.getYPos() + 64))){
						//Projectile handling happens here, if it should continue it will
						if(projectileHandling(movingS, d)){
							continue;
						}

						++collision3Count;												//Single Collision detection
						collisionX = d.getXPos();
						collisionY = d.getYPos() + 64;
						
						if(movingS.getDirection() == 0){
							canGoUp = false;
						}
						
						else if(movingS.getDirection() == 90){
							canGoRight = false;
						}
						
						else if(movingS.getDirection() == 135){
							canGoRight = false;
						}
						
						else if(movingS.getDirection() == 315){
							canGoUp = false;
						}
					}
					else if((newMaxY >= (d.getYPos())) & (newMaxY <= (d.getYPos() + 64))){
						//Projectile handling happens here, if it should continue it will
						if(projectileHandling(movingS, d)){
							continue;
						}
						
						++collision4Count;													//Single collision detection
						collisionX = d.getXPos();
						collisionY = d.getYPos();
						
						if(movingS.getDirection() == 45){
							canGoRight = false;
						}
						
						else if(movingS.getDirection() == 90){
							canGoRight = false;
						}
						
						else if(movingS.getDirection() == 180){
							canGoDown = false;
						}
						
						else if(movingS.getDirection() == 225){
							canGoDown = false;
						}
					}
				}
			}
			
			/*** This is the single collision detection section ***/
			if((collision1Count + collision2Count + collision3Count + collision4Count) == 1){
				if(collision1Count == 1){
					if((movingS.getDirection() == 0) | (movingS.getDirection() == 45)){
						canGoUp = false;
					}
					else if((movingS.getDirection() == 270) | (movingS.getDirection() == 225)){
						canGoLeft = false;
					}
					else if(movingS.getDirection() == 315){
						if((collisionX - newX) < (collisionY - newY)){
							canGoLeft = false;
						}
						else if((collisionX - newX) > (collisionY - newY)){
							canGoUp = false;
						}
						else{
							canGoUp = false;
							canGoLeft = false;
						}
					}
				}
				else if(collision2Count == 1){
					if((movingS.getDirection() == 135) | (movingS.getDirection() == 180)){
						canGoDown = false;
					}
					else if((movingS.getDirection() == 270) | (movingS.getDirection() == 315)){
						canGoLeft = false;
					}
					else if(movingS.getDirection() == 225){
						if((collisionX - newX) < (newMaxY - collisionY)){
							canGoLeft = false;
						}
						else if((collisionX - newX) > (newMaxY - collisionY)){
							canGoDown = false;
						}
						else{
							canGoDown = false;
							canGoLeft = false;
						}
					}
				}
				else if(collision3Count == 1){
					if((movingS.getDirection() == 0) | (movingS.getDirection() == 315)){
						canGoUp = false;
					}
					else if((movingS.getDirection() == 90) | (movingS.getDirection() == 135)){
						canGoRight = false;
					}
					else if(hero.getDirection() == 45){					
						if((newMaxX - collisionX) < (collisionY - newY)){
							canGoRight = false;
						}
						else if((newMaxX - collisionX) > (collisionY - newY)){
							canGoUp = false;					
						}
						else{
							canGoUp = false;
							canGoRight = false;
						}
					}
				}
				else if(collision4Count == 1){
					if((movingS.getDirection() == 180) | (movingS.getDirection() == 225)){
						canGoDown = false;
					}
					else if((movingS.getDirection() == 45) | (movingS.getDirection() == 90)){
						canGoRight = false;
					}
					else if(hero.getDirection() == 135){
						if((newMaxX - collisionX) < (newMaxY - collisionY)){
							canGoRight = false;
						}
						else if((newMaxX - collisionX) > (newMaxY - collisionY)){
							canGoDown = false;
						}
						else{
							canGoDown = false;
							canGoRight = false;
						}
					}
				}
			}
			
			/*** Actual collision handling cases ***/
			collisionHandling(movingS, canGoUp, canGoDown, canGoLeft, canGoRight);
		}
	}

	public void collisionHandling(Sprite movingS, boolean canGoUp, boolean canGoDown, boolean canGoLeft, boolean canGoRight){
		if(movingS.getDirection() == 0){
			if(canGoUp == false){
				movingS.setIsMoving(false);
			}
		}

		else if(movingS.getDirection() == 45){
			if((canGoUp == false) & (canGoRight == false)){
				movingS.setIsMoving(false);
			}
			else if(canGoUp == false){
				movingS.setDirection(90);
			}
			else if(canGoRight == false){
				movingS.setDirection(0);
			}
		}
		
		else if(movingS.getDirection() == 90){
			if(canGoRight == false){
				movingS.setIsMoving(false);
			}
		}
		
		else if(movingS.getDirection() == 135){
			if((canGoDown == false) & (canGoRight == false)){
				movingS.setIsMoving(false);
			}
			else if(canGoDown == false){
				movingS.setDirection(90);
			}
			else if(canGoRight == false){
				movingS.setDirection(180);
			}
		}
		
		else if(movingS.getDirection() == 180){
			if(canGoDown == false){
				movingS.setIsMoving(false);
			}
		}
		
		else if(movingS.getDirection() == 225){
			if((canGoDown == false) & (canGoLeft == false)){
				movingS.setIsMoving(false);
			}
			else if(canGoDown == false){
				movingS.setDirection(270);
			}
			else if(canGoLeft == false){
				movingS.setDirection(180);
			}
		}
		
		else if(movingS.getDirection() == 270){
			if(canGoLeft == false){
				movingS.setIsMoving(false);
			}
		}
		
		else if(movingS.getDirection() == 315){
			if((canGoUp == false) & (canGoLeft == false)){
				movingS.setIsMoving(false);
			}
			else if(canGoUp == false){
				movingS.setDirection(270);
			}
			else if(canGoLeft == false){
				movingS.setDirection(0);
			}
		}
	}
	
	public void checkForDeadSprites(){
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			if(d instanceof Sprite){
				Sprite s = (Sprite) d;
				if(s.getHealth() < 0){
					//TODO make sprites do their death animation and then remove themselves
					System.out.println(s.getClass().getSimpleName() + " is dead, will remove from game.");
					drawables.remove(s);
				}
			}
		}		
	}
	
	public void checkForOutsideMap(){
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			if(d.getXPos() > mapWidth){
				System.out.println("Removing " + d.toString());
				removeDrawable(d);
			}
			else if(d.getXPos() < 0){
				System.out.println("Removing " + d.toString());
				removeDrawable(d);
			}
			else if(d.getYPos() > mapHeight){
				System.out.println("Removing " + d.toString());
				removeDrawable(d);
			}
			else if(d.getYPos() < 0) {
				System.out.println("Removing " + d.toString());
				removeDrawable(d);
			}
		}
	}
	
	public void setUpMovements(Set keySet){
		hero.doLogic(keySet);
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			d.doLogic();
		}
	}
	
	public void moveDrawables(){
		hero.move();
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			d.move();
		}
	}
	
	public void executeAttacks(){
		hero.attack();
		for(int x = 0; drawables.size() > x; x++){
			if(!(drawables.get(x) instanceof Sprite)){
				continue;
			}
			Sprite attackingS = (Sprite)drawables.get(x);
			attackingS.attack();
		}
	}
	
	public void doGameLogic(Set keySet) {
		//Check to see if things are dead, if so remove them
		checkForDeadSprites();
		
		//Check to see if things are outside the map, if so remove them
		checkForOutsideMap();
		
		//Set up movements
		setUpMovements(keySet);
		
		//Check for collision
		checkForSpriteCollision(drawables, hero);
		
		//Move if can
		moveDrawables();
		
		//Do attacks for everything
		executeAttacks();
	}
}
