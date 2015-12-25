package connectfour.ui.gui.scala.swing

import java.awt.Color

import connectfour.controller._
import connectfour.model.Connect4GameField
import connectfour.ui.UI
import connectfour.ui.gui.scala.swing.widgets.{ArrowField, CoinField, StatusDisplay, ToolBar}

import scala.swing.{BorderPanel, Frame}

/**
 * Created by maharr on 24.12.15.
 */
trait ScalaSwingGUIComponent {
  this: Connect4GameControllerComponent =>

  val scalaSwingGUI: ScalaSwingGUI

  class ScalaSwingGUI extends Frame with UI {
    val (user, computer) = gameController.getPlayers

    // constructor
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
      statusDisplay.update()
      drawGameFieldRow(Connect4GameField.FIELD_ROWS - 1)
      repaint()
    }

    def drawGameFieldRow(currentRow: Int) {
      if (currentRow >= 0) {
        drawGameFieldColumn(currentRow, Connect4GameField.FIELD_COLUMNS - 1)

        drawGameFieldRow(currentRow - 1)
      }
    }

    def drawGameFieldColumn(currentRow: Int, currentColumn: Int) {
      if (currentColumn >= 0) {
        val player = gameController.getPlayerAt(currentRow, currentColumn)

        if (player == null) {
          coinField.CELLS(currentRow)(currentColumn).foreground = Color.WHITE
        } else if (player == user) {
          //TODO: Farben Zentral setzen
          coinField.CELLS(currentRow)(currentColumn).foreground = Color.RED
        } else if (player == computer) {
          coinField.CELLS(currentRow)(currentColumn).foreground = Color.YELLOW
        } else {
          val text = "Problem: Fehler beim Einfuegen der Muenze in Zeile " + currentRow + " und Spalte " + currentColumn + "!"
          statusDisplay.error(text)
          throw new RuntimeException(text)
        }

        drawGameFieldColumn(currentRow, currentColumn - 1)
      }
    }
  }
}