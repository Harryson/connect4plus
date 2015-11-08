package connectfour.ui.gui.swing

import javax.swing._
import java.awt._
import connectfour.controller.Connect4GameController

class StatusDisplay(controller: Connect4GameController) extends JPanel {
  private val status = new JLabel();

  this.add(status)
  this.setBackground(Color.WHITE)
  this.showPlayerOnTurn()

  def update {
    if (controller.getWinner != "") {
      showWinner()
    } else {
      showPlayerOnTurn()
    }
  }

  private def setPlayersColor {
    val (user, _) = controller.getPlayers

    if (controller.getPlayerOnTurn == user) {
      status.setForeground(Color.RED)
    } else {
      status.setForeground(Color.BLUE)
    }
  }

  private def showWinner() {
    val winner = String.format("%s hat gewonnen!", controller.getWinner)
    status.setText(winner)
  }

  private def showPlayerOnTurn() {
    setPlayersColor
    val playerOnTurn = String.format("Spieler %s ist dran", controller.getPlayerOnTurn)
    status.setText(playerOnTurn)
  }
}