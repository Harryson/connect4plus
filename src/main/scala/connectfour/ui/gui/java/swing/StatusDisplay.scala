package connectfour.ui.gui.java.swing

import java.awt.Color
import javax.swing.{JLabel, JPanel}

import connectfour.controller.Connect4GameController

/**
 * Created by maharr on 13.11.15.
 */
class StatusDisplay() extends JPanel {
  private val status = new JLabel();

  this.add(status)
  this.setBackground(Color.WHITE)
  this.showPlayerOnTurn()

  def update {
    val controller = Connect4GameController.getCurrentInstance

    if (controller.getWinner != "") {
      showWinner()
    } else {
      showPlayerOnTurn()
    }
  }

  private def setPlayersColor {
    val controller = Connect4GameController.getCurrentInstance
    val (user, computer) = controller.getPlayers

    if (controller.getPlayerOnTurn == user) {
      // Player 1
      status.setForeground(Color.RED)
    } else {
      status.setForeground(Color.YELLOW)
    }
  }

  private def showWinner() {
    val controller = Connect4GameController.getCurrentInstance

    val winner = String.format("%s hat gewonnen!", controller.getWinner)
    status.setText(winner)
  }

  private def showPlayerOnTurn() {
    val controller = Connect4GameController.getCurrentInstance

    setPlayersColor
    val playerOnTurn = String.format("Spieler %s ist dran", controller.getPlayerOnTurn)
    status.setText(playerOnTurn)
  }
}