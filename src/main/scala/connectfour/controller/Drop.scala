package connectfour.controller

/**
 * Created by maharr on 16.01.16.
 */
class Drop(gamecontroller: Connect4GameController, dropSentence: String) {
  implicit def string2Int(str: String): Int = new Integer(str)

  def dropCoin = {
    val column: Int = new Integer(dropSentence.substring(dropSentence.indexOf("at") + 3, dropSentence.length));
    gamecontroller.dropCoin(column);
  }
}