package marwolaeth.Listeners;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import javax.swing.JPanel;
import javax.swing.Timer;

import marwolaeth.Game;
import marwolaeth.ViewController;
import marwolaeth.Screens.PlayScreen;

public class CustomContainerListener implements ContainerListener {
    
	private JPanel rootContainer;
	private Timer timer;
	
	public CustomContainerListener(JPanel rootContainer, Timer timer) {
		this.rootContainer = rootContainer;
		this.timer = timer;
	}
	
	
	public void componentAdded(ContainerEvent e) {
		try {
				if(rootContainer.getComponent(0) instanceof PlayScreen) {
					timer.start();
				}
				else{
					timer.stop();
					System.out.println("Leaving game");
					ViewController.setGame(new Game());
				}
		} 
  	  	catch (ArrayIndexOutOfBoundsException ev) {

			    //System.out.println("ArrayIndexOutOfBoundsException: " + e.getMessage());
  	  	} 
	}

    public void componentRemoved(ContainerEvent e) {
       
    }
}
