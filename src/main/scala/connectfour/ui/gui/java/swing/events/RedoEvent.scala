package connectfour.ui.gui.java.swing.events

import connectfour.controller.IController
import connectfour.util.observer.IObserver

import java.awt.event.MouseEvent

class RedoEvent(controller: IController, observer: IObserver) extends EventAdapter(observer) {

  override def  mousePressed(e: MouseEvent) {
        controller.redoStep
        notifyObservers
    }
}