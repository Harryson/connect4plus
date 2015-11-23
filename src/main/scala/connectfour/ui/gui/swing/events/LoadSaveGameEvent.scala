package connectfour.ui.gui.swing.events

import java.awt.Frame
import java.awt.event.MouseEvent

import connectfour.controller.Connect4GameController
import connectfour.util.observer.IObserver

class LoadSaveGameEvent(frame: Frame, controller: Connect4GameController, observer: IObserver) extends EventAdapter(observer) {

  override def  mousePressed(e: MouseEvent) {
		/*val allSaveGameNames = controller.getAllSaveGameNames.toArray();
		val selection = if (allSaveGameNames.length > 0) allSaveGameNames(0) else ""

		val selectedSaveGameName = JOptionPane.showInputDialog(frame, "Pick a Name:", "ComboBox Dialog",
						JOptionPane.QUESTION_MESSAGE, null, allSaveGameNames,
						selection).asInstanceOf[String]

		if (selectedSaveGameName != null) {
			controller.loadSaveGame(selectedSaveGameName)
			notifyObservers
		}*/
	}
}