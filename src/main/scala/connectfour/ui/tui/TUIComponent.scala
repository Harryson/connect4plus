package connectfour.ui.tui

import connectfour.controller._
import connectfour.events.{DropCoinScalaSwingEvent, NewGameScalaSwingEvent, RedoScalaSwingEvent, UndoScalaSwingEvent}
import connectfour.ui.UI
import controller.GameController
import modelinterfaces.Player

import scala.swing.Reactor
import scala.util.{Failure, Success, Try}

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

    implicit def sentenceToInt(str: String) = new Drop(gameController, str)

    listenToEvents()

    reactions += {
      case e: NewGameScalaSwingEvent => listenToEvents()
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
      val winner = getWinner(gameController)

      winner match {
        case Some(name) => printf("%s has won\n", name)
        case None => printf("%s on turn\n", gameController.getPlayerOnTurn)
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
            case 'i' => printInformation
            case 'q' => System.exit(0)
            case 'n' => gameController.reset()
              gameController.closeFrameEventScala.close()
            case 'u' => gameController.undo()
            case 'r' => gameController.redo()
            case char =>
              isPrintInfo(char) match {
                case Some(i) =>
                  println("You've inserted 'I'")
                  printInformation
                case None => println("Misentry, not a correct character")
              }
          }
        case s: String =>
          s match {
            case "quit" => System.exit(0)
            case "new" => gameController.reset()
              gameController.closeFrameEventScala.close()
            case "undo" => gameController.undo()
            case "redo" => gameController.redo()
            case "" => println("Misentry, no entry!!!")
            case maybeNumber if (isSingleDigit(s)) => // with guard
              val column = new Integer(maybeNumber) - 1 // explicit convert (String - Int)
            val sentence = "Drop coin at " + column // new DSL
              sentence.dropCoin
            case maybeNumber if (isAllDigits(s)) => // with guard
              val column = maybeNumber - 1 % Connect4GameField.FIELD_COLUMNS // implicit convert (String - Int)
              gameController.dropCoin(column) // old
            case string =>

              val result = parseIntColumn(string)
              result match {
                case Success(column) =>
                case Failure(ex) => System.out.println("Misentry, not a correct number or string!!!")
              }
          }
      }
      // Infinite loop
      processInputLine(scala.io.StdIn.readLine())
    }

    private def isAllDigits(input: String) = input forall Character.isDigit

    private def isSingleDigit(input: String) = {
      var state = false

      if (input.length == 1) {
        if (isAllDigits(input)) {
          state = true
        }
      }

      state
    }

    private def isPrintInfo(input: Char): Option[Char] = {
      if (input == 'I') {
        Some('i')
      } else {
        None
      }
    }

    private def isSingleChar(input: String): Boolean = input.length == 1 && !isAllDigits(input)

    def parseIntColumn(value: String): Try[Int] = Try((value.toInt - 1) % Connect4GameField.FIELD_COLUMNS)
  }


  private def getWinner(gameController: GameController): Option[String] = {
    val winner = gameController.getWinner
    if (winner != "") {
      Some(winner)
    } else {
      None
    }
  }

}