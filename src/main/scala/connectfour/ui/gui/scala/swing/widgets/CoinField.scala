package connectfour.ui.gui.scala.swing.widgets

import java.awt.{Color, RenderingHints}

import connectfour.controller.Connect4GameControllerImpl
import connectfour.model.Connect4GameField

import scala.swing._

/**
 * Created by maharr on 16.11.15.
 */
class CoinField(arrowField: ArrowField) extends GridPanel(Connect4GameField.FIELD_ROWS, Connect4GameField.FIELD_COLUMNS) with Field{

  // constructor
  background = Color.LIGHT_GRAY
  val CELLS = Array.ofDim[ButtonCell](Connect4GameField.FIELD_ROWS, Connect4GameField.FIELD_COLUMNS)

  // turn row numbers up side down in first for loop
  for (row <- (Connect4GameField.FIELD_ROWS - 1) to 0 by -1) {
    for (col <- 0 until Connect4GameField.FIELD_COLUMNS) {
      var CELL = new ButtonCell(this) {
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
      CELLS(row)(col) = CELL
      contents += CELL
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
    val controller = Connect4GameControllerImpl.getCurrentInstance

    controller.dropCoin(CELLS.flatten.indexOf(buttonCell) % Connect4GameField.FIELD_COLUMNS)
  }
}
