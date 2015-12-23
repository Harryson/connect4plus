package connectfour.ui.gui.scala.swing.widgets

import java.awt.Color
import javax.swing.ImageIcon

import connectfour.controller.Connect4GameController
import connectfour.model.Connect4GameField

import scala.swing._

/**
 * Created by maharr on 16.11.15.
 *
 * Top row of playing field. Selected column shows a red arrow.
 */
class ArrowField(gameController: Connect4GameController) extends FlowPanel with Field {

  // constructor
  background = Color.LIGHT_GRAY
  val CELLS = new Array[Button](Connect4GameField.FIELD_COLUMNS)
  for (col <- 0 until Connect4GameField.FIELD_COLUMNS) {
    val CELL = new ButtonCell(this) {
      background = Color.LIGHT_GRAY
    }
    CELLS(col) = CELL
    contents += CELL
  }

  // Mouse in coin field enter new column
  def updateEnter(col: Int) {
    CELLS(col).icon = new ImageIcon("./res/arrow.png")
  }

  // Mouse in coint field exit current column
  def updateExit(col: Int) {
    CELLS(col).icon = null
  }

  override def mouseEntered(buttonCell: ButtonCell) {
    buttonCell.icon = new ImageIcon("./res/arrow.png")
  }

  override def mouseExit(buttonCell: ButtonCell) {
    buttonCell.icon = null
  }

  // Drop a coin form arrow field (top row)
  override def mouseReleased(buttonCell: ButtonCell) {
    gameController.dropCoin(CELLS.indexOf(buttonCell))
  }
}