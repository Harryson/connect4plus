package connectfour.ui.gui.swing

import connectfour.controller.IController;

import javax.swing._
import java.awt._

import connectfour.model.Player

class StatusDisplay(controller: IController) extends JPanel {
  private val status = new JLabel();

  this.add(status)
  this.setBackground(Color.WHITE)
  this.showPlayerOnTurn()

  def update {
    if (controller.userHasWon) {
      showWinner()
    } else {
      showPlayerOnTurn()
    }
  }

  private def setPlayersColor {
    val players: Array[Player] = controller.getPlayers;
    val player1 = players(0)

    if (controller.getPlayerOnTurn == player1) { // Player 1
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
    val playerOnTurn = String.format("Spieler %s ist dran", controller.getPlayerNameOnTurn)
    status.setText(playerOnTurn)
  }
}