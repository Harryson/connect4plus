package connectfour.ui.gui.scala.swing.widgets


import java.awt.Color

import connectfour.controller.Connect4GameController
import java.awt.Color._
import scala.swing._

/**
 * Created by maharr on 13.11.15.
 *
 * Status display shows player on turn or winner
 */
class StatusDisplay() extends FlowPanel {

  // constructor
  background = Color.LIGHT_GRAY
  val status = new Label
  contents += status
  showPlayerOnTurn

  def update {
    val controller = Connect4GameController.getCurrentInstance

    if (controller.getWinner != "") {
      showWinner
    } else {
      showPlayerOnTurn
    }
  }

  private def setPlayersColor {
    val controller = Connect4GameController.getCurrentInstance
    val (user, computer) = controller.getPlayers


    if (controller.getPlayerOnTurn == user) {
      //TODO: Farben überprüfen
      status.foreground = RED
    } else {
      status.foreground = YELLOW
    }
  }

  private def showWinner() {
    val controller = Connect4GameController.getCurrentInstance

    status.text = String.format("%s hat gewonnen!", controller.getWinner)
  }

  private def showPlayerOnTurn() {
    val controller = Connect4GameController.getCurrentInstance

    setPlayersColor
    status.text = String.format("Spieler %s ist dran", controller.getPlayerOnTurn)
  }
}
