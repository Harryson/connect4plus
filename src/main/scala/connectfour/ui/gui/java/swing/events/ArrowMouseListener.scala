package connectfour.ui.gui.java.swing.events

import java.awt.event.{MouseEvent, MouseListener}

import connectfour.controller.Connect4GameController
import connectfour.util.observer.{IObserver, Observable}

/**
 * Created by maharr on 19.12.15.
 */
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