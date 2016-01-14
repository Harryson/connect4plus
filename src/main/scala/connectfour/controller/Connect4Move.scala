package connectfour.controller

import controller.Move

/**
 * User: Stefano Di Martino
 * Date: 25.01.14
 * Time: 17:54
 */
case class Connect4Move(controller: Connect4GameController, column: Int) extends Move {
  def execute {
    controller.dropCoin(column)

    //TODO: Redo not correct, computers' move isn't correct recorded
    //    val oldGameField = controller.gameField.cloneGameField()
    //
    //    controller.undoManager.addCommand(
    //      () => controller.gameField = oldGameField
    //    )
  }
}