package connectfour.ui.gui.java.swing

import java.awt.{Color, Dimension, GridLayout}
import javax.swing.{BorderFactory, BoxLayout, JFrame, JOptionPane, JPanel}

import connectfour.controller.Connect4GameController
import connectfour.model.Connect4GameField
import connectfour.ui.UI
import connectfour.ui.gui.java.swing.controller.ArrowManager
import connectfour.ui.gui.java.swing.widgets.{ArrowCell, GUICoin}
import connectfour.util.observer.IObserver

import scala.collection.mutable.ArrayBuffer

class SwingGUI extends JFrame with UI with IObserver {
  val DIMENSION_PANEL = new Dimension(800, 600)
  val DIMENSION_CELL_WRAPPER = new Dimension(400, 300)
  val DIMENSION_STATUS_DISPLAY = new Dimension(100, 100)
  val CONTENT_PANE_BOARDER = BorderFactory.createEmptyBorder(20, 20, 10, 0)
  val WINDOW_WIDTH = 800
  val WINDOW_HEIGHT = 710

  // UI Stuff
  private val cellWrapper = new JPanel()
  private val coinCells = Array.ofDim[GUICoin](Connect4GameField.FIELD_ROWS, Connect4GameField.FIELD_COLUMNS)
  private val listArrowCells = new ArrayBuffer[ArrowCell](Connect4GameField.FIELD_COLUMNS)

  private val statusDisplay = new StatusDisplay

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
    pageAxisPanel.add(new ToolBar(this, this))
    pageAxisPanel.add(lineAxisPanel)

    contentPane.add(pageAxisPanel)

    contentPane.setBorder(CONTENT_PANE_BOARDER)

    setContentPane(contentPane)
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    setSize(WINDOW_WIDTH, WINDOW_HEIGHT)
    setVisible(true)

    createBufferStrategy(2)
  }

  drawGameField

  private def initGameField {
    for (row <- 0 until Connect4GameField.FIELD_ROWS) {
      for (col <- 0 until Connect4GameField.FIELD_COLUMNS) {
        coinCells(row)(col) = new GUICoin(col, ArrowManager)
      }
    }

    for (col <- 0 until Connect4GameField.FIELD_COLUMNS) {
      listArrowCells += new ArrowCell(col)
    }

    ArrowManager.arrowCells = listArrowCells
  }

  private def addCells {
    for (col <- 0 until Connect4GameField.FIELD_COLUMNS) {
      cellWrapper.add(listArrowCells(col))
    }

    for (row <- (Connect4GameField.FIELD_ROWS - 1) to 0 by -1) {
      for (col <- 0 until Connect4GameField.FIELD_COLUMNS) {
        cellWrapper.add(coinCells(row)(col))
      }
    }
  }

  override def drawGameField {
    val controller = Connect4GameController.getCurrentInstance
    val (user, computer) = controller.getPlayers
    statusDisplay.update

    for (currentRow <- 0 until Connect4GameField.FIELD_ROWS) {
      for (currentColumn <- 0 until Connect4GameField.FIELD_COLUMNS) {
        val player = controller.getPlayerAt(currentRow, currentColumn)

        if (player == null) {
          coinCells(currentRow)(currentColumn).setColor(Color.WHITE)
        } else if (player == user) {
          coinCells(currentRow)(currentColumn).setColor(Color.RED)
        } else if (player == computer) {
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