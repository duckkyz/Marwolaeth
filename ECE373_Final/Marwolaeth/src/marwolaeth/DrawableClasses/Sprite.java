package marwolaeth.DrawableClasses;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Sprite extends Drawable{
	//TODO : Hitboxes
	private boolean moveCasting = false;
	private boolean isMoving = false;
	private boolean completeingSequence = false;	//if the sprite needs to finish performing current action before performing others
	private int invokedAbility = 0;					//used to keep track of which ability was issued for cases when multiple abilities use the same actionSequence
	private int speed = 12;						//make only divisible by 2
	//private int speed = 30;							//make only divisible by 2
	private int direction = 0;
	private int health = 100;
	private int mana = 100;
	
	public Sprite(int direction, int spawnX, int spawnY) {
		setDirection(direction);
		setXPos(spawnX);
		setYPos(spawnY);
	}
	
	public boolean getMoveCasting() {
		return moveCasting;
	}
	
	public boolean getIsMoving() {
		return isMoving;
	}
	
	public boolean getCompleteingSequence() {
		return completeingSequence;
	}
	
	public int getInvokedAbility() {
		return invokedAbility;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMana() {
		return mana;
	}
	
	public void setMoveCasting(boolean moveCasting) {
		this.moveCasting = moveCasting;
		this.isMoving = moveCasting;					//this line is intentional
	}
	
	public void setIsMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public void setCompleteingSequence(boolean completeingSequence) {
		this.completeingSequence = completeingSequence;
	}
	
	public void setInvokedAbility(int invokedAbility) {
		this.invokedAbility = invokedAbility;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setMana(int mana) {
		this.mana = mana;
	}
	
	public void move() {
		if(isMoving == true) {
			setXPos(getXPos() + (int)(Math.round(Math.sin(Math.toRadians(direction)) * speed)));
			setYPos(getYPos() + (int)(Math.round(Math.cos(Math.toRadians(direction)) * speed * (-1))));
		}
	}
	
	public void attack(){
		
	}
	
	public BufferedImage rotate(BufferedImage image, float degreeOffset) 
	{
		float angle = getDirection()+degreeOffset;
	    float radianAngle = (float) Math.toRadians(angle) ; 

	    float sin = (float) Math.abs(Math.sin(radianAngle));
	    float cos = (float) Math.abs(Math.cos(radianAngle));

	    int w = image.getWidth() ;
	    int h = image.getHeight();

	    int neww = (int) Math.round(w * cos + h * sin);
	    int newh = (int) Math.round(h * cos + w * sin);

	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    GraphicsConfiguration gc = gd.getDefaultConfiguration();

	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();

	    //-----------------------MODIFIED--------------------------------------
	    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON) ;
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC) ;
	    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY) ;

	    AffineTransform at = AffineTransform.getTranslateInstance((neww-w)/2, (newh-h)/2);
	    at.rotate(radianAngle, w/2, h/2);
	    //---------------------------------------------------------------------

	    g.drawRenderedImage(image, at);
	    g.dispose();

	    return result;
	}
	
}