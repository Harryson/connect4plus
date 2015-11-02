package connectfour.model

import modelinterfaces.{Move, Player}
import connectfour.Controller.Connect4GameController

/**
 * User: Stefano Di Martino
 * Date: 25.01.14
 * Time: 17:58
 */
object Connect4MoveEvaluator {

  def verticalMoveIsPossible(gameField: Connect4GameField, column: Int): Boolean =
    gameField.gameField(column)(Connect4GameField.FIELD_ROWS - 1) == null

  def noMovePossible(gameField: Connect4GameField, player: Player): Boolean = {
    def noMovePossible(column: Int): Boolean = {
      if (column < Connect4GameField.FIELD_COLUMNS)
        !verticalMoveIsPossible(gameField, column) && noMovePossible(column + 1)
      else
        true
    }

    noMovePossible(0)
  }

  def getScore(gameField: Connect4GameField, player: Player): Int = {
    if (horizontalWin(gameField, player) || verticalWin(gameField, player) ||
      diagonalUpWin(gameField, player) || diagonalDownWin(gameField, player))
      Int.MaxValue
    else
      0
  }

  private def horizontalWin(gameField: Connect4GameField, player: Player): Boolean = {
    def horizontalWin(column: Int, row: Int): Boolean = {
      if (column <= Connect4GameField.FIELD_COLUMNS - 4) {
        if (gameField.gameField(column)(row) == player &&
          gameField.gameField(column + 1)(row) == player &&
          gameField.gameField(column + 2)(row) == player &&
          gameField.gameField(column + 3)(row) == player)
          true
        else
          horizontalWin(column + 1, row)
      } else if (row < Connect4GameField.FIELD_ROWS - 1)
        horizontalWin(0, row + 1)
      else
        false
    }

    horizontalWin(0, 0)
  }

  private def verticalWin(gameField: Connect4GameField, player: Player): Boolean = {
    def verticalWin(column: Int, row: Int): Boolean = {
      if (row <= Connect4GameField.FIELD_ROWS - 4) {
        if (gameField.gameField(column)(row) == player &&
          gameField.gameField(column)(row + 1) == player &&
          gameField.gameField(column)(row + 2) == player &&
          gameField.gameField(column)(row + 3) == player)
          true
        else
          verticalWin(column, row + 1)
      } else if (column < Connect4GameField.FIELD_COLUMNS - 1)
        verticalWin(column + 1, 0)
      else
        false
    }

    verticalWin(0, 0)
  }

  private def diagonalUpWin(gameField: Connect4GameField, player: Player): Boolean = {
    def diagonalUpWin(column: Int, row: Int): Boolean = {
      if (row <= Connect4GameField.FIELD_ROWS - 4) {
        if (gameField.gameField(column)(row) == player &&
          gameField.gameField(column + 1)(row + 1) == player &&
          gameField.gameField(column + 2)(row + 2) == player &&
          gameField.gameField(column + 3)(row + 3) == player)
          true
        else
          diagonalUpWin(column, row + 1)
      } else if (column < Connect4GameField.FIELD_COLUMNS - 5)
        diagonalUpWin(column + 1, 0)
      else
        false
    }

    diagonalUpWin(0, 0)
  }

  private def diagonalDownWin(gameField: Connect4GameField, player: Player): Boolean = {
    def diagonalDownWin(column: Int, row: Int): Boolean = {
      if (row >= 3) {
        if (gameField.gameField(column)(row) == player &&
          gameField.gameField(column + 1)(row - 1) == player &&
          gameField.gameField(column + 2)(row - 2) == player &&
          gameField.gameField(column + 3)(row - 3) == player)
          true
        else
          diagonalDownWin(column, row - 1)
      } else if (column < Connect4GameField.FIELD_COLUMNS - 5)
        diagonalDownWin(column + 1, Connect4GameField.FIELD_ROWS - 1)
      else
        false
    }

    diagonalDownWin(0, Connect4GameField.FIELD_ROWS - 1)
  }

  def generatePossibleMoves(controller: Connect4GameController, gameField: Connect4GameField, player: Player): List[Move] = {
    def possibleMove(column: Int, moveList: List[Move]): List[Move] = {
      if (column < Connect4GameField.FIELD_COLUMNS)
        if (verticalMoveIsPossible(gameField, column))
          possibleMove(column + 1, moveList :+ new Connect4Move(controller, column))
        else
          possibleMove(column + 1, moveList)
      else
        moveList
    }

    possibleMove(0, Nil)
  }
}