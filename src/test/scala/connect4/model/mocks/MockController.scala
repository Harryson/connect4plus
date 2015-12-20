package connect4.model.mocks

import connectfour.controller.Connect4GameController

/**
  * Created by stefano on 20.12.15.
  */
class MockController(player1Name: String, player2Name: String) extends Connect4GameController(player1Name, player2Name) {
  gameField = new MockGameField(player1, player2)

  def getGameField: MockGameField = {
    gameField.asInstanceOf[MockGameField]
  }
}
