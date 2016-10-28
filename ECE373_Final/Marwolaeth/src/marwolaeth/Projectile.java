package marwolaeth;
import java.awt.Graphics;

public class Projectile extends Sprite{

	private Sprite sprite;
	private int xLocationInFile;
	private int yLocationInFile;
	private int numFrames;
	
	public Projectile(Sprite sprite, int direction, int spawnX, int spawnY, int tileWidth, int tileHeight, int xLocationInFile, int yLocationInFile, int numFrames) {
		this.sprite = sprite;
		setDirection(direction);
		setXPos(spawnX);
		setYPos(spawnY);
		setTileWidth(tileWidth);
		setTileHeight(tileHeight);
		this.xLocationInFile = xLocationInFile;
		this.yLocationInFile = yLocationInFile;
		
	}
	
	public void doLogic() {
		if(getActionStep()<(numFrames-1))
			setActionStep(getActionStep()+1);
		else
			setActionStep(0);
	}
	
	public void paint(Graphics imageGraphics) {
		
		imageGraphics.drawImage(getGraphic(getDirection()), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), xLocationInFile+getActionStep()*getTileWidth(), yLocationInFile, xLocationInFile+getActionStep()*getTileWidth()+getTileWidth(), yLocationInFile+getTileHeight(), null);
		
	}

}
