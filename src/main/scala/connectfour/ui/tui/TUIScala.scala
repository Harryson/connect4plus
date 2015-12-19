package connectfour.ui.tui

import connectfour.controller.{DropCoinScalaSwingEvent, NewGameScalaSwingEvent, Connect4GameController}
import connectfour.model.Connect4GameField
import connectfour.ui.UI
import connectfour.ui.gui.java.swing.events.{RedoScalaSwingEvent, UndoScalaSwingEvent}
import modelinterfaces.Player
import scala.swing.Reactor

/**
 * Created by maharr on 19.12.15.
 */
class TUIScala extends UI with Reactor {
  private val controller = Connect4GameController.getCurrentInstance
  private val newline: String = System.getProperty("line.separator")

  listenTo(controller.dropCoinEventScala)
  listenTo(controller.newGameEventScala)

  reactions += {
    case e: NewGameScalaSwingEvent => drawGameField
      System.out.println("New game clicked TUI")        //TODO remove line later
    case e: DropCoinScalaSwingEvent => drawGameField    //TODO remove line later
      System.out.println("Drop coin clicked TUI")
    case e: UndoScalaSwingEvent => drawGameField        //TODO
    case e: RedoScalaSwingEvent => drawGameField        //TODO
  }

  drawGameField

  def update() = drawGameField

  override def drawGameField {
    println(renderGameField)
    println("Enter command: q-Quit; n-New; int-Drop_Coin; u-Undo, r-Redo")
  }

  def processInputLine(input: String): Unit = {
    input match {
      case "q" => System.exit(0)
      case "n" => controller.newGameEventScala.newGame
      case "u" => //TODO undo
      case "r" => //TODO redo
      case _ =>
        if (isAllDigits(input) && input.compareTo("") != 0) {
          if (!controller.dropCoin(input.toInt)) {
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
