package connectfour

import connectfour.controller.Connect4GameController
import connectfour.ui.gui.swing.SwingGUI
import connectfour.ui.tui.TUI

/**
 * Created by maharr on 19.10.15.
 */
object Connect4new {

  def main(args: Array[String]) {
    val controller = Connect4GameController.getCurrentInstance
    controller.addObserver(new SwingGUI)
    controller.addObserver(new TUI)
  }
}
