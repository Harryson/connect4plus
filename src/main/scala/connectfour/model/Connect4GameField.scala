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
  val gameField = Array.ofDim[Player](Connect4GameField.FIELD_COLUMNS, Connect4GameField.FIELD_ROWS)
  private var winner: Player = _
  
  protected var playerOnTurn: Player = {
    if (Math.random() < 0.5)
      player1
    else
      player2
  }

  
  def getPlayerOnTurn: Player = playerOnTurn
  def getWinner = {
    if (winner == null) {
      ""
    } else {
      winner.toString
    }
  }

  def changePlayerOnTurn() = {
    if (playerOnTurn == player1)
      playerOnTurn = player2
    else if (playerOnTurn == player2)
      playerOnTurn = player1
    else
      throw new IllegalStateException("No player is on turn!")
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

    for (col <- 0 until Connect4GameField.FIELD_COLUMNS)
      for (row <- 0 until Connect4GameField.FIELD_ROWS)
        newGameField.gameField(col)(row) = gameField(col)(row)

    newGameField
  }


  def canEqual(other: Any): Boolean = other.isInstanceOf[Connect4GameField]

  override def equals(other: Any): Boolean = other match {
    case that: Connect4GameField =>
      var isEqual = true
      if ((that canEqual this) && playerOnTurn == that.playerOnTurn) {
        for (col <- 0 until Connect4GameField.FIELD_COLUMNS) {
          for (row <- 0 until Connect4GameField.FIELD_ROWS) {
            isEqual = isEqual && that.gameField(col)(row) == gameField(col)(row)
          }
        }
        isEqual
      }
      else
        false
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(playerOnTurn, gameField)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}