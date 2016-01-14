package connectfour.model

import ai.MiniMax
import connectfour.controller._
import connectfour.events.{NewGameScalaSwingEvent, DropCoinScalaSwingEvent}
import modelinterfaces.Player

import scala.swing.Reactor


/**
 * Created by stefano on 19.02.14.
 */
class Connect4Computer(override val name: String, controller: Connect4GameController)
  extends Player with Reactor {

  listenToEvents()

  reactions += {
    case e: NewGameScalaSwingEvent =>
      listenToEvents()
      draw()
    case e: DropCoinScalaSwingEvent =>
      draw()
  }

  private def listenToEvents() {
    listenTo(controller.dropCoinEventScala)
    listenTo(controller.newGameEventScala)
  }

  private def draw() {
    if (controller.getPlayerOnTurn == this && !controller.gameIsOver)
      MiniMax.getNextMove(controller).execute
  }

  override def toString: String = name
}