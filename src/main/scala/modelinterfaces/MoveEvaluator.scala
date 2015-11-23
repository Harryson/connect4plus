package modelinterfaces

import controller.GameController

/**
 * User: Stefano Di Martino
 * Date: 25.01.14
 * Time: 00:31
 */
trait MoveEvaluator {

  def noMovePossible(gameEngine: GameController, player: Player): Boolean

  def getScore(gameEngine: GameController, player: Player): Int

  def generatePossibleMoves(gameEngine: GameController, player: Player): List[Move]
}
