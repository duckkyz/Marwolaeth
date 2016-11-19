package marwolaeth.FloorTiles;

import java.awt.image.BufferedImage;

public class TopWall extends Wall {
	private static BufferedImage img;
	
	public TopWall(int x, int y){
		super(x,y);
		
		setActionStep((int) (6 + Math.floor(Math.random() * 0)));		//column
		setActionSequence((int) (0 + Math.floor(Math.random() * 0)));	//row
		this.setTileHeight(128);
		setBotHitBox(8);
	}
}
