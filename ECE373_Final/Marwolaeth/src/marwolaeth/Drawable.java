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


package marwolaeth;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Drawable {

	private int xPos;
	private int yPos;
	private int xTileSize;
	private int yTileSize;
	private ArrayList<Image> graphicList = new ArrayList<Image>();
	
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
	
	public int getXTileSize() {
		return xTileSize;
	}
	
	public int getYTileSize() {
		return yTileSize;
	}
	
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}
	
	public void setXTileSize(int xTileSize) {
		this.xTileSize = xTileSize;
	}
	
	public void setYTileSize(int yTileSize) {
		this.yTileSize = yTileSize;
	}
	
	public void addGraphic(Image img){
		graphicList.add(img);
	}
	
	public void setImage(Image img){
		graphicList.add(img);
	}
	
	public Image getGraphic(int Direction){
		//Test line
		int dummyDirection = 0;
		return this.graphicList.get(dummyDirection);
	}
	
	
	public void doLogic() {
		
	}
	
	public void paint(Graphics graphics) {
		
	}

}