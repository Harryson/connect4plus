package controller

import connectfour.controller.Connect4GameController
import modelinterfaces.Player

/**
 * User: Stefano Di Martino
 * Date: 25.01.14
 * Time: 00:31
 */

trait GameController {
  def getPlayers: (Player, Player)

  def reset(): Unit

  def undo(): Unit

  def undoLastMove(): Unit

  def redo(): Unit

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
   * @param player computer or human
   * @return List of possibles moves
   */
  def generatePossibleMoves(player: Player): List[Move]

  /**
   * This is useful, if you want transform Moves, generated by a cloned GameController, for the current controller.
   * This has to be done when the Move instance has an instance of the cloned controller.
   * @return transformed Move for the current GameController.
   */
  def transferMoveToCurrentController(move: Move): Move

  /**
   * @return deep copy of controller!
   */
  def cloneController: GameController // clone() doesn't work. See https://issues.scala-lang.org/browse/SI-6760
}
