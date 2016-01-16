package connectfour.ui.gui.scala.swing.widgets

import java.awt.Color
import javax.swing.ImageIcon

import connectfour.controller.{Connect4GameController, Connect4GameField, Drop}

import scala.swing._

/**
 * Created by maharr on 16.11.15.
 *
 * Top row of playing field. Selected column shows a red arrow.
 */
class ArrowField(gameController: Connect4GameController) extends FlowPanel with Field {
  val CELLS = new Array[Button](Connect4GameField.FIELD_COLUMNS)

  implicit def sentenceToInt(str: String) = new Drop(gameController, str)

  // constructor
  background = Color.LIGHT_GRAY
  drawArrowField(0)

  private def drawArrowField(column: Int) {
    if (column < Connect4GameField.FIELD_COLUMNS) {
      val CELL = new ButtonCell(this) {
        background = Color.LIGHT_GRAY
      }
      CELLS(column) = CELL
      contents += CELL

      drawArrowField(column + 1)
    }
  }

  // Mouse in coin field enter new column, update arrow field
  def updateEnter(col: Int) {
    CELLS(col).icon = new ImageIcon("./res/arrow.png")
  }

  // Mouse in coint field exit current column, update arrow field
  def updateExit(col: Int) {
    CELLS(col).icon = null
  }

  // Update arrow field
  override def mouseEntered(buttonCell: ButtonCell) {
    buttonCell.icon = new ImageIcon("./res/arrow.png")
  }

  // Update arrow field
  override def mouseExit(buttonCell: ButtonCell) {
    buttonCell.icon = null
  }

  // Drop a coin form arrow field (top row)
  override def mouseReleased(buttonCell: ButtonCell) {
    val sentence = "Drop coin at " + CELLS.indexOf(buttonCell)
    sentence.dropCoin
  }
}