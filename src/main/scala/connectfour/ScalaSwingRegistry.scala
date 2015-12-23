package connectfour

import connectfour.controller.{Connect4GameController, Connect4GameControllerImpl}
import connectfour.ui.UI

/**
 * Created by maharr on 23.12.15.
 */
class ScalaSwingRegistry extends Components {
  override val gameController: Connect4GameController = new Connect4GameControllerImpl

  override val ui: UI = new SwingGUI

  override val tui: TUI = new TUI
}
