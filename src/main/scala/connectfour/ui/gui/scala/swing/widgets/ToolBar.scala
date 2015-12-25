package connectfour.ui.gui.scala.swing.widgets

import connectfour.controller.Connect4GameController

import scala.swing._
import scala.swing.event._

/**
 * Created by maharr on 13.11.15.
 */
class ToolBar(gameController: Connect4GameController) extends MenuBar {

  // constructor
  contents += new Menu("File") {
    mnemonic = Key.F
    contents += new MenuItem(Action("New Game") {
      mnemonic = Key.N
      gameController.reset()
    })
    contents += new MenuItem(Action("Quit") {
      mnemonic = Key.Q
      System.exit(0)
    })
  }

  contents += new Menu("Edit") {
    mnemonic = Key.E
    contents += new MenuItem(Action("Undo") {
      mnemonic = Key.U
      gameController.undo()
    })
    contents += new MenuItem(Action("Redo") {
      mnemonic = Key.R
      gameController.redo()
    })
  }
}