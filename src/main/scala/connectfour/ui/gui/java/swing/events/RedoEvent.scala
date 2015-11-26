package connectfour.ui.gui.java.swing.events

import java.awt.event.MouseEvent

import connectfour.controller.Connect4GameController
import connectfour.util.observer.IObserver

class RedoEvent(observer: IObserver) extends EventAdapter(observer) {

  override def mousePressed(e: MouseEvent) {
    val controller = Connect4GameController.getCurrentInstance
    //controller.redoStep
    //notifyObservers
  }
}