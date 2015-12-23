package connectfour.ui.gui.java.swing.events

import java.awt.event.MouseEvent

import connectfour.controller.{Connect4GameControllerImpl, UndoScalaSwingEvent}
import connectfour.util.observer.IObserver

import scala.swing.Publisher

/**
 * Created by maharr on 19.12.15.
 */
class UndoEvent(observer: IObserver) extends EventAdapter(observer) with Publisher {

  override def mousePressed(e: MouseEvent) {
    val controller = Connect4GameControllerImpl.getCurrentInstance
    controller.undoLastMove
    notifyObservers
    publish(new UndoScalaSwingEvent)
  }
}