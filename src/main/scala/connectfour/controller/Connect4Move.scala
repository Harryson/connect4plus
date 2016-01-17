package connectfour.controller

import controller.Move

/**
 * User: Stefano Di Martino
 * Date: 25.01.14
 * Time: 17:54
 */
case class Connect4Move(gameController: Connect4GameController, column: Int) extends Move {
  def execute: String = {
    implicit def sentenceToInt(str: String) = new Drop(gameController, str)

    val sentence = "Drop coin at " + column
    sentence.dropCoin

    //TODO: Redo not correct, computers' move isn't correct recorded
    //    val oldGameField = gameController.gameField.cloneGameField()
    //
    //    gameController.undoManager.addCommand(
    //      () => gameController.gameField = oldGameField
    //    )
  }
}