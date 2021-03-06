package ai

import controller.{GameController, Move, NoMovePossible}
import modelinterfaces._

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
  def getNextMove(controller: GameController, maxDepth: Int = DIFFICULTY_MIDDLE): Move = {
    val controllerCopy = controller.cloneController
    var savedMove: Move = NoMovePossible

    def miniMax(currentPlayer: Player, depth: Int): Double = {
      // base case
      if (depth == 0 || controllerCopy.gameIsOver || controllerCopy.noMovePossible(currentPlayer))
        return controllerCopy.getScore(currentPlayer).asInstanceOf[Double]

      var maxValue = Double.NegativeInfinity
      val possibleMoves: Seq[Move] = controllerCopy.generatePossibleMoves(currentPlayer)

      for (move <- possibleMoves) {
        move.execute
        // recursion
        val value = -miniMax(controllerCopy.getPlayerOnTurn, depth - 1)
        controllerCopy.undoLastMove()

        if (value > maxValue) {
          maxValue = value
          if (depth == maxDepth)
            savedMove = move
        }
      }
      maxValue
    }

    miniMax(controllerCopy.getPlayerOnTurn, maxDepth)
    controller.transferMoveToCurrentController(savedMove)
  }
}