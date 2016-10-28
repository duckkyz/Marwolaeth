package marwolaeth;
import java.awt.Graphics;

public abstract class Projectile extends Sprite{
	
	public Projectile(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		//setTileWidth(tileWidth);
		//setTileHeight(tileHeight);
		//this.xLocationInFile = xLocationInFile;
		//this.yLocationInFile = yLocationInFile;
		
	}
	
	public abstract void doLogic();
	
}
