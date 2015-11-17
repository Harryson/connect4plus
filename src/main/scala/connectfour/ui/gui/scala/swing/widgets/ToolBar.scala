package connectfour.ui.gui.scala.swing.widgets

import connectfour.controller.IController

import scala.swing._
import scala.swing.event._

/**
 * Created by maharr on 13.11.15.
 */
class ToolBar (controller: IController) extends MenuBar {

  contents += new Menu("File") {
    mnemonic = Key.F
    contents += new MenuItem(Action("New Game") {controller.newGame})
    contents += new MenuItem(Action("Quit") { System.exit(0) })
  }

  contents += new Menu("Edit") {
    import scala.swing.MenuItem
    mnemonic = Key.E
    contents += new MenuItem(Action("Undo") {controller.undoStep})
    contents += new MenuItem(Action("Redo") {controller.redoStep})
  }

}
