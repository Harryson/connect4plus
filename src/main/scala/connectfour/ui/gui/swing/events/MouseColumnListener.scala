package connectfour.ui.gui.swing.events

import java.awt.event.MouseEvent
import java.awt.event.MouseListener

import connectfour.util.observer.IObserverWithArguments
import connectfour.util.observer.ObservableWithArguments

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