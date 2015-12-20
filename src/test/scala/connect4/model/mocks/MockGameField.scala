package connect4.model.mocks

import connectfour.model.Connect4GameField
import modelinterfaces.Player

/**
  * Created by stefano on 20.12.15.
  */
class MockGameField(player1: Player, player2: Player) extends Connect4GameField(player1, player2) {

  def dropCoin(column: Int, p: Player): Boolean = {
    playerOnTurn = p
    val success = dropCoin(column)
    playerOnTurn = p

    success
  }
}
