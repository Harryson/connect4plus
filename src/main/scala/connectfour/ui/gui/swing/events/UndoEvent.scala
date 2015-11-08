package connectfour.ui.gui.swing.events

import connectfour.util.observer.IObserver
import java.awt.event.MouseEvent
import connectfour.controller.Connect4GameController

class UndoEvent(controller: Connect4GameController, observer: IObserver) extends EventAdapter(observer) {

  override def mousePressed(e: MouseEvent) {
        controller.undoLastMove
        notifyObservers
    }
}