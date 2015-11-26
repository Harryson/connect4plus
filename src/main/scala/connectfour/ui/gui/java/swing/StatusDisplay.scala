package connectfour.ui.gui.java.swing

import java.awt.Color._
import connectfour.controller.Connect4GameController
import scala.swing._

/**
 * Created by maharr on 13.11.15.
 */
class StatusDisplay extends FlowPanel {

  val status = new Label
  contents += status

  showPlayerOnTurn()

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
    val (user, _) = controller.getPlayers

    if (controller.getPlayerOnTurn == user) {
      status.foreground = RED
    } else {
      status.foreground = BLUE
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
