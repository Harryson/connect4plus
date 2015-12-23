package connectfour.ui.gui.java.swing.events

import java.awt.event.MouseEvent

import connectfour.controller.Connect4GameControllerImpl
import connectfour.util.observer.IObserver
import controller.GameController

/**
 * Created by maharr on 19.12.15.
 */
class NewGameEvent(controller: GameController, observer: IObserver) extends EventAdapter(observer) {

  override def mousePressed(e: MouseEvent) {
    Connect4GameControllerImpl.reset
    notifyObservers
  }
}