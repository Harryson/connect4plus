package connectfour.ui.gui.swing

import java.awt.Color._

import connectfour.controller.IController
import connectfour.model.Player

import scala.swing._

/**
 * Created by maharr on 13.11.15.
 */
class StatusDisplay(controller: IController) extends FlowPanel {

  val status = new Label
  contents += status

  showPlayerOnTurn()

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
      status.foreground = RED
    } else {
      status.foreground = BLUE
    }
  }

  private def showWinner() {
    val winner = String.format("%s hat gewonnen!", controller.getWinner)
    status.text = winner
  }

  private def showPlayerOnTurn() {
    setPlayersColor
    val playerOnTurn = String.format("Spieler %s ist dran", controller.getPlayerNameOnTurn)
    status.text = playerOnTurn
  }
}
