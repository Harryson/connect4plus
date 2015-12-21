package connectfour.ui.gui.java.swing.events

import java.awt.event.{MouseEvent, MouseListener}

import connectfour.util.observer.{IObserverWithArguments, ObservableWithArguments}

/**
 * Created by maharr on 19.12.15.
 */
class MouseColumnObserver(observer: IObserverWithArguments, column: Int) extends ObservableWithArguments with MouseListener {
  this.addObserver(observer)

  override def mouseEntered(e: MouseEvent) {
    notifyObservers(column)
  }

  override def mouseExited(e: MouseEvent) {
    notifyObservers(column)
  }

  override def mouseReleased(e: MouseEvent) {
  }
  override def mouseClicked(e: MouseEvent) {
  }
  override def mousePressed(e: MouseEvent) {
  }
}