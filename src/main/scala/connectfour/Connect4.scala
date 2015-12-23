package connectfour

import connectfour.controller.NewGameScalaSwingEvent

import scala.swing.Reactor

/**
 * Created by maharr on 19.10.15.
 */
object Connect4 extends Reactor {

  def main(args: Array[String]) {
    var registy = new ScalaSwingRegistry
    var controller = registy.gameController
    var tui = registy.tui

    listenTo(controller.newGameEventScala)

    reactions += {
      case e: NewGameScalaSwingEvent =>
        registy = new ScalaSwingRegistry
        controller = registy.gameController
        listenTo(controller.newGameEventScala)
    }


    registy.ui // Müsste man nicht machen, wenn in trait val statt def eingesetzt wird, ist dann aber nicht so flexibel

    //    new SwingGUI
    //        val controller = Connect4GameControllerImpl.getCurrentInstance
//        controller.addObserver(new TUI)
//        controller.addObserver(new connectfour.ui.gui.java.swing.SwingGUI)
//    controller.addObserver(new connectfour.ui.gui.scala.swing.SwingGUI)

    while(true){
      tui.processInputLine(scala.io.StdIn.readLine())
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
