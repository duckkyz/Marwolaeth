package marwolaeth;

import java.awt.Graphics;

public class Arrow extends Projectile{

	public Arrow(int direction, int spawnX, int spawnY) {
		super(direction, spawnX, spawnY);
		//setTileWidth();
		//setTileHeight();
	}

	public void doLogic() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	{
		if(getActionStep()<(numFrames-1))
			setActionStep(getActionStep()+1);
		else
			setActionStep(0);
	}
	*/
	public void paint(Graphics imageGraphics) {
		
		//imageGraphics.drawImage(getGraphic(getDirection()), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), xLocationInFile+getActionStep()*getTileWidth(), yLocationInFile, xLocationInFile+getActionStep()*getTileWidth()+getTileWidth(), yLocationInFile+getTileHeight(), null);
		
	}

}
