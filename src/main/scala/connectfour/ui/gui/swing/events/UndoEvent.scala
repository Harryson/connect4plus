package connectfour.ui.gui.swing.events

import connectfour.controller.IController
import connectfour.util.observer.IObserver

import java.awt.event.MouseEvent

class UndoEvent(controller: IController, observer: IObserver) extends EventAdapter(observer) {

  override def mousePressed(e: MouseEvent) {
        controller.undoStep
        notifyObservers
    }
}