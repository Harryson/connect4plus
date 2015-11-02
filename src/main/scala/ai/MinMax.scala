package ai

import modelinterfaces._
import controller.GameController

/**
 * User: Stefano Di Martino
 * Date: 25.01.14
 * Time: 00:13
 */
object MinMax {
  val DIFFICULTY_MIDDLE = 4
  val DIFFICULTY_HEAVY = 6

  /**
   * @return NoMovePossible, if no move is possible, else the best possible move
   */
  def getNextMove(controller: GameController, maxDepth: Int = DIFFICULTY_MIDDLE): Move = {
    val controllerCopy = controller.cloneController
    var savedMove: Move = NoMovePossible

    def miniMax(currentPlayer: Player, depth: Int): Int = {
      if (depth == 0 || controllerCopy.noMovePossible(currentPlayer))
        return controllerCopy.getScore(currentPlayer)

      var maxValue = Int.MinValue
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