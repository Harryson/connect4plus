package connectfour.ui.tui

import connectfour.controller._
import connectfour.events.{RedoScalaSwingEvent, UndoScalaSwingEvent, NewGameScalaSwingEvent, DropCoinScalaSwingEvent}
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
    private val begin: String = "|"
    private val empty: String = "_"
    private val end: String = "_|"
    private val coin1: String = "O"
    private val coin2: String = "X"
    private val row: Int = Connect4GameField.FIELD_ROWS - 1
    private val col: Int = Connect4GameField.FIELD_COLUMNS
    private val (user, computer) = gameController.getPlayers

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
      val playingField: StringBuilder = new StringBuilder
      System.out.println("  1   2   3   4   5   6   7")

      renderRow(playingField, row)
    }

    private def renderRow(playingField: StringBuilder, currentRow: Int): String = {
      if (currentRow >= 0) {
        playingField.append(begin)
        val currentColumn = 0
        renderColumn(playingField, currentColumn, currentRow)
        playingField.append(newline)
        renderRow(playingField, currentRow - 1)
        //        currentRow + 1
      } else {
        playingField.toString()
      }
    }

    private def renderColumn(playingField: StringBuilder, currentColumn: Int, currentRow: Int) {
      if (currentColumn < col) {
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

        renderColumn(playingField, currentColumn + 1, currentRow)
      }
    }

    private def printInformation() {
      val winner = gameController.getWinner
      if (winner != "") {
        printf("%s has won\n", winner)
      } else {
        printf("%s on turn\n", gameController.getPlayerOnTurn)
      }

      printf("Enter command:\n q->Quit; n->New; 1 to 7->Drop_Coin; u->Undo; r->Redo\n")
    }

    def processInputLine(string: String) {
      var input: Any = string
      implicit def string2Int(s: String): Int = new Integer(s) // implicit

      if (isSingleChar(string))
        input = string.charAt(0)

      input match {
        case c: Char =>
          c match {
            case 's' => println("Start game")
            case 'q' => System.exit(0)
            case 'n' => gameController.reset()
            case 'u' => gameController.undo()
            case 'r' => gameController.redo()
            case _ => println("Misentry, not a correct character")
          }
        case s: String =>
          s match {
            case "start" => println("Start game")
            case "quit" => System.exit(0)
            case "new" => gameController.reset()
            case "undo" => gameController.undo()
            case "redo" => gameController.redo()
            case "" => println("Misentry, no entry!!!")
            case maybeNumber if (isAllDigits(s)) => // with guard
              //          val col = maybeNumber.toInt - 1                              // explicit
              val column = maybeNumber - 1 // implicit (String - Int) DSL
              gameController.dropCoin(column)
            case _ =>
              System.out.println("Misentry, not a correct number or string!!!")
          }
      }




      // Infinite loop
      processInputLine(scala.io.StdIn.readLine())
    }

    private def isAllDigits(input: String) = input forall Character.isDigit

    private def isSingleChar(input: String): Boolean = input.length == 1 && !isAllDigits(input)
  }

}