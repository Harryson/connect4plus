package connectfour.ui.gui.swing.events

import connectfour.controller.IController
import connectfour.util.observer.IObserver
import connectfour.util.observer.Observable

import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class EventAdapter(observer: IObserver) extends Observable with MouseListener {

  addObserver(observer)

  override def mouseReleased(e: MouseEvent) {
  }

  override def mouseClicked(e: MouseEvent) {
  }

  override def mouseEntered(e: MouseEvent) {
  }

  override def mouseExited(e: MouseEvent) {
  }

  override def mousePressed(e: MouseEvent) {
  }
}