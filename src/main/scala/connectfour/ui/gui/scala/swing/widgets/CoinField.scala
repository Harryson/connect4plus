package connectfour.ui.gui.scala.swing.widgets

import java.awt.{Color, RenderingHints}

import connectfour.controller.{Connect4GameField, Connect4GameController}

import scala.swing._

/**
 * Created by maharr on 16.11.15.
 */
class CoinField(gameController: Connect4GameController, arrowField: ArrowField)
  extends GridPanel(Connect4GameField.FIELD_ROWS, Connect4GameField.FIELD_COLUMNS) with Field {

  val CELLS = Array.ofDim[ButtonCell](Connect4GameField.FIELD_ROWS, Connect4GameField.FIELD_COLUMNS)

  // constructor
  background = Color.LIGHT_GRAY

  // turn row numbers up side down in first for loop
  drawCoinFieldRow(Connect4GameField.FIELD_ROWS - 1)

  private def drawCoinFieldRow(row: Int) {
    if (row >= 0) {
      drawCoinFieldColumn(0, row)

      drawCoinFieldRow(row - 1)
    }
  }

  private def drawCoinFieldColumn(column: Int, row: Int) {
    if (column < Connect4GameField.FIELD_COLUMNS) {
      val CELL = new ButtonCell(this) {
        background = Color.BLUE
        foreground = Color.WHITE

        override def paintComponent(g: Graphics2D) {
          super.paintComponent(g); // paints background
          val g2 = g.asInstanceOf[Graphics2D]
          // Activate Anti-Aliasing!
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
          val radius = RADIUS
          g2.fillOval(X_POSITION, Y_POSITION, radius, radius)
        }
      }
      CELLS(row)(column) = CELL
      contents += CELL

      drawCoinFieldColumn(column + 1, row)
    }
  }

  // Update arrow field
  override def mouseEntered(buttonCell: ButtonCell) {
    arrowField.updateEnter(CELLS.flatten.indexOf(buttonCell) % Connect4GameField.FIELD_COLUMNS)
  }

  // Update arrow field
  override def mouseExit(buttonCell: ButtonCell) {
    arrowField.updateExit(CELLS.flatten.indexOf(buttonCell) % Connect4GameField.FIELD_COLUMNS)
  }

  // Drop a coin form coin field (below top row)
  override def mouseReleased(buttonCell: ButtonCell) {
    gameController.dropCoin(CELLS.flatten.indexOf(buttonCell) % Connect4GameField.FIELD_COLUMNS)
  }
}