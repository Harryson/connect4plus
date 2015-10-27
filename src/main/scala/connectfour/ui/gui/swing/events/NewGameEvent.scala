package connectfour.ui.gui.swing.events

import connectfour.controller.IController
import connectfour.util.observer.IObserver
import connectfour.util.observer.Observable

import java.awt.event.MouseEvent
import java.awt.event.MouseListener

class NewGameEvent(controller: IController, observer: IObserver) extends EventAdapter(observer) {

  override def mousePressed(e: MouseEvent) {
    controller.newGame
    notifyObservers
  }
}