package connectfour.ui.gui.scala.swing.widgets

import connectfour.controller.Connect4GameController

import scala.swing._
import scala.swing.event._

/**
 * Created by maharr on 13.11.15.
 */
class ToolBar(gameController: Connect4GameController) extends MenuBar {
  contents += new Menu("File") {
    mnemonic = Key.F
    contents += new MenuItem(Action("New Game") {
      gameController.reset
    })
    contents += new MenuItem(Action("Quit") { System.exit(0) })
  }

  contents += new Menu("Edit") {
    mnemonic = Key.E
    //TODO: undo und redo implementieren
//    contents += new MenuItem(Action("Undo") { new UndoEvent})
//    contents += new MenuItem(Action("Redo") { new RedoEvent})
  }
}
