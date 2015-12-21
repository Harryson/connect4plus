package connect4.model.mocks

import connectfour.model.Connect4GameField
import modelinterfaces.Player

class MockGameField(player1: Player, player2: Player) extends Connect4GameField(player1, player2) {

  def dropCoin(column: Int, p: Player): Boolean = {
    playerOnTurn = p
    val success = dropCoin(column)
    playerOnTurn = p

    success
  }

  def dropCoinChangePlayerOnTurn(column: Int): Boolean = {
    dropCoin(column)
  }
}
