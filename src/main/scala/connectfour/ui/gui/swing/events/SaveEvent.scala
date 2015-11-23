package connectfour.ui.gui.swing.events


import javax.swing._
import java.awt._
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import connectfour.controller.Connect4GameController

class SaveEvent(frame: Frame, controller: Connect4GameController) extends MouseAdapter {

  override def mousePressed(e: MouseEvent) {
    val message = { "Savegame Name" }
    val saveGameName = JOptionPane.showInputDialog(frame, message,
      "Enter Savegame Name", JOptionPane.OK_CANCEL_OPTION)

    if (saveGameName != null) {
      // TODO
      //this.controller.saveGame(saveGameName)
    }
  }
}