package ai

import modelinterfaces._
import controller.GameController
import scala.concurrent._

/**
 * User: Stefano Di Martino
 * Date: 25.01.14
 * Time: 00:13
 */
object MiniMax {
  val DIFFICULTY_MIDDLE = 4
  val DIFFICULTY_HEAVY = 6

  /**
   * @return NoMovePossible, if no move is possible, else the best possible move
   */
  def getNextMove(controller: GameController, maxDepth: Int = DIFFICULTY_HEAVY): Move = {
    val controllerCopy = controller.cloneController
    var savedMove: Move = NoMovePossible

    def miniMax(currentPlayer: Player, depth: Int): Double = {
      if (depth == 0 || controllerCopy.gameIsOver || controllerCopy.noMovePossible(currentPlayer))
        return controllerCopy.getScore(currentPlayer).asInstanceOf[Double]

      var maxValue = Double.NegativeInfinity
      val possibleMoves: List[Move] = controllerCopy.generatePossibleMoves(currentPlayer)

      for (move <- possibleMoves) {
        move.execute
        val value = -miniMax(controllerCopy.getPlayerOnTurn, depth - 1)
        controllerCopy.undoLastMove

        if (value > maxValue) {
          maxValue = value
          if (depth == maxDepth)
            savedMove = move
        }
      }
      maxValue
    }

    miniMax(controllerCopy.getPlayerOnTurn, maxDepth)
    controller.transformMoveFromClonedControllerForCurrentController(savedMove)
  }
}