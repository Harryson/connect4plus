package connectfour.controller

import scala.util.{Failure, Success, Try}

/**
 * Created by maharr on 16.01.16.
 */
class Drop(gamecontroller: Connect4GameController, dropSentence: String) {
  implicit def string2Int(str: String): Int = new Integer(str)

  def dropCoin = {
    val column: Int = new Integer(dropSentence.substring(dropSentence.indexOf("at") + 3, dropSentence.length));

    val result = dropCoinExceptionHandling(column)
    result match {
      case Success(number) => // Do nothing
      case Failure(ex) => println("Move in column 7 is not possible!")    // Hide exception
    }
  }

  def dropCoinExceptionHandling(column: Int): Try[Boolean] = {    // Try Monad (Container)
    Try (gamecontroller.dropCoin(column))
  }
}