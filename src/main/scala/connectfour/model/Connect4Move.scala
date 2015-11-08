package connectfour.model

import modelinterfaces.{Player, Move}
import connectfour.controller.Connect4GameController

/**
 * User: Stefano Di Martino
 * Date: 25.01.14
 * Time: 17:54
 */
case class Connect4Move(val controller: Connect4GameController, val column: Int) extends Move {
  def execute = controller.dropCoin(column)
}