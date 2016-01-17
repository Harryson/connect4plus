package connectfour.controller

import modelinterfaces.Player

object Connect4GameField {
  val FIELD_ROWS: Int = 6
  val FIELD_COLUMNS: Int = 7
}

/**
 * Created by stefano on 17.02.14.
 */
class Connect4GameField(player1: Player, player2: Player) {
  //TODO 3 x var
  var gameField = Array.ofDim[Player](Connect4GameField.FIELD_COLUMNS, Connect4GameField.FIELD_ROWS)
  protected var playerOnTurn: Player = {
    if (Math.random() < 0.5)
      player1
    else
      player2
  }
  private var winner: Player = _

  def getPlayerOnTurn: Player = playerOnTurn

  def getWinner = {
    if (winner == null) {
      ""
    } else {
      winner.toString
    }
  }

  def changePlayerOnTurn(): Player = {
    if (playerOnTurn == player1)
      playerOnTurn = player2
    else if (playerOnTurn == player2)
      playerOnTurn = player1
    else
      throw new IllegalStateException("No player is on turn!")

    playerOnTurn
  }

  def dropCoin(column: Int): Boolean = {
    def insertPlayerInGameField(row: Int) {
      if (row < Connect4GameField.FIELD_ROWS)
        if (gameField(column)(row) == null)
          gameField(column)(row) = playerOnTurn
        else
          insertPlayerInGameField(row + 1)
    }

    if (column >= Connect4GameField.FIELD_COLUMNS)
      throw new ArrayIndexOutOfBoundsException("Move in column %d is not possible!".format(column))

    if (Connect4MoveEvaluator.verticalMoveIsPossible(this, column) && winner == null) {
      //TODO: ask Stefano: why always in row 0?
      insertPlayerInGameField(0)

      if (Connect4MoveEvaluator.playerHasWon(this, playerOnTurn))
        winner = playerOnTurn

      changePlayerOnTurn()
      true
    } else
      false
  }

  def cloneGameField(): Connect4GameField = {
    val newGameField = new Connect4GameField(player1, player2)
    newGameField.playerOnTurn = playerOnTurn
    cloneGameFieldRow(newGameField, 0)
  }

  private def cloneGameFieldRow(newGameField: Connect4GameField, row: Int): Connect4GameField = {
    if (row < Connect4GameField.FIELD_ROWS) {
      cloneGameFieldColumn(newGameField, 0, row)

      cloneGameFieldRow(newGameField, row + 1)
    } else {
      newGameField
    }
  }

  private def cloneGameFieldColumn(newGameField: Connect4GameField, column: Int, row: Int): Connect4GameField = {
    if (column < Connect4GameField.FIELD_COLUMNS) {
      newGameField.gameField(column)(row) = gameField(column)(row)

      cloneGameFieldColumn(newGameField, column + 1, row)
    } else {
      newGameField
    }
  }
}