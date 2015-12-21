package connectfour.model

import ai.MiniMax
import connectfour.controller.Connect4GameController
import connectfour.util.observer.IObserver
import controller.{DropCoinScalaSwingEvent, GameController, NewGameScalaSwingEvent}
import modelinterfaces.Player

import scala.swing.Reactor


/**
 * Created by stefano on 19.02.14.
 */
class Connect4Computer(override val name: String, controller: GameController) extends IObserver with Player with Reactor {

  listenTo(controller.dropCoinEventScala)
  listenTo(controller.newGameEventScala)

  reactions += {
    case e: NewGameScalaSwingEvent =>
      listenTo(Connect4GameController.getCurrentInstance.dropCoinEventScala)
      listenTo(Connect4GameController.getCurrentInstance.newGameEventScala)
      draw
    case e: DropCoinScalaSwingEvent =>
      draw
  }

  private def draw {

    if (controller.getPlayerOnTurn == this && !controller.gameIsOver)
      MiniMax.getNextMove(controller).execute
  }

  override def toString: String = name

  override def update = draw
}