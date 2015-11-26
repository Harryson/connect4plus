package connectfour.ui.gui.java.swing.events

import connectfour.util.observer.IObserver
import connectfour.util.observer.Observable
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import connectfour.controller.Connect4GameController

class ArrowMouseListener(controller: Connect4GameController, observer: IObserver, column: Int) extends Observable with MouseListener {
  
  addObserver(observer)

  override def mouseReleased(e: MouseEvent) {
    controller.dropCoin(this.column)
  }

  override def mouseClicked(e: MouseEvent) {
    notifyObservers
  }

  override def mouseEntered(e: MouseEvent) {
    notifyObservers
  }

  override def mouseExited(e: MouseEvent) {
    notifyObservers
  }

  override def mousePressed(e: MouseEvent) {
    notifyObservers
  }
}