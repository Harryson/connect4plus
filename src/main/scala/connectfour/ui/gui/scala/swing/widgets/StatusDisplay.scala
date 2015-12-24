package connectfour.ui.gui.scala.swing.widgets


import java.awt.Color
import java.awt.Color._

import connectfour.controller.Connect4GameController

import scala.swing._

/**
 * Created by maharr on 13.11.15.
 *
 * Status display shows player on turn or winner
 */
class StatusDisplay(gameController: Connect4GameController) extends FlowPanel {

  // constructor
  background = Color.LIGHT_GRAY
  val status = new Label
  contents += status
  showPlayerOnTurn()

  def update() {
    if (gameController.getWinner != "") {
      showWinner()
    } else {
      setPlayersColor()
      showPlayerOnTurn()
    }
  }

  private def setPlayersColor() {
    val (user, _) = gameController.getPlayers

    if (gameController.getPlayerOnTurn == user) {
      status.foreground = RED
    } else {
      status.foreground = YELLOW
    }
  }

  private def showWinner() {
    status.text = String.format("%s has won!", gameController.getWinner)
  }

  private def showPlayerOnTurn() {
    setPlayersColor()
    status.text = String.format("%s is next", gameController.getPlayerOnTurn)
  }
}
