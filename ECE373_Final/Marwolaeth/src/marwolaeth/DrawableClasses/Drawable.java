////////////////////////////////////////////////////
// Drawables are the parent class for every thing //
// that will get drawn to the screen:			  //
//   # Walls								.	  //
//	 # Other terrain							  //
//	 # Sprites									  //
//												  //
//												  //
//												  //
////////////////////////////////////////////////////


package marwolaeth.DrawableClasses;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Drawable {

	private int xPos;
	private int yPos;
	private int tileWidth;
	private int tileHeight;
	private int actionSequence = 0;									//The current row.
	private int actionStep = 0;										//The current column.
	private BufferedImage graphic;
	
	public Drawable() {
		this.xPos = 0;
		this.yPos = 0;
	}
	
	public Drawable(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
	}
	
	public int getXPos() {
		return xPos;
	}
	
	public int getYPos() {
		return yPos;
	}
	
	public int getTileWidth() {
		return tileWidth;
	}
	
	public int getTileHeight() {
		return tileHeight;
	}
	
	public int getActionSequence() {
		return actionSequence;
	}
	
	public int getActionStep() {
		return actionStep;
	}
	
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}
	
	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
	
	public void setActionSequence(int actionSequence) {
		this.actionSequence = actionSequence;
	}
	
	public void setActionStep(int actionStep) {
		this.actionStep = actionStep;
	}
	
	public void setGraphic(BufferedImage graphic){
		this.graphic = graphic;
	}
	
	public BufferedImage getGraphic(){
		return graphic;
	}
	
	
	public void doLogic() {
		
	}
	
	public void paint(Graphics graphics) {
		
	}

}