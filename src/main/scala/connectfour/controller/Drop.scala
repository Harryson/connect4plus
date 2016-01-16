package connectfour.controller

import scala.util.{Failure, Success, Try}

/**
 * Created by maharr on 16.01.16.
 */
class Drop(gamecontroller: Connect4GameController, dropSentence: String) {
  implicit def string2Int(str: String): Int = new Integer(str)

  def dropCoin: String = {
    val column: Int = new Integer(dropSentence.substring(dropSentence.indexOf("at") + 3, dropSentence.length))
    var text: String = ""

    val result = dropCoinExceptionHandling(column)
    result match {
      case Success(number) => text = "Successful drop in column " + column
      case Failure(ex) =>
        text = "Drop in column " + column + " is not possible!"
        println(text) // Hide exception
    }
    text
  }

  def dropCoinExceptionHandling(column: Int): Try[Boolean] = {    // Try Monad (Container)
    Try (gamecontroller.dropCoin(column))
  }
}