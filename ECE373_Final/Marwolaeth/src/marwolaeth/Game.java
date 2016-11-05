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
	
	public Game() {
		
		//This makes the walls around the edge.
		for(int i=0;i<35;++i){
			drawables.add(new Wall(0,(i*64)));
			drawables.add(new Wall((2*1920)-64,(i*64)));
		}
		//for(int i=0; i<60; ++i){
		for(int i=60; i>0; --i){
			drawables.add(new Wall((i*64),0));
			drawables.add(new Wall((i*64),(2*1080)-64));
		}
		
		//Testing walls
		//for(int i=0;i<10;++i){
		//	drawables.add(new Wall((2*250)-64,(i*64)));
		//	drawables.add(new Wall((i*64),(2*250)-64));
		//}
		
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
	
	public void checkForMapEdgeCollision(ArrayList<Drawable> drawables, Hero hero){
		Collections.sort(drawables, Drawable.PosComparator);
		
		
		int newX = (hero.getXPos() + (int)(Math.round(Math.sin(Math.toRadians(hero.getDirection())) * hero.getSpeed())));
		int newY = (hero.getYPos() + (int)(Math.round(Math.cos(Math.toRadians(hero.getDirection())) * hero.getSpeed() * (-1))));
		int newMaxX = newX + 64;	//Right
		int newMaxY = newY + 64;	//Bottom
		boolean canGoUp = true;
		boolean canGoDown = true;
		boolean canGoLeft = true;
		boolean canGoRight = true;
		
		//TODO: fix this for single collision
		System.out.println("New cycle");
		for(int x=0;drawables.size()>x;x++){
			Drawable d = drawables.get(x);	
			
			if((newX >= d.getXPos()) & (newX <= (d.getXPos() + 64))){ 		//Collision from the left
				if((newY >= d.getYPos()) & (newY <= (d.getYPos() + 64))){	//Collision from the bottom
					System.out.println("Collision 1 for " + d.getXPos() + "," + d.getYPos());
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
					else if(hero.getDirection() == 225){
						//canGoDown = false;
					}
				}
			}
			else if ((newMaxX >= d.getXPos()) & (newMaxX <= (d.getXPos() + 64))){	//Collision from the right
				if((newY >= d.getYPos()) & (newY <= (d.getYPos() + 64))){
					System.out.println("Collision 3 for " + d.getXPos() + "," + d.getYPos());
					if(hero.getDirection() == 0){
						canGoUp = false;
					}
					if(hero.getDirection() == 45){
						if(canGoUp == true){
							canGoRight = false;
						}
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
