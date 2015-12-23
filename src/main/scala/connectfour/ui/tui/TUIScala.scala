package connectfour.ui.tui

import connectfour.controller.Connect4GameController
import connectfour.model.Connect4GameField
import connectfour.ui.UI
import connectfour.ui.gui.java.swing.events.{RedoScalaSwingEvent, UndoScalaSwingEvent}
import controller.{DropCoinScalaSwingEvent, NewGameScalaSwingEvent}
import modelinterfaces.Player

import scala.swing.Reactor

/**
 * Created by maharr on 19.12.15.
 */
class TUIScala extends UI with Reactor {
  private val newline: String = System.getProperty("line.separator")

  listenTo(Connect4GameController.getCurrentInstance.dropCoinEventScala)
  listenTo(Connect4GameController.getCurrentInstance.newGameEventScala)

  reactions += {
    case e: NewGameScalaSwingEvent => drawGameField
      listenTo(Connect4GameController.getCurrentInstance.dropCoinEventScala)
      listenTo(Connect4GameController.getCurrentInstance.newGameEventScala)
    case e: DropCoinScalaSwingEvent => drawGameField
    case e: UndoScalaSwingEvent => drawGameField        //TODO
    case e: RedoScalaSwingEvent => drawGameField        //TODO
  }

  drawGameField

  def update() = drawGameField

  def printInformation(): Unit = {
    val controller = Connect4GameController.getCurrentInstance

    val winner = controller.getWinner
    if (winner != "") {
      System.out.printf("%s has won\n\n", winner)
    }
    else {
      System.out.printf("%s on turn\n\n", controller.getPlayerOnTurn)
    }

    println("Enter command: q-Quit; n-New; int-Drop_Coin; u-Undo, r-Redo")
  }

  override def drawGameField {
    println(renderGameField)
    printInformation()
  }

  def processInputLine(input: String): Unit = {
    input match {
      case "q" => System.exit(0)
      case "n" => Connect4GameController.reset
      case "u" => //TODO undo
      case "r" => //TODO redo
      case _ =>
        if (isAllDigits(input) && input.compareTo("") != 0) {
          if (!Connect4GameController.getCurrentInstance.dropCoin(input.toInt)) {
            System.out.println("False Input, not a correct number !!!")
          }
        } else {
          System.out.println("False Input, not a number!!!")
        }
    }
  }

  def renderGameField: String = {
    val controller = Connect4GameController.getCurrentInstance
    val (user, computer) = controller.getPlayers

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
        val player: Player = controller.getPlayerAt(currentRow, currentColumn)
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
      currentRow -= 1; currentRow + 1
    }

    playingField.toString()
  }

  def isAllDigits(input: String) = input forall Character.isDigit
}