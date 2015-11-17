package connectfour.controller

import java.util
import javax.swing.undo.UndoManager

import com.google.inject.{Singleton, Inject}
import connectfour.model._
import connectfour.util.observer.{IObserverWithArguments, ObservableWithArguments}
import org.slf4j.LoggerFactory

import scala.swing.Publisher
import scala.swing.event.Event

/**
  * Created by maharr on 31.10.15.
 */
case class DropCoinScalaSwingEvent() extends Event
case class NewGameScalaSwingEvent() extends Event
case class UndoScalaSwingEvent() extends Event
case class RedoScalaSwingEvent() extends Event

@Singleton
class GameController extends ObservableWithArguments with IObserverWithArguments with IController with Publisher {

  val logger = LoggerFactory.getLogger(getClass.getName)

  private var gameField: GameField = null
  private var bGameHasStarted: Boolean = false
  @Inject private var scoreController: IHighScoreController = null
  private var undoManager: UndoManager = new UndoManager


  // constructor
  this.undoManager.discardAllEdits
  val p1: Player = new Human("Hugo")
  val opponent: Player = new Computer(this, "Boesewicht")
  this.gameField = new GameField(p1, opponent)
  this.bGameHasStarted = false

  override def newGame {
    logger.debug("Start newGame() without parameter")
    this.bGameHasStarted = true
    val p1: Player = new Human("Hugo")
    val opponent: Player = new Computer(this, "Boesewicht")
    undoManager.discardAllEdits
    gameField = new GameField(p1, opponent)
    this.addObserver(gameField.opponent)

    this.notifyObservers()
    this.notifyObservers(gameField)
    publish(new NewGameScalaSwingEvent)
    logger.debug("End newGame() without parameter")
  }

  override def newGame(player: Player, opponent: Player) {
    logger.debug("Start newGame() with parameter")
    this.bGameHasStarted = true
    undoManager.discardAllEdits
    gameField = new GameField(player, opponent)
    undoManager.die

    this.notifyObservers()
    this.notifyObservers(gameField)
    publish(new NewGameScalaSwingEvent)
    logger.debug("End newGame() with parameter")
  }

  override def getWinner: String = {
    logger.debug("Start getWinner()")
    val winner: Player = gameField.getWinner
    if (winner != null) {
      logger.debug("End getWinner()")
      winner.name
    }
    else {
      logger.debug("End getWinner()")
      ""
    }
  }

  override def gameHasStarted: Boolean = {
    logger.debug("Start & End gameHasStarted()")
    this.bGameHasStarted
  }

  override def dropCoinWithSuccessFeedback(col: Int): Boolean = {
    logger.debug("Start dropCoinWithSuccessFeedback()")
    var success: Boolean = true
    if (!userHasWon) {
      var previousState: GameField = null
      previousState = gameField.clone

      val p: Player = gameField.getPlayerOnTurn
      p.setGameField(gameField)
      val row: Int = gameField.dropCoin(col)
      gameField.changePlayerTurn
      if (row >= GameField.DEFAULT_ROWS) {
        success = false
        useState(previousState)
        logger.debug("End dropCoinWithSuccessFeedback() return with false")
        return success
      }
      var newState: GameField = null
      newState = gameField.clone
      if (!previousState.isGameWon && newState.isGameWon) {
        scoreController.sendHighScore("connect4plus", getWinner, getGameField.evaluatePlayerScore(getGameField.getWinner))
      }
      val undoInfo: String = String.format("Undoing %s Player Move", getPlayerOnTurn.name)
      val edit: GameFieldEdit = new GameFieldEdit(this, previousState, newState, undoInfo)
      undoManager.addEdit(edit)

      this.notifyObservers()
      this.notifyObservers(gameField)
      publish(new DropCoinScalaSwingEvent)
    }
    logger.debug("End dropCoinWithSuccessFeedback() return with true")
    success
  }

  override def getPlayerOnTurn: Player = {
    logger.debug("Start & End getPlayerOnTurn()")
    gameField.getPlayerOnTurn
  }

  override def userHasWon: Boolean = {
    logger.debug("Start & End userHasWon()")
    val winner: Player = gameField.getWinner
    winner != null
  }

  override def getPlayerNameOnTurn: String = {
    logger.debug("Start & End getPlayerNameOnTurn()")
    val player: Player = gameField.getPlayerOnTurn
    player.name
  }

  override def setGameField(gameField: GameField) {
    logger.debug("Start & End setGameField()")
    this.gameField = gameField
  }

  override def getGameField: GameField = {
    logger.debug("Start & End getGameField()")
    this.gameField
  }

  override def undoStep {
    logger.debug("Start & End undoStep()")
    if (undoManager.canUndo) {
      undoManager.undo

      //TODO: Funktioniert nicht
      publish(new UndoScalaSwingEvent)
    }
  }

  override def redoStep {
    logger.debug("Start & End redoStep()")
    if (undoManager.canRedo) {
      undoManager.redo

      //TODO: Funktioniert nicht
      publish(new RedoScalaSwingEvent)
    }
  }

  override def setPlayer(p: Player) {
    logger.debug("Start & End setPlayer()")
    gameField.setPlayer(p)
  }

  override def setOpponend(p: Player) {
    logger.debug("Start & End setOpponend()")
    gameField.setOpponent(p)
  }

  override def getPlayers: Array[Player] = {
    logger.debug("Start & End getPlayers()")
    gameField.getPlayers
  }

  override def getPlayerAt(row: Int, col: Int): Player = {
    logger.debug("Start & End getPlayerAt()")
    gameField.getPlayerAt(row, col)
  }

  override def getOpponend: Player = {
    logger.debug("Start & End getOpponend()")
    gameField.opponent
  }

  override def getPlayer: Player = {
    logger.debug("Start & End getPlayer()")
    gameField.player
  }

  override def useState(state: GameField) {
    logger.debug("Start & End useState()")
    gameField = state
  }

  override def update(arg: Any) {
    logger.debug("Start update()")
    val columnToDrop: Int = arg.asInstanceOf[Int]
    this.dropCoinWithSuccessFeedback(columnToDrop)
    logger.debug("End update()")
  }
}
