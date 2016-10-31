package marwolaeth.ImplementedEntities;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.DrawableClasses.Hero;
import marwolaeth.ImplementedProjectiles.Arrow;
import marwolaeth.ImplementedProjectiles.Fireball;


public class Wizard extends Hero{
	
	public Wizard(int direction, int xPos, int yPos) {
		super(direction, xPos, yPos);
		setTileWidth(64);
		setTileHeight(64);
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/Wizard.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ability1Setup() {							//sets the ActionSequence that ability1 invokes (based off which direction hero is currently facing)
		setActionStep(0);
		setMoveCasting(true);
		setCompleteingSequence(true);
		switch(getActionSequence()){																						
			case 8:
				setActionSequence(0);
				break;
			case 9:
				setActionSequence(1);
				break;
			case 10:
				setActionSequence(2);
				break;
			case 11:
				setActionSequence(3);
				break;
			default:
				setActionSequence(0);
				break;
		}
	}

	public void ability2Setup() {							
		//TODO implement this
	}
	
	public void ability3Setup() {	
		//TODO implement this
	}
	
	public void ability4Setup() {
		//TODO implement this
	}

	public void ability1Execute(int direction) {
		Fireball fireball = new Fireball(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true);	//math gives the created object a reference to the center of the hero
		Game.addDrawable(fireball);		
	}

	public void ability2Execute(int direction) {
		//TODO implement this
	}
	
	public void ability3Execute(int direction) {
		//TODO implement this
	}
	
	public void ability4Execute(int direction) {
		//TODO implement this
	}
		
	public void paint(Graphics imageGraphics) {
		
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		
	}

}
