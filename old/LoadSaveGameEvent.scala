package connectfour.ui.gui.swing.events

import connectfour.controller.IController
import connectfour.ui.gui.java.swing.events.EventAdapter
;
import connectfour.util.observer.IObserver;
import connectfour.util.observer.IObserverWithArguments;
import connectfour.util.observer.ObservableWithArguments;

import javax.swing._
import java.awt._
import java.awt.event.MouseEvent;

class LoadSaveGameEvent(frame: Frame, controller: IController, observer: IObserver) extends EventAdapter(observer) {

  override def  mousePressed(e: MouseEvent) {
		val allSaveGameNames = controller.getAllSaveGameNames.toArray();
		val selection = if (allSaveGameNames.length > 0) allSaveGameNames(0) else ""

		val selectedSaveGameName = JOptionPane.showInputDialog(frame, "Pick a Name:", "ComboBox Dialog",
						JOptionPane.QUESTION_MESSAGE, null, allSaveGameNames,
						selection).asInstanceOf[String]

		if (selectedSaveGameName != null) {
			controller.loadSaveGame(selectedSaveGameName)
			notifyObservers
		}
	}
}