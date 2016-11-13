package marwolaeth;

import java.awt.BorderLayout;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import marwolaeth.Listeners.CustomContainerListener;
import marwolaeth.Screens.GameState;
import marwolaeth.Screens.PlayScreen;
import marwolaeth.Screens.TitleScreen;


public class ViewController {
	
	private JFrame frame;
	private static Game game;
	private JPanel rootContainer;
	
	private Timer timer;
	private final int timerDelay = 10;
	
	
	public ViewController() {
		frame = new JFrame("Marwolaeth");
		game = new Game();
		rootContainer = new JPanel();
		rootContainer.setLayout(new BorderLayout());
		frame.getContentPane().add(rootContainer, BorderLayout.CENTER);
		frame.setResizable(false);										//Prevents user from manually resizing frame.
		GameState titleScreen = new TitleScreen(frame, rootContainer);
		rootContainer.add(titleScreen);
		
		//Next 3 lines: Turn cursor transparent
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);
		
		gameTimer();
		rootContainer.addContainerListener(new CustomContainerListener(rootContainer, timer));
		frame.setVisible(true);
	}
	
	public static void setGame(Game game){
		ViewController.game = game;
	}
	
	
	public void gameTimer() {
		timer = new Timer(timerDelay, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				((PlayScreen) rootContainer.getComponent(0)).prepaint(game.getHero(), game.getDrawables());
				game.doGameLogic(((PlayScreen)rootContainer.getComponent(0)).getKeySet());			
				rootContainer.repaint();
					
			}
			
		});
		timer.stop();
	}

}
