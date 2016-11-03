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
		for(int i=0;i<10;++i){
			drawables.add(new Wall((2*250)-64,(i*64)));
			drawables.add(new Wall((i*64),(2*250)-64));
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
	
	public void stopUpMovement(){
		if(hero.getDirection() == 0){
			hero.setIsMoving(false);
		}	
		else if(hero.getDirection() == 45){
			hero.setIsMoving(true);
			hero.setDirection(90);
		}
		else if(hero.getDirection() == 315){
			hero.setIsMoving(true);
			hero.setDirection(270);
		}
	}

	public void stopDownMovement(){
		if(hero.getDirection() == 180){
			hero.setIsMoving(false);
		}	
		else if(hero.getDirection() == 135){
			hero.setIsMoving(true);
			hero.setDirection(90);
		}
		else if(hero.getDirection() == 225){
			hero.setIsMoving(true);
			hero.setDirection(270);
		}
	}
	
	public void stopLeftMovement(){
		if(hero.getDirection() == 270){
			hero.setIsMoving(false);
		}	
		else if(hero.getDirection() == 315){
			hero.setIsMoving(true);
			hero.setDirection(0);
		}
		else if(hero.getDirection() == 225){
			hero.setIsMoving(true);
			hero.setDirection(180);
		}
	}
	
	public void stopRightMovement(){
		if(hero.getDirection() == 90){
			hero.setIsMoving(false);
		}	
		else if(hero.getDirection() == 45){
			hero.setIsMoving(true);
			hero.setDirection(0);
		}
		else if(hero.getDirection() == 135){
			hero.setIsMoving(true);
			hero.setDirection(180);
		}
	}
	
	public void checkForMapEdgeCollision(ArrayList<Drawable> drawables, Hero hero){
		
		
		
		int newX = (hero.getXPos() + (int)(Math.round(Math.sin(Math.toRadians(hero.getDirection())) * hero.getSpeed())));
		int newY = (hero.getYPos() + (int)(Math.round(Math.cos(Math.toRadians(hero.getDirection())) * hero.getSpeed() * (-1))));
		int newMaxX = newX + 64;	//Right
		int newMaxY = newY + 64;	//Bottom
		boolean canGoUp = true;
		boolean canGoDown = true;
		boolean canGoLeft = true;
		boolean canGoRight = true;

		System.out.println("New cycle");
		for(int x=0;drawables.size()>x;x++){		
			Drawable d = drawables.get(x);	
			
			if((newX >= d.getXPos()) & (newX <= (d.getXPos() + 64))){ 		//Collision from the left
				if((newY >= d.getYPos()) & (newY <= (d.getYPos() + 64))){	//Collision from the bottom
					System.out.println("Collision 1 for " + d.getXPos() + "," + d.getYPos());
					canGoLeft = false;
					canGoUp = false;
					
					/*if(hero.getDirection() == 270){
						canGoLeft = false;
						System.out.println("I'm trying to go left");
					}
					else if(hero.getDirection() == 315){
						canGoLeft = false;
						//hero.setDirection(0);
						System.out.println("I'm trying to go up-left");
					}
					else if(hero.getDirection() == 225){
						canGoLeft = false;
						//hero.setDirection(180);
						System.out.println("I'm trying to go down-left");
					}		
					else if(hero.getDirection() == 0){
						canGoUp = false;
					}
					*/
				}
				else if((newMaxY >= (d.getYPos())) & (newMaxY <= (d.getYPos() + 64))){	//Collision from top
					System.out.println("Collision 2 for " + d.getXPos() + "," + d.getYPos());
					canGoLeft = false;
					canGoDown = false;
					/*if(hero.getDirection() == 270){
						canGoLeft = false;
						System.out.println("I'm trying to go left");
					}
					else if(hero.getDirection() == 315){
						canGoLeft = false;
						//hero.setDirection(0);
						System.out.println("I'm trying to go up-left");
					}
					else if(hero.getDirection() == 225){
						canGoLeft = false;
						//hero.setDirection(180);
						System.out.println("I'm trying to go down-left");
					}
					*/		
				}
			}
			else if ((newMaxX >= d.getXPos()) & (newMaxX <= (d.getXPos() + 64))){	//Collision from the right
				if((newY >= d.getYPos()) & (newY <= (d.getYPos() + 64))){
					System.out.println("Collision 3 for " + d.getXPos() + "," + d.getYPos());
					canGoRight = false;
					canGoUp = false;
					/*
					if(hero.getDirection() == 90){
						//hero.setIsMoving(false);
						System.out.println("I'm trying to go right");
					}
					else if(hero.getDirection() == 45){
						//hero.setDirection(0);
						System.out.println("I'm trying to go up-right");
					}
					else if(hero.getDirection() == 135){
						//hero.setDirection(180);
						System.out.println("I'm trying to go down-right");
					}		
					else if(hero.getDirection() == 0){
						canGoUp = false;
					}
					*/
				}
				else if((newMaxY >= (d.getYPos())) & (newMaxY <= (d.getYPos() + 64))){
					System.out.println("Collision 4 for " + d.getXPos() + "," + d.getYPos());
					canGoRight = false;
					canGoDown = false;
				}
			}
			//Collision types:
			//top: newMinX < (d.getXPos() + 32)
			//topLeft: (newMinX < (d.getXPos() + 32)) & (newMinY > 
			//
			//
			
			//TODO: Fix collision for non wall entities			
			
			/*
			
			
			//Implements top collision
			if(d.getYPos() == 0){
				if((newY < (d.getYPos() + 64)) & (newY > d.getYPos())){
					if((newX < d.getXPos()) & (newMaxX > d.getXPos())){
						System.out.println("1. Wont move up because newY: " + newY + " <= d.getYPos + 64: " + (d.getYPos() + 64));
						System.out.println();
						stopUpMovement();
					}	
					else if((newX < (d.getXPos() + 64)) & (newMaxX > (d.getXPos() + 64))){
						System.out.println("2. Wont move up because newY: " + newY + " <= d.getYPos + 64: " + (d.getYPos() + 64));
						System.out.println();
						stopUpMovement();
					}
					else if((newX == (d.getXPos())) & (newMaxX == (d.getXPos() + 64))){
						System.out.println("3. Wont move up because newY: " + newY + " <= d.getYPos + 64: " + (d.getYPos() + 64));
						System.out.println();
						stopUpMovement();
					}
				}
			}
			
			//Bot collision
			if(d.getYPos() == ((1080 * 2) - 64)){
				if((newMaxY > d.getYPos()) & (newY < d.getYPos())){
					if((newX < d.getXPos()) & (newMaxX > d.getXPos())){
						System.out.println("1. Wont move down because newMaxY: " + newMaxY + " >= d.getYPos: " + d.getYPos());
						System.out.println();
						stopDownMovement();
					}	
					else if((newX < (d.getXPos() + 64)) & (newMaxX > (d.getXPos() + 64))){
						System.out.println("2. Wont move down because newMaxY: " + newMaxY + " >= d.getYPos: " + d.getYPos());
						System.out.println();
						stopDownMovement();
					}
					else if((newX == (d.getXPos())) & (newMaxX == (d.getXPos() + 64))){
						System.out.println("3. Wont move down because newMaxY: " + newMaxY + " >= d.getYPos: " + d.getYPos());
						System.out.println();
						stopDownMovement();
					}
				}
			}
			
			if(d.getXPos() == ((1920 * 2) - 64)){
				//Right Collision
				if((newMaxX > d.getXPos()) & (newX < d.getXPos())){
					if((newY < d.getYPos()) & (newMaxY > d.getYPos())){
						System.out.println("1. Wont move right because newMaxX: " + newX + " >= d.getXPos: " + d.getXPos());
						System.out.println();
						stopRightMovement();
					}	
					else if((newY < (d.getYPos() + 64)) & (newMaxY > (d.getYPos() + 64))){
						System.out.println("2. Wont move right because newMaxX: " + newX + " >= d.getXPos: " + d.getXPos());
						System.out.println();
						stopRightMovement();
					}
					else if((newY == (d.getYPos())) & (newMaxY == (d.getYPos() + 64))){
						System.out.println("3. Wont move right because newMaxX: " + newX + " >= d.getXPos: " + d.getXPos());
						System.out.println();
						stopRightMovement();
					}
				}
			}
			
			if(d.getXPos() == 0){
				//Left Collision
				if((newX < (d.getXPos() + 64)) & (newMaxX > (d.getXPos() + 64))){
					if((newY < d.getYPos()) & (newMaxY > d.getYPos())){
						System.out.println("1. Wont move left because newX: " + newX + " <= d.getXPos + 64: " + (d.getXPos() + 64));
						System.out.println();
						stopLeftMovement();
					}	
					else if((newY < (d.getYPos() + 64)) & (newMaxY > (d.getYPos() + 64))){
						System.out.println("2. Wont move left because newX: " + newX + " <= d.getXPos + 64: " + (d.getXPos() + 64));
						System.out.println();
						stopLeftMovement();
					}
					else if((newY == (d.getYPos())) & (newMaxY == (d.getYPos() + 64))){
						System.out.println("3. Wont move left because newX: " + newX + " <= d.getXPos + 64: " + (d.getXPos() + 64));
						System.out.println();
						stopLeftMovement();
					}
				}			
			}
			*/
		}

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
	
	public void doGameLogic(Set keySet) {
		//Set up movements
		hero.doLogic(keySet);
		for(int x=0;drawables.size()>x;x++) {					//arrow can remove itself with this type of for loop without exploding the program
			Drawable d = drawables.get(x);
			d.doLogic();
		}
				
		//Check for collision
		/* Brainstorming a little:
		 * A generic collision detection alg:
		 * 	1. are you in this d?
		 * 		a. if ((this.xPos >= d.xPos) and (this.xPos <= (d.xPos + 64))) 
		 * 			i. if((this.yPos >= d.yPos) and (this.yPos <= (d.yPos + 64))){
		 * 					//youre inside, you have problem
		 * 				}
		 * 		   ii. else if(((this.yPos + 64) >= (d.yPos)) and ((this.yPos +64) <= (d.yPos + 64))){
		 * 					//youre inside, you have problem
		 * 				}
		 * 		b. else if (((this.xPos + 64) >= d.xPos) and ((this.xPos + 64) <= (d.xPos + 64)))
		 * 			i. if((this.yPos >= d.yPos) and (this.yPos <= (d.yPos + 64))){
		 * 					//youre inside, you have problem
		 * 				}
		 * 		   ii. else if(((this.yPos + 64) >= (d.yPos)) and ((this.yPos +64) <= (d.yPos + 64))){
		 * 					//youre inside, you have problem
		 * 				}
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */		
		checkForMapEdgeCollision(drawables, hero);
		
		
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
