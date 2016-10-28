package marwolaeth;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall extends Drawable {
	
	
	public Wall(int x, int y){
		try{
			this.setXPos(x);
			this.setYPos(y);
			setGraphic(ImageIO.read(new File("DrawableImages/Wall.png")));
		}
		catch(IOException ex){
			
		}
	}
	
	public void paint(Graphics imageGraphics){
		imageGraphics.drawImage(getGraphic(), getXPos(), getYPos(), null);
	}
}
