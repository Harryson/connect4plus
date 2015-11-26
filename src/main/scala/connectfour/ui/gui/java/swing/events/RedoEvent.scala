package connectfour.ui.gui.java.swing.events

import java.awt.event.MouseEvent

import connectfour.controller.Connect4GameController
import connectfour.util.observer.IObserver

import scala.swing.Publisher
import scala.swing.event.Event

//TODO: Aus java.swing herauslösen
case class RedoScalaSwingEvent() extends Event

class RedoEvent(observer: IObserver) extends EventAdapter(observer) with Publisher {

  override def mousePressed(e: MouseEvent) {
    val controller = Connect4GameController.getCurrentInstance
    //controller.redoStep
    //notifyObservers
    publish(new RedoScalaSwingEvent)
  }
}