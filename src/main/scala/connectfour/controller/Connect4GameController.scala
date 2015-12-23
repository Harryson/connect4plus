package connectfour.controller

import connectfour.model.Connect4GameField
import connectfour.ui.gui.scala.swing.events.{DropCoinEventScala, NewGameEventScala}
import controller.GameController

/**
 * Created by maharr on 23.12.15.
 */
trait Connect4GameController extends GameController {
  val dropCoinEventScala: DropCoinEventScala
  val newGameEventScala: NewGameEventScala
  //  val undoEventScala: UndoEventScala  //TODO
  //  val redoEventScala: RedoEventScala  //TODO
  protected var gameField: Connect4GameField

  // TODO move this in GameController e.g. doMove(move: Move): Boolean
  def dropCoin(column: Int): Boolean
}
