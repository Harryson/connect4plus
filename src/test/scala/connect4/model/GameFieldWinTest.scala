package connect4.model

import org.scalatest._
import connectfour.model.Connect4GameField
import modelinterfaces.Player
import connectfour.controller.Connect4GameController

class MockGameField(player1: Player, player2: Player) extends Connect4GameField(player1, player2) {

  def dropCoin(column: Int, p: Player): Boolean = {
    playerOnTurn = p
    val success = dropCoin(column)
    playerOnTurn = p

    success
  }
}

class MockController(player1Name: String, player2Name: String) extends Connect4GameController(player1Name, player2Name) {
  gameField = new MockGameField(player1, player2)

  def getGameField: MockGameField = {
    gameField.asInstanceOf[MockGameField]
  }
}

class GameFieldWinTest extends FlatSpec with Matchers {

  private var controller: MockController = _
  private var gameField: MockGameField = _
  private var player: Player = _
  private var opponent: Player = _

  def reset {
    controller = new MockController("Hugo", "Boss")
    gameField = controller.getGameField
    player = controller.player1
    opponent = controller.player2
  }

  reset

  /**
    *    0
    *   0X
    *  0XX
    * 0XX00
    */

  "The gamefield" should
    "return 'Boss' (KI) as winner, because he won vertically from bottom to top" in {
    reset
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(1, player)
    gameField.dropCoin(1, opponent)
    gameField.dropCoin(2, player)
    gameField.dropCoin(3, opponent)
    gameField.dropCoin(2, player)
    gameField.dropCoin(2, opponent)
    gameField.dropCoin(3, player)
    gameField.dropCoin(4, opponent)
    gameField.dropCoin(3, player)
    gameField.dropCoin(3, opponent)

    gameField.getWinner should be("Boss")
  }



  "The gamefield" should
    "return 'Boss' (KI) as winner, because he won horizontically" in {
    reset
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(1, player)
    gameField.dropCoin(1, opponent)
    gameField.dropCoin(2, player)
    gameField.dropCoin(2, opponent)
    gameField.dropCoin(2, player)
    gameField.dropCoin(4, opponent)
    gameField.dropCoin(3, player)
    gameField.dropCoin(3, opponent)
    gameField.dropCoin(5, player)
    gameField.dropCoin(4, opponent)

    gameField.getWinner should be("Boss")
  }

  /*
  *   0   1   2   3   4   5   6
  * |___|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_O_|___|___|___|_X_|___|___|
  * |_X_|___|___|_O_|_O_|___|___|
  * |_X_|___|_X_|_O_|_O_|_X_|_O_|
  * |_X_|___|_X_|_O_|_O_|_O_|_X_|
  *
  */

  "The gamefield" should
    "return '' as winner in this complex match" in {
    reset
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, player)
    gameField.dropCoin(0, opponent)

    gameField.dropCoin(2, opponent)
    gameField.dropCoin(2, opponent)

    gameField.dropCoin(3, player)
    gameField.dropCoin(3, player)
    gameField.dropCoin(3, player)

    gameField.dropCoin(4, player)
    gameField.dropCoin(4, player)
    gameField.dropCoin(4, player)
    gameField.dropCoin(4, opponent)

    gameField.dropCoin(5, player)
    gameField.dropCoin(5, opponent)

    gameField.dropCoin(6, opponent)
    gameField.dropCoin(6, player)

    gameField.getWinner should be("")
  }
}