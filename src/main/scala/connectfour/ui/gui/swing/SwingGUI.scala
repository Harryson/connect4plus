package connectfour.ui.gui.swing

import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import java.util.ArrayList
import java.util.List

import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.border.Border

import com.google.inject.Inject
import com.google.inject.Singleton

import connectfour.controller.IController
import connectfour.model.GameField
import connectfour.model.Player
import connectfour.ui.UI
import connectfour.ui.gui.swing.controller.ArrowManager
import connectfour.ui.gui.swing.widgets.ArrowCell
import connectfour.ui.gui.swing.widgets.GUICoin
import connectfour.util.observer.IObserver

import scala.collection.mutable.ArrayBuffer

class SwingGUI @Inject() (controller: IController) extends JFrame with UI with IObserver {
  val DIMENSION_PANEL = new Dimension(800, 600)
  val DIMENSION_CELL_WRAPPER = new Dimension(400, 300)
  val DIMENSION_STATUS_DISPLAY = new Dimension(100, 100)
  val CONTENT_PANE_BOARDER = BorderFactory.createEmptyBorder(20, 20, 10, 0)
  val WINDOW_WIDTH = 800
  val WINDOW_HEIGHT = 710

  // UI Stuff
  private val cellWrapper = new JPanel()
  private val coinCells = Array.ofDim[GUICoin](GameField.DEFAULT_ROWS, GameField.DEFAULT_COLUMNS)
  private val listArrowCells = new ArrayBuffer[ArrowCell](GameField.DEFAULT_COLUMNS)

  private val statusDisplay = new StatusDisplay(controller)
  private var players = controller.getPlayers

  {
    initGameField

    val contentPane = new JPanel()
    contentPane.setBackground(Color.WHITE)

    val lineAxisPanel = new JPanel()
    val pageAxisPanel = new JPanel()

    val lineAxisLayout = new BoxLayout(lineAxisPanel, BoxLayout.LINE_AXIS)
    val pageAxisLayout = new BoxLayout(pageAxisPanel, BoxLayout.PAGE_AXIS)

    lineAxisPanel.setLayout(lineAxisLayout)
    pageAxisPanel.setLayout(pageAxisLayout)

    // Layout setzen
    cellWrapper.setLayout(new GridLayout(7, 7))

    addCells

    lineAxisPanel.setPreferredSize(DIMENSION_PANEL)

    cellWrapper.setPreferredSize(DIMENSION_CELL_WRAPPER)
    statusDisplay.setPreferredSize(DIMENSION_STATUS_DISPLAY)

    lineAxisPanel.add(cellWrapper)
    lineAxisPanel.add(statusDisplay)
    pageAxisPanel.add(new ToolBar(controller, this, this))
    pageAxisPanel.add(lineAxisPanel)

    contentPane.add(pageAxisPanel)

    contentPane.setBorder(CONTENT_PANE_BOARDER)

    setContentPane(contentPane)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
    setVisible(true)

    createBufferStrategy(2)
  }

  private def initGameField {
    for (row <- 0 until GameField.DEFAULT_ROWS) {
      for (col <- 0 until GameField.DEFAULT_COLUMNS) {
        coinCells(row)(col) = new GUICoin(controller, col, ArrowManager)
      }
    }

    for (col <- 0 until GameField.DEFAULT_COLUMNS) {
      listArrowCells += new ArrowCell(col)
    }

    ArrowManager.arrowCells = listArrowCells
  }

  private def addCells {
    for (col <- 0 until GameField.DEFAULT_COLUMNS) {
      cellWrapper.add(listArrowCells(col))
    }

    for (row <- (GameField.DEFAULT_ROWS - 1) to 0 by -1) {
      for (col <- 0 until GameField.DEFAULT_COLUMNS) {
        cellWrapper.add(coinCells(row)(col))
      }
    }
  }

  override def drawGameField {
    players = controller.getPlayers
    statusDisplay.update

    for (currentRow <- 0 until GameField.DEFAULT_ROWS) {
      for (currentColumn <- 0 until GameField.DEFAULT_COLUMNS) {
        val player = this.controller.getPlayerAt(currentRow, currentColumn)

        if (player == null) {
          coinCells(currentRow)(currentColumn).setColor(Color.WHITE)
        } else if (player == players(0)) {
          coinCells(currentRow)(currentColumn).setColor(Color.RED)
        } else if (player == players(1)) {
          coinCells(currentRow)(currentColumn).setColor(Color.YELLOW)
        } else {
          JOptionPane.showMessageDialog(this,
            "Fehler beim Einfuegen der Muenze in Zeile "
              + currentRow
              + " und Spalte "
              + currentColumn
              + "!")
        }
      }
    }
    repaint()
  }

  override def update {
    drawGameField
  }
}