package connectfour

import connectfour.controller.{Connect4GameController, Connect4GameControllerImpl}
import connectfour.ui.UI

/**
 * Created by maharr on 23.12.15.
 */
object JavaSwingRegistry extends Components {
  override val gameController: Connect4GameController = new Connect4GameControllerImpl

  override def tui: UI = new TUI

  override def ui: UI = new SwingGUI
}
