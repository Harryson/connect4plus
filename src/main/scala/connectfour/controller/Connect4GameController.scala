package connectfour.controller

import connectfour.events._
import controller.GameController
import modelinterfaces.Player

/**
 * Created by maharr on 23.12.15.
 */
trait Connect4GameController extends GameController {
  val dropCoinEventScala = new DropCoinEventScala
  val newGameEventScala = new NewGameEventScala
  val undoEventScala = new UndoEventScala
  val redoEventScala = new RedoEventScala
  val closeFrameEventScala = new CloseFrameEventScala

  //TODO var
  var gameField: Connect4GameField

  def dropCoin(column: Int): Boolean

  def getPlayerAt(currentRow: Int, currentColumn: Int): Player
}
