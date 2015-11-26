package connectfour.controller

import java.util
import connectfour.model.GameField
import modelinterfaces.Player


/**
 * Created by maharr on 19.10.15.
 */
trait IController {

  def newGame

  def newGame(player: Player, opponent: Player)

  def getWinner: String

  def gameHasStarted: Boolean

  def dropCoinWithSuccessFeedback(col: Int): Boolean

  def getPlayerOnTurn: Player

  def userHasWon: Boolean

  def getPlayerNameOnTurn: String

  def setGameField(gameField: GameField)

  def getGameField: GameField

  def undoStep

  def redoStep

  def setPlayer(p: Player)

  def setOpponend(p: Player)

  def getPlayers: Array[Player]

  def getPlayerAt(row: Int, col: Int): Player

  def getOpponend: Player

  def getPlayer: Player

  def useState(state: GameField)
}
