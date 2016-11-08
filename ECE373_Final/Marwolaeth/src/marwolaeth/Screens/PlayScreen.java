package marwolaeth.Screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import marwolaeth.Game;
import marwolaeth.ViewController;
import marwolaeth.DrawableClasses.Drawable;
import marwolaeth.DrawableClasses.Hero;
import marwolaeth.FloorTiles.GrassTile;

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
	private int percentHealth;				//needed for drawing UI
	private int percentMana;
	private int heroResourcesXLocation = 0;		//for easy change if we want to move the resource location
	private int heroResourcesYLocation = 0;
	private Image heroResources;			//health/mana bar
	
	private boolean testHealthBar = false;	//used for testing. Remove when done
	
	public PlayScreen() {
		try {
			setBackgroundImage(ImageIO.read(new File("Background_Images/Map.png")));
			heroResources = ImageIO.read(new File("UI_Images/HeroResources.png"));
		} 
		catch(IOException ex) {
			
		}
		makeMap();
		setBlankImage(new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB));
		scaling = (double) getResolutionSizes()[0][0]/ (double) getImageWidth();
		
		//scaling = scaling*1.5;																			//Added for Ben's testing. Reverted other values
		
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
		// TODO Auto-generated method stub.
		
	}

	
	public void actionPerformed(ActionEvent arg0) {
		
	}
	
	public void makeMap(){
		setImageGraphics(getBlankImage().getGraphics());
		BufferedImage img = new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB);
		GrassTile g = new GrassTile(0,0);
		
		for(int i = 0; i < 60; ++i){
			for(int j = 0; j < 34; ++j){
				g.setXPos(i*64);
				g.setYPos(j*64);
				g.setActionStep((int) (3 + Math.floor(Math.random() * 3)));
				g.setActionSequence((int) (3 + Math.floor(Math.random() * 0)));
				g.paint(img.getGraphics());
			}
		}
		//try {
		//	ImageIO.write(img, "png", new File("test.png"));
		//} catch (IOException e) {
		//	// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		setBackgroundImage(img);

		//setBlankImage(img);
		//return img;
		//setImageGraphics(getImageGraphics());
	}
	
	public void prepaint(Hero hero, ArrayList<Drawable> drawables) {
		setImageGraphics(getBlankImage().getGraphics());		//use imageGraphics to draw on the image
		getImageGraphics().drawImage(this.getBackgroundImage(), 0, 0, this);

		
		for(int x = 0;x < drawables.size();x++) {
			drawables.get(x).paint(getImageGraphics());
		}
		
		hero.paint(getImageGraphics());
		heroX = hero.getXPos()+32;
		heroY = hero.getYPos()+32;
		percentHealth = (155*hero.getHealth())/100;			//155 is the px width of the health bar
		percentMana = (141*hero.getMana())/100;
			
		if(hero.getHealth()>0 & testHealthBar == false) {								//drawns health bar. Remove at some point
			hero.setHealth(hero.getHealth()-1);
			hero.setMana(hero.getMana()-1);
		}
		else
			testHealthBar = true;
		if(hero.getHealth()<100 & testHealthBar == true) {								//drawns health bar. Remove at some point
			hero.setHealth(hero.getHealth()+1);
			hero.setMana(hero.getMana()+1);
		}
		else
			testHealthBar = false;
	}
	
	public void paint(Graphics graphics) {
		requestFocus();
		super.paint(graphics);
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, getFrameWidth(), getFrameHeight());		//makes whatever is outside the image black
		if(getFullScreen()==true) {
			graphics.drawImage(getBlankImage(), (int) ((-1)*(heroX)/(scaling)+getImageWidth()/2+halfDiffDisImgWidth), (int) ((-1)*(heroY)/(scaling)+getImageHeight()/2+halfDiffDisImgHeight), scaledBackgroundImageWidth, scaledBackgroundImageHeight, this);
			graphics.fillRect(0, 0, getMonitorWidth(), halfDiffDisImgHeight);																			//top
			graphics.fillRect(0, bottomBarYLocation, getMonitorWidth(), halfDiffDisImgHeight);														//bottom
			graphics.fillRect(0, 0, halfDiffDisImgWidth, getMonitorHeight());																			//left
			graphics.fillRect(rightBarXLocation, 0, halfDiffDisImgWidth, getMonitorHeight());															//right
		}
		else
			graphics.drawImage(getBlankImage(), (int) ((-1)*(heroX)/(scaling)+getImageWidth()/2), (int) ((-1)*(heroY)/(scaling)+getImageHeight()/2), scaledBackgroundImageWidth, scaledBackgroundImageHeight, this);
		
		//draw UI
		//getGraphic(), getXPos(), getYPos(), getXPos()+getTileWidth(), getYPos()+getTileHeight(), getActionStep()*getTileWidth(), getActionSequence()*getTileHeight(), getActionStep()*getTileWidth()+getTileWidth(), getActionSequence()*getTileHeight()+getTileHeight(), null
		graphics.drawImage(heroResources, heroResourcesXLocation, heroResourcesYLocation, heroResourcesXLocation+291, heroResourcesYLocation+198, 0, 0, 291, 198, null);											//draws UI boarder
		graphics.drawImage(heroResources, heroResourcesXLocation+119, heroResourcesYLocation+41, heroResourcesXLocation+119+percentHealth, heroResourcesYLocation+41+34, 0, 200, percentHealth, 200+34, null);		//draws health bar
		graphics.drawImage(heroResources, heroResourcesXLocation+101, heroResourcesYLocation+86, heroResourcesXLocation+101+percentMana, heroResourcesYLocation+86+26, 0, 233, percentMana, 259, null);		//draws mana bar
	}
}
