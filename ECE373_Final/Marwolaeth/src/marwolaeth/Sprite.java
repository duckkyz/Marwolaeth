package marwolaeth;

public class Sprite extends Drawable{

	private boolean isMoving = false;
	private int speed = 12;						//make only divisible by 2
	private int direction = 0;
	
	public Sprite() {
		// TODO Auto-generated constructor stub
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setIsMoving(Boolean isMoving) {
		this.isMoving = isMoving;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void move() {
		if(isMoving == true) {
			switch(direction){
			case 0:
				this.setYPos(getYPos()+(-1)*this.speed);
				break;
			case 45:
				this.setXPos(getXPos()+((int)Math.floor(this.speed/1.414)));
				this.setYPos(getYPos()+(-1)*((int)Math.floor(this.speed/1.414)));
				break;
			case 90:
				this.setXPos(getXPos()+this.speed);
				break;
			case 135:
				this.setXPos(getXPos()+((int)Math.floor(this.speed/1.414)));
				this.setYPos(getYPos()+((int)Math.floor(this.speed/1.414)));
				break;
			case 180:
				this.setYPos(getYPos()+this.speed);
				break;
			case 225:
				this.setXPos(getXPos()+(-1)*((int)Math.floor(this.speed/1.414)));
				this.setYPos(getYPos()+((int)Math.floor(this.speed/1.414)));
				break;
			case 270:
				this.setXPos(getXPos()+(-1)*this.speed);
				break;
			case 315:
				this.setXPos(getXPos()+(-1)*((int)Math.floor(this.speed/1.414)));
				this.setYPos(getYPos()+(-1)*((int)Math.floor(this.speed/1.414)));
				break;
				
				
			}
		}
	}

}