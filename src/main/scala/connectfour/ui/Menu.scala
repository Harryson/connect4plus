package connectfour.ui

import connectfour.controller.IController

/**
 * Created by maharr on 19.10.15.
 */
abstract class Menu(controller: IController) {

  def saveGame

  def loadSaveGame

  def newGame = controller.newGame

  //TODO: Braucht an die Methode auch mit Spieler?
  //def newGameWithPlayer = controller.newGame(player1, player2)

  def quitGame

  def show

  def exitGame = System.exit(0)
}
