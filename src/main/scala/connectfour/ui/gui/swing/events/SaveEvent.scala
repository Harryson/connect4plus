package connectfour.ui.gui.swing.events

import connectfour.controller.IController

import javax.swing._
import java.awt._
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent

class SaveEvent(frame: Frame, controller: IController) extends MouseAdapter {

  override def mousePressed(e: MouseEvent) {
    val message = { "Savegame Name" }
    val saveGameName = JOptionPane.showInputDialog(frame, message,
      "Enter Savegame Name", JOptionPane.OK_CANCEL_OPTION)

    if (saveGameName != null) {
      this.controller.saveGame(saveGameName)
    }
  }
}