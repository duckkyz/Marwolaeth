package marwolaeth;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Archer extends Hero{
	
	public Archer(int direction, int xPos, int yPos) {
		super(direction, xPos, yPos);
		setTileWidth(64);
		setTileHeight(64);
		try {
			setGraphic(ImageIO.read(new File("Drawable_Images/Archer.png")));
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void ability1_setup() {							//sets the ActionSequence that ability_1 invokes (based off which direction hero is currently facing)
		setActionStep(0);
		setCompleteingSequence(true);
		switch(getActionSequence()){																						
			case 8:
				setActionSequence(16);
				break;
			case 9:
				setActionSequence(17);
				break;
			case 10:
				setActionSequence(18);
				break;
			case 11:
				setActionSequence(19);
				break;
			default:
				setActionSequence(16);
				break;
		}
		
	}
	
	public void ability1_execute(int direction) {
		Arrow arrow = new Arrow(direction, getXPos()+getTileWidth()/2, getYPos()+getTileHeight()/2, true);	//math gives the created object a reference to the center of the hero
		Game.addDrawable(arrow);
		
	}
	
	public void paint(Graphics imageGraphics) {
		
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null);
		
	}

}