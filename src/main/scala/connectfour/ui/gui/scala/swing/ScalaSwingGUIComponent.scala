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
