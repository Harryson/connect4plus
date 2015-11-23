package connectfour.ui.gui.swing.events

import java.awt.event.MouseEvent

import connectfour.controller.Connect4GameController
import connectfour.util.observer.IObserver

class RedoEvent(controller: Connect4GameController, observer: IObserver) extends EventAdapter(observer) {

  override def  mousePressed(e: MouseEvent) {
        //controller.redoStep
        //notifyObservers
    }
}