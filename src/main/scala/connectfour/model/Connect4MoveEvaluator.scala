package connectfour.model

import modelinterfaces.{ Move, Player }
import connectfour.controller.Connect4GameController

import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

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

  def playerHasWon(gameField: Connect4GameField, player: Player): Boolean = {
    horizontalWin(gameField, player) ||
      verticalWin(gameField, player) ||
      diagonalUpWin(gameField, player) ||
      diagonalDownWin(gameField, player)
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
      } else if (column < Connect4GameField.FIELD_COLUMNS - 4)
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
      } else if (column < Connect4GameField.FIELD_COLUMNS - 4)
        diagonalDownWin(column + 1, Connect4GameField.FIELD_ROWS - 1)
      else
        false
    }

    diagonalDownWin(0, Connect4GameField.FIELD_ROWS - 1)
  }

  def generatePossibleMoves(controller: Connect4GameController, gameField: Connect4GameField, player: Player): List[Move] = {

    def possibleMove(colFrom: Int, colTo: Int,  moveList: List[Move]): List[Move] = {
      if (colFrom < colTo)
        if (verticalMoveIsPossible(gameField, colFrom))
          possibleMove(colFrom + 1, colTo, moveList :+ new Connect4Move(controller, colFrom))
        else
          possibleMove(colFrom + 1, colTo, moveList)
      else
        moveList
    }

    val futureMoveList1 = Future {
      val to =  Connect4GameField.FIELD_COLUMNS / 2
      possibleMove(0, to, Nil)
    }

    val futureMoveList2 = Future {
      val from =  (Connect4GameField.FIELD_COLUMNS / 2)
      possibleMove(from, Connect4GameField.FIELD_COLUMNS, Nil)
    }

    val futureFullMoveList = for {
      one <- futureMoveList1
      two <- futureMoveList2
    } yield one ++ two

    val fullMoveList = Await.result(futureFullMoveList, 10 seconds)

    return fullMoveList
  }
}