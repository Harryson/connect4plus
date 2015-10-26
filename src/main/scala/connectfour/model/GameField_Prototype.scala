package connectfour.model

import java.util.Random

class GameFieldPrototype

/*{
  val gameField: Array[Array[Player]] = Array.ofDim[Player](GameField.DEFAULT_COLUMNS, GameField.DEFAULT_ROWS)

  var gameWon = false

  var player: Player = _
  var opponend: Player = _
  var playerOnTurn: Player = _
  var playerWon: Player = _

  def setGameField(gameField: Array[Array[Player]]) {
    System.arraycopy(gameField, 0, this.gameField, 0, gameField.length)
  }
  
  private def initPlayerOnTurn() {
		val r = new Random()
		val rInt = r.nextInt()
		
		playerOnTurn = if (rInt % 2 == 0) player else	opponend
	}

	override def toString(): String = {
		val b = new StringBuilder
		
		for (i <- GameField.DEFAULT_ROWS - 1 to 0 by -1) {
		  for (j <- 0 until GameField.DEFAULT_COLUMNS) { 
				val actualPlayer = gameField(i)(j)
				
				if (actualPlayer != null && actualPlayer.equals(player)) {
					b ++= "[o]"
				} else if (actualPlayer != null && actualPlayer.equals(opponend)) {
					b ++= "[x]"
				} else {
					b ++= "[-]"
				}
			}
			b ++= "\n"
		}
		
		b.toString()
	}
  
	def getPlayers {
		(player, opponend)
	}
	
	def dropCoin(column: Int) : Int {
		// modCount++;
		dropCoin(column, playerOnTurn)
	}
	
	def dropCoin(column: Int, p: Player): Int = {
		var row = dropCoin(0, column, p)
		
		gameWon = hasWon(p)

		if (lastLineFull() && !gameWon) {
			removeLastLineIfFull()
			row -= 1
		}
		
		if (gameWon) {
			playerWon = p;
		}
		
		row
	}
	
	private def dropCoin(row: Int, column: Int, p: Player): Int = {
		var row1 = row

		if (row >= GameField.DEFAULT_COLUMNS - 1) {
			return row1
		}

		if (column >= GameField.DEFAULT_COLUMNS) {
			throw new IllegalArgumentException();
		}

		if (gameField(row1)(column) == null) {
			gameField(row1)(column) = p
		} else {
		  row1 += 1
			row1 = dropCoin(row1, column, p);
		}

		row1
	}
	
	private def hasWon(playerToCheck: Player): Boolean = {
    if (checkHorizontal(playerToCheck)) return true
		if (checkVertical(playerToCheck)) return true
		if (checkLeftRightDiagonal(playerToCheck)) return true
		if (checkRigthLeftDiagonal(playerToCheck)) return true

		false
	}

	private def checkRigthLeftDiagonal(playerToCheck: Player): Boolean = {
	  for (row <- GameField.DEFAULT_ROWS - 1 to 3 by -1) {
		  for (col <- 0 until GameField.DEFAULT_COLUMNS - 3) { 
				if (gameField(row)(col) != null
                        && gameField(row)(col).equals(playerToCheck)
						&& gameField(row)(col).equals(gameField(row - 1)(col + 1))
						&& gameField(row)(col).equals(gameField(row - 2)(col + 2))
						&& gameField(row)(col).equals(gameField(row - 3)(col + 3))) {
					return true
				}
			}
		}
	  
		false
	}

	private def checkLeftRightDiagonal(playerToCheck: Player): Boolean = {
		for (row <- 0 until GameField.DEFAULT_ROWS - 3) {
			for (col <- 0 until GameField.DEFAULT_COLUMNS - 3) {
				if (gameField(row)(col) != null
                        && gameField(row)(col).equals(playerToCheck)
						&& gameField(row)(col).equals(gameField(row + 1)(col + 1))
						&& gameField(row)(col).equals(gameField(row + 2)(col + 2))
						&& gameField(row)(col).equals(gameField(row + 3)(col + 3))) {
					return true
				}
			}
		}
		
		false
	}

	private def checkVertical(playerToCheck: Player): Boolean = {
		for (col <- 0 until GameField.DEFAULT_COLUMNS) {
			for (row <- 0 until GameField.DEFAULT_ROWS - 3) {
				if (gameField(row)(col) != null
                        && gameField(row)(col).equals(playerToCheck)
						&& gameField(row)(col).equals(gameField(row + 1)(col))
						&& gameField(row)(col).equals(gameField(row + 2)(col))
						&& gameField(row)(col).equals(gameField(row + 3)(col))) {
					return true
				}
			}
		}
		
		false
	}

	private def checkHorizontal(playerToCheck: Player): Boolean = {
		for (row <- 0 until GameField.DEFAULT_ROWS) {
			for (col <- 0 until GameField.DEFAULT_COLUMNS - 3) {
				if (gameField(row)(col) != null
                        && gameField(row)(col).equals(playerToCheck)
						&& gameField(row)(col).equals(gameField(row)(col + 1))
						&& gameField(row)(col).equals(gameField(row)(col + 2))
						&& gameField(row)(col).equals(gameField(row)(col + 3))) {
					return true
				}
			}
		}
		
		false
	}
	
}

object GameField {
  val DEFAULT_COLUMNS = 7
  val DEFAULT_ROWS = 6
}*/