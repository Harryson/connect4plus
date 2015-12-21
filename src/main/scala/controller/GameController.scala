package controller

import connectfour.ui.gui.scala.swing.events.{DropCoinEventScala, NewGameEventScala}
import modelinterfaces.{Move, Player}

import scala.swing.event.Event

/**
 * User: Stefano Di Martino
 * Date: 25.01.14
 * Time: 00:31
 */

case class DropCoinScalaSwingEvent() extends Event

case class NewGameScalaSwingEvent() extends Event

trait GameController {
  var dropCoinEventScala: DropCoinEventScala
  var newGameEventScala: NewGameEventScala

  def getPlayers: (Player, Player)
  
  def undoLastMove

  def getPlayerOnTurn: Player

  def noMovePossible(player: Player): Boolean

  def getScore(player: Player): Int
  
  def getWinner: String

  def gameIsOver: Boolean

  /**
   * Generates possibles moves for a player. CAUTION: It is strongly recommended to do this on a cloned controller,
   * because it could have side effects on the current GameController, if the moves gets executed!
   * Use a cloned controller and transformMoveFromClonedControllerForCurrentController() afterwards!
   *
   * @param player
   * @return List of possibles moves
   */
  def generatePossibleMoves(player: Player): List[Move]

  /**
   * This is useful, if you want transform Moves, generated by a cloned GameController, for the current controller
   * @return transformed Move for the current GameController.
   */
  def transformMoveFromClonedControllerForCurrentController(move: Move): Move

  /**
   * @return deep copy of controller!
   */
  def cloneController: GameController // clone() doesn't work. See https://issues.scala-lang.org/browse/SI-6760
}
