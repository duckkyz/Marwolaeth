package marwolaeth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;

import marwolaeth.DrawableClasses.*;
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
		
		drawables.add(new Orc(180,250,250));
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
	
	public void checkForHeroCollision(ArrayList<Drawable> drawables, Hero hero){
		Collections.sort(drawables, Drawable.PosComparator);
		
		
		int newX = (hero.getXPos() + (int)(Math.round(Math.sin(Math.toRadians(hero.getDirection())) * hero.getSpeed())));
		int newY = (hero.getYPos() + (int)(Math.round(Math.cos(Math.toRadians(hero.getDirection())) * hero.getSpeed() * (-1))));
		int newMaxX = newX + 64;	//Right
		int newMaxY = newY + 64;	//Bottom
		boolean canGoUp = true;
		boolean canGoDown = true;
		boolean canGoLeft = true;
		boolean canGoRight = true;
		int collisionX = 0;
		int collisionY = 0;
		
		//TODO: fix this for single collision
		System.out.println("New cycle");
		int collision1Count = 0;
		int collision2Count = 0;
		int collision3Count = 0;
		int collision4Count = 0;
		for(int x=0;drawables.size()>x;x++){
			Drawable d = drawables.get(x);
			
			if((newX >= d.getXPos()) & (newX <= (d.getXPos() + 64))){ 		//Collision from the left
				if((newY >= d.getYPos()) & (newY <= (d.getYPos() + 64))){	//Collision from the bottom
					System.out.println("Collision 1 for " + d.getXPos() + "," + d.getYPos());
					//TODO this should be generalzied some how
					if(d instanceof Projectile){
						System.out.println("I hit a projectile");
						Projectile p = (Projectile) d;
						if(!p.getIsFromHero()){
							drawables.remove(d);
						}
						continue;
					}
					//end TODO
					++collision1Count;
					collisionX = d.getXPos() + 64;
					collisionY = d.getYPos() + 64;
					if(hero.getDirection() == 0){
						canGoUp = false;
					}
					else if(hero.getDirection() == 45){
						canGoUp = false;
					}
					else if(hero.getDirection() == 225){
						canGoLeft = false;
					}
					else if(hero.getDirection() == 270){
						canGoLeft = false;
					}
				}
				else if((newMaxY >= (d.getYPos())) & (newMaxY <= (d.getYPos() + 64))){	//Collision from top
					System.out.println("Collision 2 for " + d.getXPos() + "," + d.getYPos());
					//TODO this should be generalzied some how
					if(d instanceof Projectile){
						System.out.println("I hit a projectile");
						Projectile p = (Projectile) d;
						if(!p.getIsFromHero()){
							drawables.remove(d);
						}
						continue;
					}
					//end TODO
					++collision2Count;
					collisionX = d.getXPos() + 64;
					collisionY = d.getYPos();
					if(hero.getDirection() == 135){
						canGoDown = false;
					}
					else if(hero.getDirection() == 270){
						canGoLeft = false;
					}
					else if(hero.getDirection() == 315){
						canGoLeft = false;
					}	
					else if(hero.getDirection() == 180){
						canGoDown = false;
					}
				}
			}
			else if ((newMaxX >= d.getXPos()) & (newMaxX <= (d.getXPos() + 64))){	//Collision from the right
				if((newY >= d.getYPos()) & (newY <= (d.getYPos() + 64))){
					System.out.println("Collision 3 for " + d.getXPos() + "," + d.getYPos());
					//TODO this should be generalzied some how
					if(d instanceof Projectile){
						System.out.println("I hit a projectile");
						Projectile p = (Projectile) d;
						if(!p.getIsFromHero()){
							drawables.remove(d);
						}
						continue;
					}
					//end TODO
					++collision3Count;
					collisionX = d.getXPos();
					collisionY = d.getYPos() + 64;
					if(hero.getDirection() == 0){
						canGoUp = false;
					}
					else if(hero.getDirection() == 90){
						canGoRight = false;
					}
					else if(hero.getDirection() == 135){
						canGoRight = false;
					}
					else if(hero.getDirection() == 315){
						canGoUp = false;
					}
				}
				else if((newMaxY >= (d.getYPos())) & (newMaxY <= (d.getYPos() + 64))){
					System.out.println("Collision 4 for " + d.getXPos() + "," + d.getYPos());
					//TODO this should be generalzied some how
					if(d instanceof Projectile){
						System.out.println("I hit a projectile");
						Projectile p = (Projectile) d;
						if(!p.getIsFromHero()){
							drawables.remove(d);
						}
						continue;
					}
					//end TODO
					++collision4Count;
					collisionX = d.getXPos();
					collisionY = d.getYPos();
					if(hero.getDirection() == 45){
						canGoRight = false;
					}
					else if(hero.getDirection() == 90){
						canGoRight = false;
					}
					else if(hero.getDirection() == 180){
						canGoDown = false;
					}
					else if(hero.getDirection() == 225){
						canGoDown = false;
					}
				}
			}
		}
		
		if((collision1Count + collision2Count + collision3Count + collision4Count) == 1){
			if(collision1Count == 1){
				if((hero.getDirection() == 0) | (hero.getDirection() == 45)){
					canGoUp = false;
				}
				else if((hero.getDirection() == 270) | (hero.getDirection() == 225)){
					canGoLeft = false;
				}
				else if(hero.getDirection() == 315){
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
				if((hero.getDirection() == 135) | (hero.getDirection() == 180)){
					canGoDown = false;
				}
				else if((hero.getDirection() == 270) | (hero.getDirection() == 315)){
					canGoLeft = false;
				}
				else if(hero.getDirection() == 225){
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
				if((hero.getDirection() == 0) | (hero.getDirection() == 315)){
					canGoUp = false;
				}
				else if((hero.getDirection() == 90) | (hero.getDirection() == 135)){
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
				if((hero.getDirection() == 180) | (hero.getDirection() == 225)){
					canGoDown = false;
				}
				else if((hero.getDirection() == 45) | (hero.getDirection() == 90)){
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
		
		
		//TODO: take this into separate method
		if(hero.getDirection() == 0){
			if(canGoUp == false){
				hero.setIsMoving(false);
			}
		}

		else if(hero.getDirection() == 45){
			if((canGoUp == false) & (canGoRight == false)){
				hero.setIsMoving(false);
			}
			else if(canGoUp == false){
				hero.setDirection(90);
			}
			else if(canGoRight == false){
				hero.setDirection(0);
			}
		}
		
		else if(hero.getDirection() == 90){
			if(canGoRight == false){
				hero.setIsMoving(false);
			}
		}
		
		else if(hero.getDirection() == 135){
			if((canGoDown == false) & (canGoRight == false)){
				hero.setIsMoving(false);
			}
			else if(canGoDown == false){
				hero.setDirection(90);
			}
			else if(canGoRight == false){
				hero.setDirection(180);
			}
		}
		
		else if(hero.getDirection() == 180){
			if(canGoDown == false){
				hero.setIsMoving(false);
			}
		}
		
		else if(hero.getDirection() == 225){
			if((canGoDown == false) & (canGoLeft == false)){
				hero.setIsMoving(false);
			}
			else if(canGoDown == false){
				hero.setDirection(270);
			}
			else if(canGoLeft == false){
				hero.setDirection(180);
			}
		}
		
		else if(hero.getDirection() == 270){
			if(canGoLeft == false){
				hero.setIsMoving(false);
			}
		}
		
		else if(hero.getDirection() == 315){
			if((canGoUp == false) & (canGoLeft == false)){
				hero.setIsMoving(false);
			}
			else if(canGoUp == false){
				hero.setDirection(270);
			}
			else if(canGoLeft == false){
				hero.setDirection(0);
			}
		}
	}
	
	public void checkForSpriteCollision(ArrayList<Drawable> drawables, Hero hero){
		Collections.sort(drawables, Drawable.PosComparator);
		Drawable movingD;
		for(int y = 0; (drawables.size() + 1) > y; y++){
			if(y == drawables.size()){
				movingD = hero;
			}
			else{
				movingD = drawables.get(y);
			}
			if(!(movingD instanceof Sprite)){
				continue;
			}
			Sprite movingS = (Sprite) movingD;
			int newX = (movingS.getXPos() + (int)(Math.round(Math.sin(Math.toRadians(movingS.getDirection())) * movingS.getSpeed())));
			int newY = (movingS.getYPos() + (int)(Math.round(Math.cos(Math.toRadians(movingS.getDirection())) * movingS.getSpeed() * (-1))));
			int newMaxX = newX + 64;	//Right
			int newMaxY = newY + 64;	//Bottom
			boolean canGoUp = true;
			boolean canGoDown = true;
			boolean canGoLeft = true;
			boolean canGoRight = true;
			int collisionX = 0;
			int collisionY = 0;
			
			//TODO: fix this for single collision
			System.out.println("New cycle");
			int collision1Count = 0;
			int collision2Count = 0;
			int collision3Count = 0;
			int collision4Count = 0;
			Drawable d;
			for(int x = 0; (drawables.size() + 1) > x; x++){
				if(x == (drawables.size())){
					d = hero;
				}
				else{
					d = drawables.get(x);
				}
				if(d == movingS){
					continue;
				}
				
				if((newX >= d.getXPos()) & (newX <= (d.getXPos() + 64))){ 		//Collision from the left
					if((newY >= d.getYPos()) & (newY <= (d.getYPos() + 64))){	//Collision from the bottom
						System.out.println("Collision 1 for " + d.getXPos() + "," + d.getYPos());
						//TODO this should be generalzied some how
						if(movingS instanceof Projectile){
							if(d instanceof Wall){
								drawables.remove(movingS);
							}
							else{
								continue;
							}
						}
						if(d instanceof Projectile){
							System.out.println("NPC hit a projectile");
							Projectile p = (Projectile) d;
							if(p.getIsFromHero() & (!(movingS == hero))){
								drawables.remove(d);
							}
							else if(!(p.getIsFromHero()) & (movingS == hero)){
								drawables.remove(d);
							}
							continue;
						}
						//end TODO
						++collision1Count;
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
						System.out.println("Collision 2 for " + d.getXPos() + "," + d.getYPos());
						//TODO this should be generalzied some how
						if(movingS instanceof Projectile){
							if(d instanceof Wall){
								drawables.remove(movingS);
							}
							else{
								continue;
							}
						}
						if(d instanceof Projectile){
							System.out.println("I hit a projectile");
							Projectile p = (Projectile) d;
							if(p.getIsFromHero() & (!(movingS == hero))){
								drawables.remove(d);
							}
							else if(!(p.getIsFromHero()) & (movingS == hero)){
								drawables.remove(d);
							}
							continue;
						}
						//end TODO
						++collision2Count;
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
						System.out.println("Collision 3 for " + d.getXPos() + "," + d.getYPos());
						//TODO this should be generalzied some how
						if(movingS instanceof Projectile){
							if(d instanceof Wall){
								drawables.remove(movingS);
							}
							else{
								continue;
							}
						}
						if(d instanceof Projectile){
							System.out.println("I hit a projectile");
							Projectile p = (Projectile) d;
							if(p.getIsFromHero() & (!(movingS == hero))){
								drawables.remove(d);
							}
							else if(!(p.getIsFromHero()) & (movingS == hero)){
								drawables.remove(d);
							}
							continue;
						}
						//end TODO
						++collision3Count;
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
						System.out.println("Collision 4 for " + d.getXPos() + "," + d.getYPos());
						//TODO this should be generalzied some how
						if(movingS instanceof Projectile){
							if(d instanceof Wall){
								drawables.remove(movingS);
							}
							else{
								continue;
							}
						}
						if(d instanceof Projectile){
							System.out.println("I hit a projectile");
							Projectile p = (Projectile) d;
							if(p.getIsFromHero() & (!(movingS == hero))){
								drawables.remove(d);
							}
							else if(!(p.getIsFromHero()) & (movingS == hero)){
								drawables.remove(d);
							}
							continue;
						}
						//end TODO
						++collision4Count;
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
			
			
			//TODO: take this into separate method
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
	}

	
	public void doGameLogic(Set keySet) {
		//Check to see if things are outside the map, if so kill them
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

		
		//Set up movements
		hero.doLogic(keySet);
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			d.doLogic();
		}
		
		//checkForHeroCollision(drawables, hero);
		checkForSpriteCollision(drawables, hero);
		
		//Move if not colliding
		hero.move();
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			d.move();
		}
		
		//Do attacks for everything
		hero.attack();
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			Sprite dummy = new Sprite(0, 0, 0);
			if(d.getClass() == dummy.getClass()){
				dummy = (Sprite) d;
				dummy.attack();
			}
		}
	}
}
