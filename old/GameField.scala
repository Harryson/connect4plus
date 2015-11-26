package connectfour.model

import java.util.Random

/**
 * Created by maharr on 31.10.15.
 */
object GameField {
  val DEFAULT_COLUMNS: Int = 7
  val DEFAULT_ROWS: Int = 6
}


class GameField(var player: Player, var opponent: Player) extends Cloneable{
  private var gameField: Array[Array[Player]] = null
  private var playerOnTurn: Player = null
  private var modCount: Int = 0
  private var playerWon: Player = null
  private var gameWon: Boolean = false

  // constructor
  gameField = Array.ofDim[Player](GameField.DEFAULT_ROWS, GameField.DEFAULT_COLUMNS)
  initPlayerOnTurn

  def getGameField: Array[Array[Player]] = {
    this.gameField
  }

  def getModCount: Int = {
    modCount
  }

  def isGameWon: Boolean = {
    gameWon
  }

  def setGameField(gameField: Array[Array[Player]]) {
    System.arraycopy(gameField, 0, this.gameField, 0, gameField.length)
  }

  def setGameIsWon(gameIsWon: Boolean) {
    this.gameWon = gameIsWon
  }

  def setModCount(modCount: Int) {
    this.modCount = modCount
  }

  def setPlayerOnTurn(player: Player) {
    this.playerOnTurn = player
  }

  private def initPlayerOnTurn() {
    val r: Random = new Random
    val rInt: Int = r.nextInt
    if (rInt % 2 == 0) {
      playerOnTurn = player
    }
    else {
      playerOnTurn = opponent
    }
  }

  override def toString: String = {
    val b: StringBuilder = new StringBuilder()

      var i: Int = GameField.DEFAULT_ROWS - 1
      while (i >= 0) {
        var j: Int = 0
        while (j < GameField.DEFAULT_COLUMNS) {
          val actualPlayer: Player = gameField(i)(j)
          if (actualPlayer != null && (actualPlayer == player)) {
            b.append("[o]")
          }
          else if (actualPlayer != null && (actualPlayer == opponent)) {
            b.append("[x]")
          }
          else {
            b.append("[-]")
          }

          j += 1
        }
        b.append("\n")
        i -= 1
      }
    b.toString
  }

  def getPlayers: Array[Player] = {
    val p: Array[Player] = new Array[Player](2)
    p(0) = player
    p(1) = opponent
    p
  }

  def getPlayerOnTurn: Player = {
    playerOnTurn
  }

  def setPlayer(p: Player) {
    player = p
  }

  def setOpponent(o: Player) {
    opponent = o
  }

  def getPlayerWon: Player = {
    playerWon
  }

  def setPlayerWon(playerWon: Player) {
    this.playerWon = playerWon
  }

  def setGameWon(gameWon: Boolean) {
    this.gameWon = gameWon
  }

  def dropCoin(column: Int): Int = {
    val row: Int = dropCoin(column, playerOnTurn)
    modCount += 1
    row
  }

  private def removeLastLineIfFull: Boolean = {
    if (lastLineFull) {
      System.arraycopy(gameField, 1, gameField, 0, gameField.length - 1)
      gameField(GameField.DEFAULT_ROWS - 1) = new Array[Player](GameField.DEFAULT_COLUMNS)
      return true
    }
    false
  }

  private def lastLineFull: Boolean = {
    var result = true
    for (element <- gameField(0)) {
      result &= element != null
    }
    result
  }

  def dropCoin(column: Int, p: Player): Int = {
    var row: Int = 0
    row = dropCoin(row, column, p)
    gameWon = hasWon(p)
    if (lastLineFull && !gameWon) {
      removeLastLineIfFull
      row -= 1
    }
    if (gameWon) {
      playerWon = p
    }
    row
  }

  @throws(classOf[CloneNotSupportedException])
  def getCopyOfGamefield: Array[Array[Player]] = {
    this.gameField.clone
  }

  private def dropCoin(row: Int, column: Int, p: Player): Int = {
    var row1: Int = row
    if (row >= GameField.DEFAULT_COLUMNS - 1) {
      return row1
    }
    if (column >= GameField.DEFAULT_COLUMNS) {
      throw new IllegalArgumentException
    }
    if (gameField(row1)(column) == null) {
      gameField(row1)(column) = p
    }
    else {
      row1 = dropCoin(row1 + 1, column, p)
    }
    row1
  }

