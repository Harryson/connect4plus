package connectfour.ui.gui.swing.events

import connectfour.util.observer.IObserver
import java.awt.event.MouseEvent
import connectfour.controller.Connect4GameController

class UndoEvent(observer: IObserver) extends EventAdapter(observer) {

  override def mousePressed(e: MouseEvent) {
    val controller = Connect4GameController.getCurrentInstance
    controller.undoLastMove
    notifyObservers
  }
}