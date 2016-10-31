package marwolaeth.Screens;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
//
//Try using source folder to load things locally
//
//
//


public class TitleScreen extends GameState{

	public TitleScreen() {
		//TODO why is this here if theres already a constructor for TitleScreen?
		try {
			setBackgroundImage(ImageIO.read(new File("Background_Images/Title.png")));
		} 
		catch(IOException ex) {
			System.out.println("I'm catching");
		}
		
		setBlankImage(new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB));
	}
	
	//This constructor should only be called once
	public TitleScreen(JFrame frame, JPanel rootContainer) {
		setFrame(frame);
		setRootContainer(rootContainer);
		try {
			setBackgroundImage(ImageIO.read(new File("Background_Images/Title.png")));
		} 
		catch(IOException ex) {
			
		}
		
		setBlankImage(new BufferedImage(getBackgroundImage().getWidth(this), getBackgroundImage().getHeight(this), BufferedImage.TYPE_INT_ARGB));
		setupResolution(getResolutionSizes(), getNumResolutionSizes());
		
	}
	
	public void setupResolution(int[][] resolutionSizes, int numResolutionSizes) {
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setMonitorHeight(screenSize.height);
		setMonitorWidth(screenSize.width);
		
		for(int x = 0;x < numResolutionSizes; x++) {
			if(getMonitorWidth() >= resolutionSizes[x][0])
				if(getMonitorHeight() >= resolutionSizes[x][1]) {
					getFrame().setSize(resolutionSizes[x][0], resolutionSizes[x][1]);
					break;
				}
		}
		
		setImageWidth(getFrameWidth());
		setImageHeight(getFrameHeight());
		getFrame().setLocation((getMonitorWidth()-getFrameWidth())/2, (getMonitorHeight()-getFrameHeight())/2);
		//frame.setUndecorated(true);
		getFrame().setVisible(true);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		if (keyEvent.getKeyCode()==KeyEvent.VK_S) {
			HeroSelectScreen heroSelectScreen = new HeroSelectScreen();
			getRootContainer().remove(this);
			getRootContainer().add(heroSelectScreen);
			getRootContainer().doLayout();
			getRootContainer().repaint();
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_H) {
			HelpScreen helpScreen = new HelpScreen();
			getRootContainer().remove(this);
			getRootContainer().add(helpScreen);
			getRootContainer().doLayout();
			getRootContainer().repaint();
		}
		else if(keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE) {
			System.exit(0);
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
		//System.out.println(game.getRootContainer().getHeight());
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
