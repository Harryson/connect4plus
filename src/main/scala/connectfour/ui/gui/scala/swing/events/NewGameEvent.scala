package connectfour.ui.gui.scala.swing.events

import connectfour.controller.NewGameScalaSwingEvent

import scala.swing.Publisher

/**
 * Created by maharr on 19.12.15.
 */
class NewGameEvent extends Publisher {
  def newGame(): Unit = {
    publish(new NewGameScalaSwingEvent)
  }
}