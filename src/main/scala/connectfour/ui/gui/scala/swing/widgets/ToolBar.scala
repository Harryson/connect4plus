package connectfour.ui.gui.scala.swing.widgets

import connectfour.controller.Connect4GameController
import connectfour.ui.gui.java.swing.events.{RedoEvent, UndoEvent}

import scala.swing._
import scala.swing.event._

/**
 * Created by maharr on 13.11.15.
 */
class ToolBar extends MenuBar {
  val controller = Connect4GameController.getCurrentInstance

  contents += new Menu("File") {
    mnemonic = Key.F
    //TODO newGame implementieren
    contents += new MenuItem(Action("New Game") { Connect4GameController.reset })
    contents += new MenuItem(Action("Quit") { System.exit(0) })
  }

  contents += new Menu("Edit") {
    mnemonic = Key.E
    //TODO: undo und redo implementieren
//    contents += new MenuItem(Action("Undo") { new UndoEvent})
//    contents += new MenuItem(Action("Redo") { new RedoEvent})
  }
}
