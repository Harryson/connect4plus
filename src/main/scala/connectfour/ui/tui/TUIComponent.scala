package connectfour.ui.tui

import connectfour.controller._
import connectfour.model.Connect4GameField
import connectfour.ui.UI
import modelinterfaces.Player

import scala.swing.Reactor

/**
 * Created by maharr on 24.12.15.
 */
trait TUIComponent {
  this: Connect4GameControllerComponent =>

  val tui: TUI

  class TUI extends UI with Reactor {
    private val newline = System.getProperty("line.separator")

    listenToEvents()

    reactions += {
      case e: NewGameScalaSwingEvent => drawGameField()
        listenToEvents()
      case e: DropCoinScalaSwingEvent => drawGameField()
      case e: UndoScalaSwingEvent => drawGameField()
      case e: RedoScalaSwingEvent => drawGameField()
    }

    private def listenToEvents() {
      listenTo(gameController.dropCoinEventScala)
      listenTo(gameController.newGameEventScala)
      listenTo(gameController.undoEventScala)
      listenTo(gameController.redoEventScala)
    }

    drawGameField()

    override def drawGameField() {
      println(renderGameField)
      printInformation()
    }

    private def renderGameField: String = {
      val (user, computer) = gameController.getPlayers

      val row: Int = Connect4GameField.FIELD_ROWS - 1
      val col: Int = Connect4GameField.FIELD_COLUMNS
      val playingField: StringBuilder = new StringBuilder
      val begin: String = "|"
      val empty: String = "_"
      val end: String = "_|"
      val coin1: String = "O"
      val coin2: String = "X"
      System.out.println("  1   2   3   4   5   6   7")
      var currentRow: Int = row

      while (currentRow >= 0) {
        playingField.append(begin)
        var currentColumn: Int = 0
        while (currentColumn < col) {
          playingField.append(empty)
          val player: Player = gameController.getPlayerAt(currentRow, currentColumn)
          if (player == null) {
            playingField.append(empty)
          }
          else if (player == user) {
            playingField.append(coin1)
          }
          else if (player == computer) {
            playingField.append(coin2)
          }
          else {
            playingField.append("ERROR!!!")
          }
          playingField.append(end)
          currentColumn += 1
        }
        playingField.append(newline)
        currentRow -= 1
        currentRow + 1
      }

      playingField.toString()
    }

    private def printInformation() {
      val winner = gameController.getWinner
      if (winner != "") {
        println("%s has won\n", winner)
      }
      else {
        println("%s on turn\n", gameController.getPlayerOnTurn)
      }

      println("Enter command:\n q->Quit; n->New; 1 to 7->Drop_Coin; u->Undo; r->Redo")
    }

    def processInputLine(input: String) {
      input match {
        case "start" => println("Start game")
        case "q" => System.exit(0)
        case "n" => gameController.reset()
        case "u" => gameController.undo()
        case "r" => gameController.redo()
        case _ =>
          if (isAllDigits(input) && input.compareTo("") != 0) {
            val col = input.toInt - 1
            if (!gameController.dropCoin(col)) {
              System.out.println("Misentry, not a correct number !!!")
            }
          } else {
            System.out.println("Misentry, not a number!!!")
          }
      }

      // Infinite loop
      processInputLine(scala.io.StdIn.readLine())
    }

    private def isAllDigits(input: String) = input forall Character.isDigit
  }
}