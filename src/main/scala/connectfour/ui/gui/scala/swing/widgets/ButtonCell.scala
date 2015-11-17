package connectfour.ui.gui.scala.swing.widgets

import java.awt.{Color, Dimension}

import scala.swing.Button
import scala.swing.event.{MouseReleased, MouseExited, MouseEntered}

/**
 * Created by maharr on 16.11.15.
 */
class ButtonCell(row: Field) extends Button {
  val RADIUS = 40
  val X_POSITION = 20
  val Y_POSITION = 20
  val X_SIZE_BUTTON = RADIUS + 2 * X_POSITION
  val Y_SIZE_BUTTON = RADIUS + 2 * Y_POSITION

  preferredSize = new Dimension(X_SIZE_BUTTON, Y_SIZE_BUTTON)
  listenTo(mouse.moves, mouse.clicks)

  reactions += {
    case b: MouseEntered => row.mouseEntered(this)
    case b: MouseExited => row.mouseExit(this)
    case b: MouseReleased => row.mouseReleased(this)
  }
}
