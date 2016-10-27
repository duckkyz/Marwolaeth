package marwolaeth;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class GameState extends JPanel implements ActionListener, KeyListener{
	
	private static BufferedImage blankImage;									//gets painted on
	private static Image backgroundImage;
	private static JFrame frame;
	private static JPanel rootContainer;
	private static Graphics imageGraphics;
	private static boolean fullScreen = false;
	private static int imageHeight;
	private static int imageWidth;
	private static int monitorHeight;
	private static int monitorWidth;
	private static int numResolutionSizes = 6;
	private static int resolutionSizes[][] = { {1920, 1080},  {1600, 900}, {1366, 768}, {1280, 720}, {1152, 648}, {1024, 576} };			//All resolutions will be scaled assuming that resolutionSizes[0] is the standard
	private static Set keySet = new HashSet();
	
	public GameState() {
		addKeyListener(this);
	}
	
	public BufferedImage getBlankImage() {
		return blankImage;
	}
	public Image getBackgroundImage() {
		return backgroundImage;
	}
	public JFrame getFrame() {
		return frame;
	}
	public JPanel getRootContainer() {
		return rootContainer;
	}
	public Graphics getImageGraphics() {
		return imageGraphics;
	}
	public boolean getFullScreen() {
		return fullScreen;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public int getFrameHeight() {
		return frame.getHeight();
	}
	public int getFrameWidth() {
		return frame.getWidth();
	}
	public int getMonitorHeight() {
		return monitorHeight;
	}
	public int getMonitorWidth() {
		return monitorWidth;
	}
	public int getNumResolutionSizes() {
		return numResolutionSizes;
	}
	public int[][] getResolutionSizes() {
		return resolutionSizes;
	}
	public Set getKeySet() {
		return keySet;
	}
	
	public void setBlankImage(BufferedImage blankImage) {
		GameState.blankImage = blankImage;
	}
	public void setBackgroundImage(Image backgroundImage) {
		GameState.backgroundImage = backgroundImage;
	}
	public void setFrame(JFrame frame) {
		GameState.frame = frame;
	}
	public void setRootContainer(JPanel rootContainer) {
		GameState.rootContainer = rootContainer;
	}
	public void setImageGraphics(Graphics imageGraphics) {
		this.imageGraphics = imageGraphics;
	}
	public void setFullScreen(boolean fullScreen) {
		GameState.fullScreen = fullScreen;
	}
	public void setImageHeight(int imageHeight) {
		GameState.imageHeight = imageHeight;
	}
	public void setImageWidth(int imageWidth) {
		GameState.imageWidth = imageWidth;
	}
	public void setMonitorHeight(int monitorHeight) {
		GameState.monitorHeight = monitorHeight;
	}
	public void setMonitorWidth(int monitorWidth) {
		GameState.monitorWidth = monitorWidth;
	}
	
}