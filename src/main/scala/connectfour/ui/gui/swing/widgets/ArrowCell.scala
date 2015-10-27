package connectfour.ui.gui.swing.widgets

import javax.swing._
import java.awt._

class ArrowCell(column: Int) extends JPanel {

  private val ARROW_WIDTH = 10
  private val X_MIDDLE_LEFT = 34
  private val ARROW_LENGTH = 40
  private val Y_TOP = 20

  private var drawArrow = false

  // x coordinates
  private val xMiddleLeft = X_MIDDLE_LEFT
  private val xMiddleRight = xMiddleLeft + ARROW_WIDTH

  // y coordinates
  private val arrowLength = ARROW_LENGTH
  private val yTop = Y_TOP
  private val arrow = new Polygon()

  {
    setArrowBackAndForegroundColor
    createArrow
  }

  private def setArrowBackAndForegroundColor {
    setForeground(Color.RED)
    setBackground(Color.WHITE)
  }

  private def createArrow {
    arrow.addPoint(this.xMiddleLeft, this.yTop)
    val yMiddle = yTop + arrowLength / 2
    arrow.addPoint(this.xMiddleLeft, yMiddle)
    val xLeft = xMiddleLeft - ARROW_WIDTH
    arrow.addPoint(xLeft, yMiddle)
    val yArrowPoint = yTop + arrowLength
    val xArrowPoint = xMiddleLeft + ((xMiddleRight - xMiddleLeft) / 2)
    arrow.addPoint(xArrowPoint, yArrowPoint)
    val xRight = xMiddleRight + ARROW_WIDTH
    arrow.addPoint(xRight, yMiddle)
    arrow.addPoint(this.xMiddleRight, yMiddle)
    arrow.addPoint(this.xMiddleRight, this.yTop)
  }

  def showArrow(show: Boolean) {
    this.drawArrow = show
  }

  override def paintComponent(g: Graphics) {
    super.paintComponent(g) // paints background

    if (this.drawArrow) {
      val g2 = g.asInstanceOf[Graphics2D]

      // Activate Anti-Aliasing!
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

      g2.fillPolygon(arrow)
    }
  }
}