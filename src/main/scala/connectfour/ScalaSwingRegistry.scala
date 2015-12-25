package connectfour

import connectfour.controller.{Connect4GameController, Connect4GameControllerImpl}
/**
 * Created by maharr on 23.12.15.
 */
class ScalaSwingRegistry extends Components {
  override val gameController: Connect4GameController = new Connect4GameControllerImpl
  override val tui = new TUI
  override val scalaSwingGUI = new ScalaSwingGUI
}