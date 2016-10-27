package marwolaeth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PlayScreen extends GameState{

	//only do reocurring math operations once
	private double scaling;
	private int scaledBackgroundImageWidth;				
	private int scaledBackgroundImageHeight;
	private int bottomBarYLocation;
	private int rightBarXLocation;
	private int halfDiffDisImgHeight;
	private int halfDiffDisImgWidth;
	private int heroX;
	private int heroY;
	
	public PlayScreen() {
		try {
			setBackgroundImage(ImageIO.read(new File("BackgroundImages/Map.png")));
		} 
		catch(IOException ex) {
			
		}
		setBlankImage(new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB));
		scaling = (double) getResolutionSizes()[0][0]/ (double) getImageWidth();
		
		scaling = scaling*2;																			//Added for Ben's testing. Reverted other values
		
		scaledBackgroundImageWidth = (int) (getBackgroundImage().getWidth(this) / (scaling));		
		scaledBackgroundImageHeight = (int) (getBackgroundImage().getHeight(this) / (scaling));	
		bottomBarYLocation = (getMonitorHeight()-getImageHeight()) / 2 + getImageHeight();
		rightBarXLocation = (getMonitorWidth()-getImageWidth()) / 2 + getImageWidth();
		halfDiffDisImgHeight = (getMonitorHeight()-getImageHeight()) / 2;
		halfDiffDisImgWidth = (getMonitorWidth()-getImageWidth()) / 2;
		
	}

	public void keyPressed(KeyEvent keyEvent) {
		getKeySet().add(keyEvent.getKeyCode());
		
		if(keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE) {
			TitleScreen titleScreen = new TitleScreen();
			getRootContainer().remove(this);
			getRootContainer().add(titleScreen);
			getRootContainer().doLayout();
			getRootContainer().repaint();				
		}

	}

	
	public void keyReleased(KeyEvent keyEvent) {
		getKeySet().remove(keyEvent.getKeyCode());
		
	}

	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	public void prepaint(Hero hero, ArrayList<Drawable> drawables) {
		setImageGraphics(getBlankImage().getGraphics());		//use imageGraphics to draw on the image
		getImageGraphics().drawImage(this.getBackgroundImage(), 0, 0, this);
		
		hero.paint(getImageGraphics());
		heroX = hero.getXPos();
		heroY = hero.getYPos();
	
		for(int x = 0;x < drawables.size();x++) {
			drawables.get(x).paint(getImageGraphics());
		}
		
	}
	
	public void paint(Graphics graphics) {
		requestFocus();
		super.paint(graphics);
		
		if(getFullScreen()==true) {
			graphics.drawImage(getBlankImage(), (int) ((-1)*heroX/(scaling)+getImageWidth()/2+halfDiffDisImgWidth), (int) ((-1)*heroY/(scaling)+getImageHeight()/2+halfDiffDisImgHeight), scaledBackgroundImageWidth, scaledBackgroundImageHeight, this);
			graphics.setColor(Color.black);
			graphics.fillRect(0, 0, getMonitorWidth(), halfDiffDisImgHeight);																			//top
			graphics.fillRect(0, bottomBarYLocation, getMonitorWidth(), halfDiffDisImgHeight);														//bottom
			graphics.fillRect(0, 0, halfDiffDisImgWidth, getMonitorHeight());																			//left
			graphics.fillRect(rightBarXLocation, 0, halfDiffDisImgWidth, getMonitorHeight());															//right
		}
		else
			graphics.drawImage(getBlankImage(), (int) ((-1)*heroX/(scaling)+getImageWidth()/2), (int) ((-1)*heroY/(scaling)+getImageHeight()/2), scaledBackgroundImageWidth, scaledBackgroundImageHeight, this);
		
	}
}
