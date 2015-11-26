package connectfour.ui.gui.java.swing.events

import connectfour.util.observer.IObserver
import java.awt.event.MouseEvent
import connectfour.controller.Connect4GameController

import scala.swing.Publisher
import scala.swing.event.Event

//TODO: Aus java.swing herauslösen
case class UndoScalaSwingEvent() extends Event

class UndoEvent(observer: IObserver) extends EventAdapter(observer) with Publisher {

  override def mousePressed(e: MouseEvent) {
    val controller = Connect4GameController.getCurrentInstance
    controller.undoLastMove
    notifyObservers
    publish(new UndoScalaSwingEvent)
  }
}