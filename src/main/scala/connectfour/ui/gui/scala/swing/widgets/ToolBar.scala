package connectfour.ui.gui.scala.swing.widgets

import connectfour.controller.Connect4GameControllerImpl

import scala.swing._
import scala.swing.event._

/**
 * Created by maharr on 13.11.15.
 */
class ToolBar extends MenuBar {
  contents += new Menu("File") {
    val controller = Connect4GameControllerImpl.getCurrentInstance
    mnemonic = Key.F
    contents += new MenuItem(Action("New Game") {
      Connect4GameControllerImpl.reset
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
