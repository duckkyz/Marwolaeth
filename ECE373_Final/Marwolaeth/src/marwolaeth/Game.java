package marwolaeth;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import marwolaeth.DrawableClasses.*;
import marwolaeth.FloorTiles.*;
import marwolaeth.ImplementedEntities.*;

public class Game {
	private static ArrayList<Drawable> drawables = new ArrayList<Drawable>();
	private static Hero hero;
	private final int mapHeight = 2*1080;
	private final int mapWidth = 2*1920;
	private int currentWave = 0;
	private boolean waveDoneSpawning = true;
	private int spawnCounter = 0;
	private boolean debugText = false;
	
	public Game() {
		setUpGame();
	}

	public static Hero getHero() {
		return hero;
	}
	
	public static ArrayList<Drawable> getDrawables() {
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
		
	public void setUpGame(){
		//Removes all things from game so it starts fresh
		currentWave = 0;
		drawables.removeAll(drawables);

		//This makes the walls around the edge.
		for(int i=2;i<33;++i){
			drawables.add(new LeftWall(0,(i*64)));
			drawables.add(new RightWall((mapWidth)-64,(i*64)));
		}
		for(int i=1; i<59; ++i){
			drawables.add(new TopWall((i*64),0));
			drawables.add(new BotWall((i*64),(mapHeight)-64));
		}
		drawables.add(new TopLeftCornerWall(0,0));
		drawables.add(new TopRightCornerWall((59*64),0));
		drawables.add(new BotLeftCornerWall(0,(mapHeight)-64));
		drawables.add(new BotRightCornerWall((59*64),(mapHeight)-64));
		
		//Testing walls
		
		TikiGolem spawnGolem = new TikiGolem(0,0);
		for(int j = 0; j < 10; ++j){
			for(int i = 0; i < 2; ++i){
				//Initial new wall
				spawnGolem = new TikiGolem(((int)(Math.floor((Math.random() * mapWidth)/64) * 64)),((int)(Math.floor((Math.random() * (mapHeight - 192))/64) * 64)));

				//Checks to see if its on top of something else
				while((checkCanSpawn(spawnGolem) == false) & (drawables.size() < mapWidth*mapHeight)){
					spawnGolem = new TikiGolem(((int)(Math.floor((Math.random() * mapWidth)/64) * 64)),((int)(Math.floor((Math.random() * mapHeight)/64) * 64)));
				}
				drawables.add(spawnGolem);
			}
		}
		
		Sprite spawnSprite = new Sprite(0,0,0);
		for(int i = 0; i < 3; ++i){
			int orcDirection = (int) (45 * (Math.floor(((Math.random() * 360) / 45))));
			int orcXPos = (int)(Math.floor((Math.random() * (mapWidth - 2*64))/64) * 64);
			int orcYPos = (int)(Math.floor((Math.random() * (mapHeight - 2*64))/64) * 64);
			
			while((checkCanSpawn(spawnSprite) == false) & (drawables.size() < mapWidth*mapHeight)){
				orcDirection = (int) (45 * (Math.floor(((Math.random() * 360) / 45))));
				orcXPos = (int)(Math.floor((Math.random() * (mapWidth - 2*64))/64) * 64);
				orcYPos = (int)(Math.floor((Math.random() * (mapHeight - 2*64))/64) * 64);
				spawnSprite = new Sprite(orcDirection, orcXPos, orcYPos);
			}
			
			//if(i%3 == 0){
				drawables.add(new Orc(orcDirection, orcXPos, orcYPos));
			//}
			//else if(i%3 == 1){
			//	drawables.add(new Wizard(orcDirection, orcXPos, orcYPos));
			//}
			//else{
			//	drawables.add(new Archer(orcDirection, orcXPos, orcYPos));
			//}
		}
	}
	
	public boolean checkCanSpawn(Drawable toSpawn){
		int newX = ((toSpawn.getXPos() + toSpawn.getLeftHitBox()));
		int newY = ((toSpawn.getYPos() + toSpawn.getTopHitBox()));
		int newMaxX = newX + (toSpawn.getTileWidth() - toSpawn.getLeftHitBox() - toSpawn.getRightHitBox());	//Right
		int newMaxY = newY + (toSpawn.getTileHeight() - toSpawn.getTopHitBox() - toSpawn.getBotHitBox());	//Bottom
		
		int dX;
		int dY;
		int dMaxX;
		int dMaxY;
		
		
		for(Drawable d : drawables){
			dX = ((d.getXPos() + d.getLeftHitBox()));
			dY = ((d.getYPos() + d.getTopHitBox()));
			dMaxX = dX + (d.getTileWidth() - d.getLeftHitBox() - d.getRightHitBox());	//Right
			dMaxY = dY + (d.getTileHeight() - d.getTopHitBox() - d.getBotHitBox());	//Bottom
			
			if((newX >= dX) & (newX <= dMaxX)){ 			//Collision from the left
				if((newY >= dY) & (newY <= dMaxY)){		//Collision from the bottom
					return false;
				}
				
				else if((newMaxY >= dY) & (newMaxY <= dMaxY)){	//Collision from top
					return false;
				}
			}
			else if ((newMaxX >= dX) & (newMaxX <= dMaxX)){	//Collision from the right
				if((newY >= dY) & (newY <= dMaxY)){		//Collision from the bottom
					return false;
				}
				
				else if((newMaxY >= dY) & (newMaxY <= dMaxY)){	//Collision from top
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean projectileHandling(Sprite movingS, Drawable d){
		if(movingS instanceof Projectile){
			if(d instanceof Wall){
				drawables.remove(movingS);
				return true;
			}
			else if(d instanceof Projectile){
				return true;
			}
			else{
				System.out.println(d.getClass().getSimpleName() + " was hit by a projectile");
				if(d instanceof Sprite){
					Projectile p = (Projectile) movingS;
					Sprite dSprite = (Sprite) d;
					if(p.getIsFromHero() & (!(dSprite == hero))){
						if((dSprite instanceof Hero) | (dSprite instanceof Villain)){
							p.attack(dSprite);
						}
						drawables.remove(p);
					}
					else if(!(p.getIsFromHero()) & (dSprite == hero)){
						if((dSprite instanceof Hero) | (dSprite instanceof Villain)){
							p.attack(dSprite);
						}
						drawables.remove(p);
					}
					return true;
				}
				return true;
			}
		}
		return false;
	}
	
	private void spawnNewWave(int currentWave) {
		if(waveDoneSpawning == false){
			Sprite spawnSprite = new Sprite(0,0,0);
			int orcDirection = (int) (45 * (Math.floor(((Math.random() * 360) / 45))));
			int orcXPos = (int)(Math.floor((Math.random() * (mapWidth - 2*64))/64) * 64);
			int orcYPos = (int)(Math.floor((Math.random() * (mapHeight - 2*64))/64) * 64);				
			while((checkCanSpawn(spawnSprite) == false) & (drawables.size() < mapWidth*mapHeight)){
				orcDirection = (int) (45 * (Math.floor(((Math.random() * 360) / 45))));
				orcXPos = (int)(Math.floor((Math.random() * (mapWidth - 2*64))/64) * 64);
				orcYPos = (int)(Math.floor((Math.random() * (mapHeight - 2*64))/64) * 64);
				spawnSprite = new Sprite(orcDirection, orcXPos, orcYPos);
			}
			
			if(spawnCounter%3 == 0){
				drawables.add(new Orc(orcDirection, orcXPos, orcYPos));
			}
			else if(spawnCounter%3 == 1){
				drawables.add(new Wizard(orcDirection, orcXPos, orcYPos));
			}
			else{
				drawables.add(new Archer(orcDirection, orcXPos, orcYPos));
			}
			
			++spawnCounter;
			if(spawnCounter > currentWave){
				waveDoneSpawning = true;
				++this.currentWave;
			}	
		}
	}
	
	public boolean checkForOutsideMap(Drawable d){
		if(d.getXPos() > mapWidth){
			System.out.println("Removing " + d.toString());
			removeDrawable(d);
			return true;
		}
		else if(d.getXPos() < 0){
			System.out.println("Removing " + d.toString());
			removeDrawable(d);
			return true;
		}
		else if(d.getYPos() > mapHeight){
			System.out.println("Removing " + d.toString());
			removeDrawable(d);
			return true;
		}
		else if(d.getYPos() < 0) {
			System.out.println("Removing " + d.toString());
			removeDrawable(d);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setUpMovements(Set keySet){
		hero.doLogic(keySet);
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			if(d instanceof Sprite){
				if(checkForOutsideMap(d) == false){
					d.doLogic();
				}
			}
		}
	}
	
	public void checkForSpriteCollision(ArrayList<Drawable> drawables, Hero hero){
		Collections.sort(drawables, Drawable.PosComparator);				//Sorts drawables so there is no need for spawn order collision casing
		Drawable movingD;													
		if(debugText){
			System.out.println();
			System.out.println("New cycle:");
		}
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
			
			int newX = 0;
			int newY = 0;
			int newMaxX = 0;	//Right
			int newMaxY = 0;	//Bottom
			
			newX = ((movingS.getXPos() + movingS.getLeftHitBox()) + (int)(Math.round(Math.sin(Math.toRadians(movingS.getDirection())) * movingS.getSpeed())));
			newY = ((movingS.getYPos() + movingS.getTopHitBox()) + (int)(Math.round(Math.cos(Math.toRadians(movingS.getDirection())) * movingS.getSpeed() * (-1))));
			newMaxX = newX + (movingS.getTileWidth() - movingS.getLeftHitBox() - movingS.getRightHitBox());	//Right
			newMaxY = newY + (movingS.getTileHeight() - movingS.getTopHitBox() - movingS.getBotHitBox());	//Bottom

			//Initializes the movement booleans
			boolean canGoUp = true;
			boolean canGoDown = true;
			boolean canGoLeft = true;
			boolean canGoRight = true;
			
			//Used for single collision checking
			ArrayList<Integer> collisionX = new ArrayList<Integer>();
			ArrayList<Integer> collisionY = new ArrayList<Integer>();
			int collision1Count = 0;
			int collision2Count = 0;
			int collision3Count = 0;
			int collision4Count = 0;
			int dX;
			int dY;
			int dMaxX;
			int dMaxY;
			
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

				dX = ((d.getXPos() + d.getLeftHitBox()));
				dY = ((d.getYPos() + d.getTopHitBox()));
				dMaxX = dX + (d.getTileWidth() - d.getLeftHitBox() - d.getRightHitBox());	//Right
				dMaxY = dY + (d.getTileHeight() - d.getTopHitBox() - d.getBotHitBox());	//Bottom
				
				if((newX >= dX) & (newX <= dMaxX)){ 			//Collision from the left
					if((newY >= dY) & (newY <= dMaxY)){		//Collision from the bottom
						//Projectile handling happens here, if it should continue it will
						if(movingS == hero){
							if(debugText){
								System.out.println("collision 1 for " + d.getClass().getSimpleName() + ": " + d.getXPos() + ", " + d.getYPos());
							}
						}
						if(projectileHandling(movingS, d)){
							continue;
						}
						
						if(movingS.getDirection() == 0){
							canGoUp = false;
						}
						
						else if(movingS.getDirection() == 45){
							canGoUp = false;
						}
						
						else if(movingS.getDirection() == 225){
							canGoLeft = false;
							if(collision1Count > 0){
								//canGoDown = false;
							}
						}
						
						else if(movingS.getDirection() == 270){
							canGoLeft = false;
						}
						else if(movingS.getDirection() == 315){
							if(collision3Count > 0){
								canGoUp = false;
								if(!collisionY.contains(dMaxY)){
									if((collision1Count > 0)){
										canGoLeft = false;
									}
								}
							}
						}
						
						++collision1Count;											//Used for single collision 
						collisionX.add(dMaxX);
						collisionY.add(dMaxY);
						
					}
					
					else if((newMaxY >= dY) & (newMaxY <= dMaxY)){	//Collision from top
						//Projectile handling happens here, if it should continue it will
						if(movingS == hero){
							if(debugText){
								System.out.println("collision 2 for " + d.getClass().getSimpleName() + ": " + d.getXPos() + ", " + d.getYPos());
							}
						}
						if(projectileHandling(movingS, d)){
							continue;
						}
					
						if(movingS.getDirection() == 135){
							canGoDown = false;
						}
						
						else if(movingS.getDirection() == 270){
							canGoLeft = false;
						}
						
						else if(movingS.getDirection() == 315){
							canGoLeft = false;
							if((collision1Count > 0)){
								if(!collisionX.contains(dMaxX)){
									canGoUp = false;
								}
							}
						}	
						
						else if(movingS.getDirection() == 180){
							canGoDown = false;
						}
						
						else if(movingS.getDirection() == 225){
							if(collision4Count > 0){
								canGoDown = false;
							}
							if((collision1Count > 0)){
								if(d instanceof BotLeftCornerWall){
									canGoDown = false;
								}
							}
							if(collision2Count >0){
								canGoDown = false;
							}
						}
						
						++collision2Count;													//Single Collision Detection
						collisionX.add(dMaxX);
						collisionY.add(dY);
						
					}
				}
				else if ((newMaxX >= dX) & (newMaxX <= dMaxX)){	//Collision from the right
					if((newY >= dY) & (newY <= dMaxY)){		//Collision from the bottom
						if(movingS == hero){
							if(debugText){
								System.out.println("collision 3 for " + d.getClass().getSimpleName() + ": " + d.getXPos() + ", " + d.getYPos());
							}
						}
						//Projectile handling happens here, if it should continue it will
						if(projectileHandling(movingS, d)){
							continue;
						}

						++collision3Count;												//Single Collision detection
						collisionX.add(dX);
						collisionY.add(dMaxY);
						
						if(movingS.getDirection() == 0){
							canGoUp = false;
						}
						
						if((collision3Count > 1) & (movingS.getDirection() == 45)){
							canGoRight = false;
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
					else if((newMaxY >= dY) & (newMaxY <= dMaxY)){	//Collision from top
						if(movingS == hero){
							if(debugText){
								System.out.println("collision 4 for " + d.getClass().getSimpleName() + ": " + d.getXPos() + ", " + d.getYPos());
							}
						}
						//Projectile handling happens here, if it should continue it will
						if(projectileHandling(movingS, d)){
							continue;
						}
						
						++collision4Count;													//Single collision detection
						collisionX.add(dX);
						collisionY.add(dY);
						
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
				if((canGoDown & canGoRight & canGoUp & canGoLeft) == false){
					if(d instanceof Wall){
						movingS.incrementCollisionCounter();
					}
				}
				else{
					movingS.decrementCollisionCounter();
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
						if((collisionX.get(0) - newX) < (collisionY.get(0) - newY)){
							canGoLeft = false;
						}
						else if((collisionX.get(0) - newX) > (collisionY.get(0) - newY)){
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
						if((collisionX.get(0) - newX) < (newMaxY - collisionY.get(0))){
							canGoLeft = false;
						}
						else if((collisionX.get(0) - newX) > (newMaxY - collisionY.get(0))){
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
						if((newMaxX - collisionX.get(0)) < (collisionY.get(0) - newY)){
							canGoRight = false;
						}
						else if((newMaxX - collisionX.get(0)) > (collisionY.get(0) - newY)){
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
						if((newMaxX - collisionX.get(0)) < (newMaxY - collisionY.get(0))){
							canGoRight = false;
						}
						else if((newMaxX - collisionX.get(0)) > (newMaxY - collisionY.get(0))){
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
	
	public void moveDrawables(){
		hero.move();
		for(int x = 0; drawables.size() > x; x++){
			Drawable d = drawables.get(x);
			//if((d instanceof Sprite) & !(d instanceof Projectile)){
			//	Sprite s = (Sprite)d;
			//	s.setHealth(s.getHealth() - 1);
			//}
			d.move();
		}
	}
		
	public boolean doGameLogic(Set keySet) {		
		//Checks if hero is dead if so returns false
		if(hero.getHealth() <= 0){
			if(hero.getActionStep() == 5) {
				return false;
			}
		}
		
		//Hero heath/mana regain
		if((hero.getHealth() < 100 & hero.getHealth() > 0)){
			hero.setHealth(hero.getHealth() + 1);
		}
		if((hero.getMana() < 100)){
			hero.setMana(hero.getMana() + 1);
		}
		
		//Debug stuff
		if(keySet.contains(KeyEvent.VK_B)){
			this.debugText = true;
		}
		else{
			this.debugText = false;
		}
		
		//Check if all non hero entities are dead, if so spawn a new wave
		int waveCounter = 0;
		for(Drawable d : drawables){
			if(d instanceof Sprite){
				++waveCounter;
				break;
			}
		}
		if(waveCounter == 0){
			System.out.println("Spawning new wave");
			spawnCounter = 0;
			this.waveDoneSpawning = false;
		}
		spawnNewWave(currentWave);
		
		//Set up movements
		setUpMovements(keySet);
		
		//Check for collision
		checkForSpriteCollision(drawables, hero);
		
		//Move if can
		moveDrawables();
		
		return true;
	}
}
