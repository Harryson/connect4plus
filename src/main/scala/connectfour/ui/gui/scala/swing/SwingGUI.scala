package connectfour.ui.gui.scala.swing


import java.awt.Color
import javax.swing.JOptionPane

import com.google.inject.Inject
import connectfour.controller._
import connectfour.model.GameField
import connectfour.ui.UI
import connectfour.ui.gui.scala.swing.widgets.{StatusDisplay, ArrowField, CoinField, ToolBar}
import connectfour.util.observer.{IObserver, Observable}
import scala.swing._

/**
 * Created by maharr on 13.11.15.
 */
class SwingGUI @Inject() (controller: GameController) extends Frame with UI with IObserver {

  private var players = controller.getPlayers

  visible = true
  title = "Connect 4 in Scala"
  menuBar = new ToolBar(controller)

  val arrowField = new ArrowField(controller)
  val coinField = new CoinField(controller, arrowField)
  val statusDisplay = new StatusDisplay(controller)

  contents = new BorderPanel {
    add(statusDisplay, BorderPanel.Position.South)
    add(coinField, BorderPanel.Position.Center)
    add(arrowField, BorderPanel.Position.North)
  }

  reactions += {
    case e: NewGameScalaSwingEvent => drawGameField
    case e: DropCoinScalaSwingEvent => drawGameField
    case e: UndoScalaSwingEvent => drawGameField
    case e: RedoScalaSwingEvent => drawGameField
  }

  override def update {
    drawGameField
  }

  override def drawGameField {
    players = controller.getPlayers
    statusDisplay.update

    //TODO: Nicht mehr Default Row und Col verwenden (soll skalierbar sein)
    for (currentRow <- 0 until GameField.DEFAULT_ROWS) {
      for (currentColumn <- 0 until GameField.DEFAULT_COLUMNS) {
        val player = this.controller.getPlayerAt(currentRow, currentColumn)

        if (player == null) {
          coinField.CELLS(currentRow)(currentColumn).foreground = Color.WHITE
        } else if (player == players(0)) {
          coinField.CELLS(currentRow)(currentColumn).foreground = players(0).color
        } else if (player == players(1)) {
          coinField.CELLS(currentRow)(currentColumn).foreground = players(1).color
        } else {
          //TODO: Noch machen bei Fehlerfall
//          JOptionPane.showMessageDialog(this,
//            "Fehler beim Einfuegen der Muenze in Zeile "
//              + currentRow
//              + " und Spalte "
//              + currentColumn
//              + "!")
          throw new RuntimeException("Problem: Fehler beim Einfuegen der Muenze in Zeile " + currentRow + " und Spalte " + currentColumn + "!");
        }
      }
    }
    repaint
  }
}
