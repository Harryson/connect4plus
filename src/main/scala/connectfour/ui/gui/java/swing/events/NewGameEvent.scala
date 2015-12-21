package connectfour.ui.gui.java.swing.events

import java.awt.event.MouseEvent

import connectfour.controller.Connect4GameController
import connectfour.util.observer.IObserver

class NewGameEvent(controller: Connect4GameController, observer: IObserver) extends EventAdapter(observer){

  override def mousePressed(e: MouseEvent) {
    Connect4GameController.reset
    notifyObservers
  }
}