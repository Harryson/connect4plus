/**
 * 
 */
package connectfour.ui.gui.swing.events;

import connectfour.controller.IController;
import connectfour.ui.gui.java.swing.events.EventAdapter;
import connectfour.util.observer.IObserver;

import java.awt.event.MouseEvent;

/**
 * @author: Stefano Di Martino
 * @created: Jun 22, 2012
 */
public class NewGameEvent extends EventAdapter {
	private final IController controller;
	
    public NewGameEvent(final IController controller, final IObserver observer) {
        super(observer);
        this.controller = controller; 
    }
    
    @Override
    public void mouseClicked(final MouseEvent e) {}
    
    @Override
    public void mousePressed(final MouseEvent e) {
    	this.controller.newGame();
        this.notifyObservers();
    }
}
