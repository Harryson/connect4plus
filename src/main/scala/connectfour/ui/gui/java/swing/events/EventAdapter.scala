package connectfour.ui.gui.java.swing.events

import java.awt.event.{MouseEvent, MouseListener}

import connectfour.util.observer.{IObserver, Observable}

/**
 * Created by maharr on 19.12.15.
 */
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