package marwolaeth;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HeroSelectScreen extends GameState{

	public HeroSelectScreen() {
		try {
			setBackgroundImage(ImageIO.read(new File("BackgroundImages/HeroSelect.png")));
		} catch(IOException ex) {}
		
		setBlankImage(new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB));
	}

	public void keyPressed(KeyEvent keyEvent) {
		if (keyEvent.getKeyCode()==KeyEvent.VK_S) {
			Game.setHero(new Wizard(0, 200,200));
			PlayScreen playScreen = new PlayScreen();
			getRootContainer().remove(this);
			getRootContainer().add(playScreen);
			getRootContainer().doLayout();
			getRootContainer().repaint();
		}
		else if (keyEvent.getKeyCode()==KeyEvent.VK_A) {
			Game.setHero(new Archer(0, 200, 200));
			PlayScreen playScreen = new PlayScreen();
			getRootContainer().remove(this);
			getRootContainer().add(playScreen);
			getRootContainer().doLayout();
			getRootContainer().repaint();
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE) {
				TitleScreen titleScreen = new TitleScreen();
				getRootContainer().remove(this);
				getRootContainer().add(titleScreen);
				getRootContainer().doLayout();
				getRootContainer().repaint();
				
		}
		
	}
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void paint(Graphics graphics) {
		requestFocus();
		super.paint(graphics);
		Graphics imageGraphics = getBlankImage().getGraphics();		//use imageGraphics to draw on the image
															
		//paint() should only be edited between these comments
		imageGraphics.drawImage(this.getBackgroundImage(), 0, 0, this);
		//paint() should only be edited between these comments
		
		if(getFullScreen()==true) {
			graphics.setColor(Color.BLACK);
			graphics.fillRect(0, 0, getMonitorWidth(), getMonitorHeight());
			graphics.drawImage(getBlankImage(), (getMonitorWidth()-getImageWidth())/2, (getMonitorHeight()-getImageHeight())/2, getImageWidth(), getImageHeight(), this);	//resize this to fit current resolution
		}
		else
			graphics.drawImage(getBlankImage(), 0, 0, getFrameWidth(), getFrameHeight(), this);	//resize this to fit current resolution
		imageGraphics.dispose();
		
		
	}

}