  def getPlayerAt(row: Int, column: Int): Player = {
    gameField(row)(column)
  }

  def isEmpty: Boolean = {
    modCount == 0
  }

  def getWinner: Player = {
    playerWon
  }

  private def hasWon(playerToCheck: Player): Boolean = {
    var ret: Boolean = false
    ret |= checkHorizontal(playerToCheck)
    ret |= checkVertical(playerToCheck)
    ret |= checkLeftRightDiagonal(playerToCheck)
    ret |= checkLeftRigthDiagonal(playerToCheck)
    ret
  }

  private def checkLeftRigthDiagonal(playerToCheck: Player): Boolean = {
    var row: Int = GameField.DEFAULT_ROWS - 1
    while (row >= 3) {
      var col: Int = 0
      while (col < GameField.DEFAULT_COLUMNS - 3) {
        if (gameField(row)(col) != null && gameField(row)(col) == playerToCheck && gameField(row)(col) == gameField(row - 1)(col + 1) && gameField(row)(col) == gameField(row - 2)(col + 2) && gameField(row)(col) == gameField(row - 3)(col + 3)) {
          return true
        }
        col += 1
      }
      row -= 1
    }
    false
  }

  private def checkLeftRightDiagonal(playerToCheck: Player): Boolean = {
    var row: Int = 0
    while (row < GameField.DEFAULT_ROWS - 3) {
      var col: Int = 0
      while (col < GameField.DEFAULT_COLUMNS - 3) {
        if (gameField(row)(col) != null && gameField(row)(col) == playerToCheck && gameField(row)(col) == gameField(row + 1)(col + 1) && gameField(row)(col) == gameField(row + 2)(col + 2) && gameField(row)(col) == gameField(row + 3)(col + 3)) {
          return true
        }
        col += 1
      }
      row += 1
    }
    false
  }

  private def checkVertical(playerToCheck: Player): Boolean = {
    var col: Int = 0
    while (col < GameField.DEFAULT_COLUMNS) {
      var row: Int = 0
      while (row < GameField.DEFAULT_ROWS - 3) {
        if (gameField(row)(col) != null && gameField(row)(col) == playerToCheck && gameField(row)(col) == gameField(row + 1)(col) && gameField(row)(col) == gameField(row + 2)(col) && gameField(row)(col) == gameField(row + 3)(col)) {
          return true
        }
        row += 1
      }
      col += 1
    }
    false
  }

  private def checkHorizontal(playerToCheck: Player): Boolean = {
    var row: Int = 0
    while (row < GameField.DEFAULT_ROWS) {
      var col: Int = 0
      while (col < GameField.DEFAULT_COLUMNS - 3) {
        if (gameField(row)(col) != null && gameField(row)(col) == playerToCheck && gameField(row)(col) == gameField(row)(col + 1) && gameField(row)(col) == gameField(row)(col + 2) && gameField(row)(col) == gameField(row)(col + 3)) {
          return true
        }
        col += 1
      }
      row += 1
    }
    false
  }

  def changePlayerTurn() {
    if (playerOnTurn == opponent) {
      playerOnTurn = player
    }
    else if (playerOnTurn == player) {
      playerOnTurn = opponent
    }
  }

  def evaluateScore: Int = {
    val opponand: Player = if (playerOnTurn == player) opponent else player
    val plScore: Int = evaluatePlayerScore(playerOnTurn)
    val opScore: Int = evaluatePlayerScore(opponand)
    plScore - opScore
  }

  private def evaluateVertical(playerToCheck: Player, counters: Array[Int]) {
    var count: Int = 0
    var row: Int = 0
    while (row < GameField.DEFAULT_ROWS) {
      count = 0
      var col: Int = 0
      while (col < GameField.DEFAULT_COLUMNS - 1) {
        if (gameField(row)(col) != null && gameField(row)(col) == playerToCheck && gameField(row)(col) == gameField(row)(col + 1)) {
          count += 1
        }
        col += 1
      }
      counters(count) += 1
      row += 1
    }
  }

