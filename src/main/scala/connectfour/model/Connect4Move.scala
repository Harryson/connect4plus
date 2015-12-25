package connectfour.model

import connectfour.controller._
import modelinterfaces.Move

/**
 * User: Stefano Di Martino
 * Date: 25.01.14
 * Time: 17:54
 */
case class Connect4Move(controller: Connect4GameController, column: Int) extends Move {
  def execute = controller.dropCoin(column)
}