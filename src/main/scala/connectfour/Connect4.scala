package connectfour

import com.google.inject.Guice
import com.google.inject.Injector

import connectfour.controller.Connect4GameController
import connectfour.ui.tui.TUI

/**
 * Created by maharr on 19.10.15.
 */
object Connect4new {

  def main(args: Array[String]) {
    val controller = Connect4GameController.getCurrentInstance
    //TODO: noch newGame implementieren
    //controller.newGame
    controller.addObserver(new connectfour.ui.gui.java.swing.SwingGUI)
    controller.addObserver(new TUI)
    controller.addObserver(new connectfour.ui.gui.scala.swing.SwingGUI)
    
    //TODO: Mit Injections arbeiten
//    val injector: Injector = Guice.createInjector(new GameControllerModule)
//    val controller: GameController = injector.getInstance(classOf[GameController])
//    controller.newGame
//    controller.addObserver(injector.getInstance(classOf[connectfour.ui.gui.java.swing.SwingGUI]))
//    controller.addObserver(injector.getInstance(classOf[TUI]))
//    controller.addObserver(injector.getInstance(classOf[connectfour.ui.gui.scala.swing.SwingGUI]))
  }
}
