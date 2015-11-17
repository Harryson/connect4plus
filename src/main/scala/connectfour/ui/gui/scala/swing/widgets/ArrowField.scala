package connectfour.ui.gui.scala.swing.widgets

import javax.swing.ImageIcon

import connectfour.controller.IController
import connectfour.model.GameField

import scala.swing.{FlowPanel, Button}

/**
 * Created by maharr on 16.11.15.
 */
class ArrowField(iController: IController) extends FlowPanel with Field{

  val CELLS = new Array[Button](GameField.DEFAULT_COLUMNS)

  for (col <- 0 until GameField.DEFAULT_COLUMNS) {
    val CELL = new ButtonCell(this) {

    }
    CELLS(col) = CELL
    contents += CELL
  }

  def updateEnter(col: Int) {
    CELLS(col).icon = new ImageIcon("./res/arrow.png")
  }

  def updateExit(col: Int) {
    CELLS(col).icon = null
  }

  override def mouseEntered(buttonCell: ButtonCell) {

    buttonCell.icon = new ImageIcon("./res/arrow.png")
  }

  override def mouseExit(buttonCell: ButtonCell) {
    buttonCell.icon = null
  }

  override def mouseReleased(buttonCell: ButtonCell): Unit = {
    iController.dropCoinWithSuccessFeedback(CELLS.indexOf(buttonCell))
  }

}