package connectfour.controller

import connectfour.model.Connect4GameField
import connectfour.ui.gui.scala.swing.events._
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

  protected var gameField: Connect4GameField

  // TODO move this in GameController e.g. doMove(move: Move): Boolean
  def dropCoin(column: Int): Boolean

  def getPlayerAt(currentRow: Int, currentColumn: Int): Player

  def reset(): Unit

  def undo(): Unit

  def redo(): Unit
}
