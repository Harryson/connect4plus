package connectfour.controller

import connectfour.events.{UndoEventScala, RedoEventScala, NewGameEventScala, DropCoinEventScala}
import controller.GameController
import manager.{RedoManager, UndoManager}
import modelinterfaces.Player

/**
 * Created by maharr on 23.12.15.
 */
trait Connect4GameController extends GameController {
  val dropCoinEventScala = new DropCoinEventScala
  val newGameEventScala = new NewGameEventScala
  val undoEventScala = new UndoEventScala
  val redoEventScala = new RedoEventScala

  //TODO var
  var gameField: Connect4GameField
  var undoManager: UndoManager
  var redoManager: RedoManager

  // TODO move this in GameController e.g. doMove(move: Move): Boolean
  def dropCoin(column: Int): Boolean

  def getPlayerAt(currentRow: Int, currentColumn: Int): Player
}
