package connectfour.ui.gui.swing.events

import connectfour.util.observer.Observable
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import connectfour.controller.Connect4GameController
import connectfour.util.observer.IObserver

class NewGameEvent(controller: Connect4GameController, observer: IObserver) extends EventAdapter(observer) {

  override def mousePressed(e: MouseEvent) {
    // TODO
    //controller.newGame
    notifyObservers
  }
}