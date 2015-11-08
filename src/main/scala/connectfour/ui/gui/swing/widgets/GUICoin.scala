package connectfour.ui.gui.swing.widgets

import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.swing.JPanel
import connectfour.ui.gui.swing.events.GUICoinMouseListener
import connectfour.ui.gui.swing.events.MouseColumnObserver
import connectfour.util.observer.IObserver
import connectfour.util.observer.IObserverWithArguments
import scala.beans.BeanProperty
import connectfour.controller.Connect4GameController

class GUICoin(controller: Connect4GameController, column: Int, observer: IObserverWithArguments) extends JPanel with IObserver {
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
    	controller.dropCoin(column);
    }
}