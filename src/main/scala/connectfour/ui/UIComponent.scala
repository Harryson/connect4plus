package connectfour.ui

import java.awt.Color

import connectfour.controller._
import connectfour.model.Connect4GameField
import connectfour.ui.gui.scala.swing.widgets.{ArrowField, CoinField, StatusDisplay, ToolBar}
import modelinterfaces.Player

import scala.swing.{BorderPanel, Frame, Reactor}

/**
 * Created by maharr on 23.12.15.
 */
trait UIComponent {
  this: Connect4GameControllerComponent =>

  val ui: UI

  val tui: UI

  class TUI extends UI with Reactor {

    private val newline: String = System.getProperty("line.separator")

    listenTo(gameController.dropCoinEventScala)
    listenTo(gameController.newGameEventScala)

    reactions += {
      case e: NewGameScalaSwingEvent => drawGameField
        listenTo(gameController.dropCoinEventScala)
        listenTo(gameController.newGameEventScala)
      case e: DropCoinScalaSwingEvent => drawGameField
      case e: UndoScalaSwingEvent => drawGameField //TODO
      case e: RedoScalaSwingEvent => drawGameField //TODO
    }

    drawGameField

    private def printInformation(): Unit = {

      val winner = gameController.getWinner
      if (winner != "") {
        System.out.printf("%s has won\n\n", winner)
      }
      else {
        System.out.printf("%s on turn\n\n", gameController.getPlayerOnTurn)
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
        case "n" => gameController.reset
        case "u" =>
          gameController.undoLastMove
          // TODO Statt drawGameField aufzurufen, braucht es ein UndoEvent
          drawGameField
        case "r" => //TODO redo
        case _ =>
          if (isAllDigits(input) && input.compareTo("") != 0) {
            val col = input.toInt - 1
            if (!gameController.dropCoin(col)) {
              System.out.println("False Input, not a correct number !!!")
            }
          } else {
            System.out.println("False Input, not a number!!!")
          }
      }
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
        currentRow -= 1;
        currentRow + 1
      }

      playingField.toString()
    }

    private def isAllDigits(input: String) = input forall Character.isDigit
  }

  ///////////////////////////////////////////////////////////////////////////////////////

  class SwingGUI extends Frame with UI {
    println("Create SwingGUI")

    visible = true
    title = "Connect 4 in Scala"
    menuBar = new ToolBar(gameController)

    val arrowField = new ArrowField(gameController)
    val coinField = new CoinField(gameController, arrowField)
    val statusDisplay = new StatusDisplay(gameController)

    contents = new BorderPanel {
      add(statusDisplay, BorderPanel.Position.South)
      add(coinField, BorderPanel.Position.Center)
      add(arrowField, BorderPanel.Position.North)
    }

    listenTo(gameController.dropCoinEventScala)
    listenTo(gameController.newGameEventScala)

    reactions += {
      case e: NewGameScalaSwingEvent => drawGameField
        listenTo(gameController.dropCoinEventScala)
        listenTo(gameController.newGameEventScala)
      case e: DropCoinScalaSwingEvent => drawGameField
      case e: UndoScalaSwingEvent => drawGameField //TODO
      case e: RedoScalaSwingEvent => drawGameField //TODO
    }

    drawGameField

    override def drawGameField {
      val (user, computer) = gameController.getPlayers
      statusDisplay.update

      //TODO: Nicht mehr Default Row und Col verwenden (soll skalierbar sein)
      for (currentRow <- 0 until Connect4GameField.FIELD_ROWS) {
        for (currentColumn <- 0 until Connect4GameField.FIELD_COLUMNS) {
          val player = gameController.getPlayerAt(currentRow, currentColumn)

          if (player == null) {
            coinField.CELLS(currentRow)(currentColumn).foreground = Color.WHITE
          } else if (player == user) {
            //TODO: Farben Zentral setzen
            coinField.CELLS(currentRow)(currentColumn).foreground = Color.RED
          } else if (player == computer) {
            coinField.CELLS(currentRow)(currentColumn).foreground = Color.YELLOW
          } else {
            //TODO: Noch machen bei Fehlerfall
            //          JOptionPane.showMessageDialog(this,
            //            "Fehler beim Einfuegen der Muenze in Zeile "
            //              + currentRow
            //              + " und Spalte "
            //              + currentColumn
            //              + "!")
            throw new RuntimeException("Problem: Fehler beim Einfuegen der Muenze in Zeile " + currentRow + " und Spalte " + currentColumn + "!")
          }
        }
      }
      repaint()
    }
  }
}
