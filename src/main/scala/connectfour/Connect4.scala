package connectfour

import com.google.inject.{Guice, Injector}
import connectfour.controller.GameController
import connectfour.ui.tui.TUI

/**
 * Created by maharr on 19.10.15.
 */
object Connect4new {

  def main(args: Array[String]) {
    val injector: Injector = Guice.createInjector(new GameControllerModule)
    val controller: GameController = injector.getInstance(classOf[GameController])
    controller.newGame
    controller.addObserver(injector.getInstance(classOf[connectfour.ui.gui.java.swing.SwingGUI]))
    controller.addObserver(injector.getInstance(classOf[TUI]))
    controller.addObserver(injector.getInstance(classOf[connectfour.ui.gui.scala.swing.SwingGUI]))
  }
}
