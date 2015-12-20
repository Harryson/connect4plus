package connect4.model.mocks

import connectfour.controller.Connect4GameController

class MockController(player1Name: String, player2Name: String) extends Connect4GameController(player1Name, player2Name) {
  gameField = new MockGameField(player1, player2)

  def getGameField: MockGameField = {
    gameField.asInstanceOf[MockGameField]
  }
}
