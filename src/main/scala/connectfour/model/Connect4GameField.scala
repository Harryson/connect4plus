package connectfour.model

import modelinterfaces.Player

object Connect4GameField {
  val FIELD_ROWS = 6
  val FIELD_COLUMNS = 7
}

/**
 * Created by stefano on 17.02.14.
 */
class Connect4GameField(player1: Player, player2: Player) {
  //TODO var
  var gameField = Array.ofDim[Player](Connect4GameField.FIELD_COLUMNS, Connect4GameField.FIELD_ROWS)
  private var winner: Player = _

  //TODO var
  //  protected var playerOnTurn: Player = {
  //    if (Math.random() < 0.5)
  //      player1
  //    else
  //      player2
  //  }

  def initPlayerOnTurn: Player = {
    if (Math.random() < 0.5)
      player1
    else
      player2
  }

  def getPlayerOnTurn(currentPlayer: Player): Player = {
    //playerOnTurn
    if (currentPlayer == player1)
      player1
    else if (currentPlayer == player2)
      player2
    else
      throw new IllegalStateException("No player is on turn!")
  }

  def getWinner = {
    if (winner == null) {
      ""
    } else {
      winner.toString
    }
  }

  def dropCoin(currentPlayer: Player, column: Int): Player = {
    def insertPlayerInGameField(row: Int) {
      if (row < Connect4GameField.FIELD_ROWS)
        if (gameField(column)(row) == null)
          gameField(column)(row) = getPlayerOnTurn(currentPlayer)
        else
          insertPlayerInGameField(row + 1)
    }

    if (column >= Connect4GameField.FIELD_COLUMNS)
      throw new ArrayIndexOutOfBoundsException("Move in column %d is not possible!".format(column))

    if (Connect4MoveEvaluator.verticalMoveIsPossible(this, column) && winner == null) {
      insertPlayerInGameField(0)

      if (Connect4MoveEvaluator.playerHasWon(this, getPlayerOnTurn(currentPlayer))) {
        winner = getPlayerOnTurn(currentPlayer)
      }

      changePlayerOnTurn(currentPlayer)
    } else
      null
  }

  private def changePlayerOnTurn(currentPlayer: Player): Player = {
    if (currentPlayer == player1)
      player2
    else if (currentPlayer == player2)
      player1
    else
      throw new IllegalStateException("No player is on turn!")
  }

  def cloneGameField(currentPlayer: Player): Connect4GameField = {
    val newGameField = new Connect4GameField(player1, player2)
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