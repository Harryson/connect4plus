package connectfour

import com.google.inject.Guice
import com.google.inject.Injector

import connectfour.controller.Connect4GameController
import connectfour.ui.gui.swing.SwingGUI
import connectfour.ui.tui.TUI

/**
 * Created by maharr on 19.10.15.
 */
object Connect4new {

  def main(args: Array[String]) {
    // TODO
    //controller.newGame
    val controller = Connect4GameController.getCurrentInstance
    controller.addObserver(new SwingGUI)
    controller.addObserver(new TUI)
  }
}
