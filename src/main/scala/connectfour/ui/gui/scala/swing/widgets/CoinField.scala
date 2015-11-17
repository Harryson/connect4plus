package connectfour.ui.gui.scala.swing.widgets

import java.awt.{RenderingHints, Color}

import connectfour.controller.IController
import connectfour.model.GameField

import scala.swing._
import scala.swing.event.{MouseReleased, MouseExited, MouseEntered}

/**
 * Created by maharr on 16.11.15.
 */
class CoinField(iController: IController, arrowField: ArrowField) extends GridPanel(GameField.DEFAULT_ROWS, GameField.DEFAULT_COLUMNS) with Field{

  val COLOR_PLAYER_1 = Color.YELLOW // Computer
  val COLOR_PLAYER_2 = Color.RED // Human
  var COLOR_BLANK = Color.WHITE
  val CELLS = Array.ofDim[ButtonCell](GameField.DEFAULT_ROWS, GameField.DEFAULT_COLUMNS)

  for (row <- (GameField.DEFAULT_ROWS - 1) to 0 by -1) {
    for (col <- 0 until GameField.DEFAULT_COLUMNS) {
      var CELL = new ButtonCell(this) {
        background = Color.BLUE
        foreground = Color.WHITE

        override def paintComponent(g: Graphics2D) {
          super.paintComponent(g); // paints background

          val g2 = g.asInstanceOf[Graphics2D]

          // Activate Anti-Aliasing!
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          val radius = RADIUS;
          g2.fillOval(X_POSITION, Y_POSITION, radius, radius);
        }

      }
      CELLS(row)(col) = CELL
      contents += CELL
    }
  }

  override def mouseEntered(buttonCell: ButtonCell) {
    arrowField.updateEnter(CELLS.flatten.indexOf(buttonCell) % GameField.DEFAULT_COLUMNS)
  }

  override def mouseExit(buttonCell: ButtonCell) {
    arrowField.updateExit(CELLS.flatten.indexOf(buttonCell) % GameField.DEFAULT_COLUMNS)
  }

  override def mouseReleased(buttonCell: ButtonCell) {
    iController.dropCoinWithSuccessFeedback(CELLS.flatten.indexOf(buttonCell) % GameField.DEFAULT_COLUMNS)
  }

}
