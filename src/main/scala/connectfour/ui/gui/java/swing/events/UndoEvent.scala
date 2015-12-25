package connectfour.ui.gui.java.swing.events

import java.awt.event.MouseEvent

import connectfour.controller.{Connect4GameControllerComponent, UndoScalaSwingEvent}
import connectfour.util.observer.IObserver

import scala.swing.Publisher

/**
 * Created by maharr on 19.12.15.
 */
class UndoEvent(observer: IObserver) extends EventAdapter(observer) with Publisher {
  this: Connect4GameControllerComponent =>

  override def mousePressed(e: MouseEvent) {
    val controller = gameController
    controller.undoLastMove
    notifyObservers
    publish(new UndoScalaSwingEvent)
  }
}