package connectfour.ui.gui.scala.swing


import java.awt.Color

import connectfour.controller.Connect4GameController
import connectfour.model.Connect4GameField
import connectfour.ui.UI
import connectfour.ui.gui.java.swing.events.{RedoScalaSwingEvent, UndoScalaSwingEvent}
import connectfour.ui.gui.scala.swing.widgets.{ArrowField, CoinField, StatusDisplay, ToolBar}
import controller.{DropCoinScalaSwingEvent, NewGameScalaSwingEvent}

import scala.swing._

/**
 * Created by maharr on 13.11.15.
 */
class SwingGUI extends Frame with UI {

  visible = true
  title = "Connect 4 in Scala"
  menuBar = new ToolBar

  val arrowField = new ArrowField
  val coinField = new CoinField(arrowField)
  val statusDisplay = new StatusDisplay

  contents = new BorderPanel {
    add(statusDisplay, BorderPanel.Position.South)
    add(coinField, BorderPanel.Position.Center)
    add(arrowField, BorderPanel.Position.North)
  }

  listenTo(Connect4GameController.getCurrentInstance.dropCoinEventScala)
  listenTo(Connect4GameController.getCurrentInstance.newGameEventScala)

  reactions += {
    case e: NewGameScalaSwingEvent => drawGameField
      listenTo(Connect4GameController.getCurrentInstance.dropCoinEventScala)
      listenTo(Connect4GameController.getCurrentInstance.newGameEventScala)
    case e: DropCoinScalaSwingEvent => drawGameField
    case e: UndoScalaSwingEvent => drawGameField      //TODO
    case e: RedoScalaSwingEvent => drawGameField      //TODO
  }

  drawGameField

  override def drawGameField {
    val controller = Connect4GameController.getCurrentInstance
    val (user, computer) = controller.getPlayers
    statusDisplay.update

    //TODO: Nicht mehr Default Row und Col verwenden (soll skalierbar sein)
    for (currentRow <- 0 until Connect4GameField.FIELD_ROWS) {
      for (currentColumn <- 0 until Connect4GameField.FIELD_COLUMNS) {
        val player = controller.getPlayerAt(currentRow, currentColumn)

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
