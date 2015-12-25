package connectfour.ui.gui.java.swing.widgets

import java.awt.{Color, Graphics, Graphics2D, RenderingHints}
import javax.swing.JPanel

import connectfour.controller.Connect4GameControllerImpl
import connectfour.ui.gui.java.swing.events.{GUICoinMouseListener, MouseColumnObserver}
import connectfour.util.observer.{IObserver, IObserverWithArguments}

import scala.beans.BeanProperty

/**
 * Created by maharr on 19.12.15.
 */
class GUICoin(column: Int, observer: IObserverWithArguments) extends JPanel with IObserver {
  val RADIUS = 40;
  val X_POSITION = 18;
  val Y_POSITION = 20;
  
  @BeanProperty
  var color = Color.WHITE

  {
    this.setCoinBackAndForegroundColor
    this.addMouseListener(new GUICoinMouseListener(this));
    this.addMouseListener(new MouseColumnObserver(observer, column));
  }

  def setCoinBackAndForegroundColor {
    this.setBackground(Color.BLUE);
    this.setForeground(this.color);
  }
  

    
    override def paintComponent(g: Graphics) {
        super.paintComponent(g); // paints background
        setCoinBackAndForegroundColor
        
        val g2 = g.asInstanceOf[Graphics2D]
        
        // Activate Anti-Aliasing!
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        val radius = RADIUS;
        g2.fillOval(X_POSITION, Y_POSITION, radius, radius);
    }
    
    
    override def update() {
      val controller = Connect4GameControllerImpl.getCurrentInstance
    	controller.dropCoin(column);
    }
}