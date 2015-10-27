package connectfour.ui.gui.swing.events

import connectfour.util.observer.IObserver
import connectfour.util.observer.Observable

import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class GUICoinMouseListener(observer: IObserver) extends Observable with MouseListener {
  addObserver(observer)

  override def mousePressed(e: MouseEvent) {
    notifyObservers
  }

  override def mouseEntered(e: MouseEvent) {}
  override def mouseExited(e: MouseEvent) {}
  override def mouseReleased(e: MouseEvent) {}
  override def mouseClicked(e: MouseEvent) {}
}