package connectfour.ui.gui.scala.swing.widgets

import scala.swing.Button

/**
 * Created by maharr on 16.11.15.
 */
trait Field {
  def mouseEntered(buttonCell: ButtonCell)
  def mouseExit(buttonCell: ButtonCell)
  def mouseReleased(buttonCell: ButtonCell)
}
