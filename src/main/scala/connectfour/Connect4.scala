package connectfour

import connectfour.ui.gui.scala.swing.SwingGUI
import connectfour.ui.tui.TUI

/**
 * Created by maharr on 19.10.15.
 */
object Connect4 {

  def main(args: Array[String]) {
    val tui = new TUI
    new SwingGUI
//        val controller = Connect4GameController.getCurrentInstance
//        controller.addObserver(new TUI)
//        controller.addObserver(new connectfour.ui.gui.java.swing.SwingGUI)
//    controller.addObserver(new connectfour.ui.gui.scala.swing.SwingGUI)

    while(true){
      tui.processInputLine(readLine())
    }
    
    //TODO: Mit Injections arbeiten
//    val injector: Injector = Guice.createInjector(new GameControllerModule)
//    val controller: GameController = injector.getInstance(classOf[GameController])
//    controller.newGame
//    controller.addObserver(injector.getInstance(classOf[connectfour.ui.gui.java.swing.SwingGUI]))
//    controller.addObserver(injector.getInstance(classOf[TUI]))
//    controller.addObserver(injector.getInstance(classOf[connectfour.ui.gui.scala.swing.SwingGUI]))
  }
}
