package connectfour.ui.gui.scala.swing.widgets


import java.awt.Color

import connectfour.controller.IController
import connectfour.model.Player
import scala.swing._

/**
 * Created by maharr on 13.11.15.
 *
 * Status display shows player on turn or winner
 */
class StatusDisplay(controller: IController) extends FlowPanel {

  // constructor
  background = Color.LIGHT_GRAY
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
    val players: Array[Player] = controller.getPlayers
    val player1 = players(0) // Human
    val player2 = players(1) // Computer

    if (controller.getPlayerOnTurn == player1) { // Player 1
      status.foreground = player1.color
    } else {
      status.foreground = player2.color
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
