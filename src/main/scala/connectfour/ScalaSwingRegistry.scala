package connectfour

import connectfour.controller.{Connect4GameController, Connect4GameControllerImpl}
import connectfour.ui.UI
import connectfour.ui.gui.scala.swing.SwingGUI
import connectfour.ui.tui.TUI

/**
 * Created by maharr on 23.12.15.
 */
object ScalaSwingRegistry extends Components {
  override def gameController: Connect4GameController = new Connect4GameControllerImpl

  override def ui: UI = new SwingGUI

  override def tui: UI = new TUI
}