  private def evaluateHorizontal(playerToCheck: Player, counters: Array[Int]) {
    var count: Int = 0
    var col: Int = 0
    while (col < GameField.DEFAULT_COLUMNS) {
      count = 0
      var row: Int = 0
      while (row < GameField.DEFAULT_ROWS - 1) {
        if (gameField(row)(col) != null && (gameField(row)(col) == playerToCheck) && (gameField(row)(col) == gameField(row + 1)(col))) {
          count += 1
        }
        row += 1
      }
      counters(count) += 1
      col += 1
    }
  }

  private def evaluateDiagonalFromLeftToRight(playerToCheck: Player, counters: Array[Int]) {
    var count: Int = 0
    var row: Int = 0
    while (row < GameField.DEFAULT_ROWS - 1) {
      count = 0
      var col: Int = 0
      while (col < GameField.DEFAULT_COLUMNS - 1) {
        if (gameField(row)(col) != null && (gameField(row)(col) == playerToCheck) && (gameField(row)(col) == gameField(row + 1)(col + 1))) {
          count += 1
        }
        col += 1
      }
      counters(count) += 1
      row += 1
    }
  }

  private def evaluateDiagonalFromRigthToLeft(playerToCheck: Player, counters: Array[Int]) {
    var count: Int = 0
    var row: Int = 0
    while (row < GameField.DEFAULT_ROWS - 1) {
      count = 0
      var col: Int = 0
      while (col < GameField.DEFAULT_COLUMNS - 1) {
        if (gameField(row)(col) != null && (gameField(row)(col) == playerToCheck) && (gameField(row)(col) == gameField(row + 1)(col + 1))) {
          count += 1
        }
        col += 1
      }
      counters(count) += 1
      row += 1
    }
  }

  def evaluatePlayerScore(playerToCheck: Player): Int = {
    val counters: Array[Int] = new Array[Int](10)
    evaluateVertical(playerToCheck, counters)
    evaluateHorizontal(playerToCheck, counters)
    evaluateDiagonalFromLeftToRight(playerToCheck, counters)
    evaluateDiagonalFromRigthToLeft(playerToCheck, counters)
    var result: Int = 0
    var scoreMulitplier: Int = 1
    for (counter <- counters) {
      result += counter * scoreMulitplier
      scoreMulitplier *= 2
    }
    result
  }

  @throws(classOf[CloneNotSupportedException])
  override def clone: GameField = {
    val gf: GameField = super.clone.asInstanceOf[GameField]
    gf.gameField = this.gameField.clone
    var i: Int = 0
    while (i < GameField.DEFAULT_ROWS) {
        gf.gameField(i) = this.gameField(i).clone
        i += 1
    }
    gf
  }

  override def equals(o: Any): Boolean = {
    if (this eq o.asInstanceOf[AnyRef]) {
      return true
    }
    if (o == null || (getClass ne o.getClass)) {
      return false
    }
    val gameField1: GameField = o.asInstanceOf[GameField]

    if (gameWon != gameField1.isGameWon) {
      return false
    }
    if (modCount != gameField1.getModCount) {
      return false
    }
    if (if (opponent != null) !(opponent == gameField1.opponent) else gameField1.opponent != null) {
      return false
    }
    if (if (player != null) !(player == gameField1.player) else gameField1.player != null) {
      return false
    }
    if (if (playerOnTurn != null) !(playerOnTurn == gameField1.getPlayerOnTurn) else gameField1.getPlayerOnTurn != null) {
      return false
    }
    return !(if (playerWon != null) !(playerWon == gameField1.getPlayerWon) else gameField1.getPlayerWon != null)
  }

  override def hashCode: Int = {
    var result: Int = if (player != null) player.hashCode else 0
    result = 31 * result + (if (opponent != null) opponent.hashCode else 0)
    result = 31 * result + (if (playerOnTurn != null) playerOnTurn.hashCode else 0)
    result = 31 * result + modCount
    result = 31 * result + (if (playerWon != null) playerWon.hashCode else 0)
    result = 31 * result + (if (gameWon) 1 else 0)
    result
  }
}