package connectfour

import connectfour.controller.NewGameScalaSwingEvent

import scala.swing.Reactor

/**
 * Created by maharr on 19.10.15.
 */
object Connect4 extends Reactor {

  def main(args: Array[String]) {
    var registry = new ScalaSwingRegistry
    var controller = registry.gameController
    var tui = registry.tui

    listenTo(controller.newGameEventScala)

    reactions += {
      case e: NewGameScalaSwingEvent =>
        registry = new ScalaSwingRegistry
        controller = registry.gameController
        listenTo(controller.newGameEventScala)
        tui = registry.tui
    }

    //    controller.addObserver(new connectfour.ui.gui.java.swing.SwingGUI)
    tui.processInputLine("start")
  }
}
