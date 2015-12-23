package connect4.model.mocks

import connectfour.controller.Connect4GameControllerImpl

class MockController(player1Name: String, player2Name: String) extends Connect4GameControllerImpl(player1Name, player2Name) {
  gameField = new MockGameField(player1, player2)

  def getGameField: MockGameField = {
    gameField.asInstanceOf[MockGameField]
  }

  def dropCoinChangePlayerOnTurn(column: Int): Boolean = {
    val success = gameField.asInstanceOf[MockGameField].dropCoinChangePlayerOnTurn(column)
    dropCoinEventScala.dropCoin
    success
  }
}
