package connect4.model

import ai.MiniMax
import connect4.model.mocks.MockBase
import controller.NoMovePossible

/**
 * Created by maharr on 16.01.16.
 */
class MinMaxTest extends MockBase {

  /*
  *   0   1   2   3   4   5   6
  * |_O_|_X_|_O_|_X_|_O_|_X_|_O_|
  * |_O_|_X_|_O_|_X_|_O_|_X_|_O_|
  * |_O_|_X_|_O_|_X_|_O_|_X_|_O_|
  * |_X_|_O_|_X_|_O_|_X_|_O_|_X_|
  * |_X_|_O_|_X_|_O_|_X_|_O_|_X_|
  * |_X_|_O_|_X_|_O_|_X_|_O_|_X_|
  *
  */

  "The gamefield" should
    "return 'Boss' (KI) as winner, because he won vertically from bottom to top" in {
    reset

    // column 0
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, player)
    gameField.dropCoin(0, player)
    gameField.dropCoin(0, player)

    // column 1
    gameField.dropCoin(1, player)
    gameField.dropCoin(1, player)
    gameField.dropCoin(1, player)
    gameField.dropCoin(1, opponent)
    gameField.dropCoin(1, opponent)
    gameField.dropCoin(1, opponent)

    // column 2
    gameField.dropCoin(2, opponent)
    gameField.dropCoin(2, opponent)
    gameField.dropCoin(2, opponent)
    gameField.dropCoin(2, player)
    gameField.dropCoin(2, player)
    gameField.dropCoin(2, player)

    // column 3
    gameField.dropCoin(3, player)
    gameField.dropCoin(3, player)
    gameField.dropCoin(3, player)
    gameField.dropCoin(3, opponent)
    gameField.dropCoin(3, opponent)
    gameField.dropCoin(3, opponent)

    // column 4
    gameField.dropCoin(4, opponent)
    gameField.dropCoin(4, opponent)
    gameField.dropCoin(4, opponent)
    gameField.dropCoin(4, player)
    gameField.dropCoin(4, player)
    gameField.dropCoin(4, player)

    // column 5
    gameField.dropCoin(5, player)
    gameField.dropCoin(5, player)
    gameField.dropCoin(5, player)
    gameField.dropCoin(5, opponent)
    gameField.dropCoin(5, opponent)
    gameField.dropCoin(5, opponent)

    // column 6
    gameField.dropCoin(6, opponent)
    gameField.dropCoin(6, opponent)
    gameField.dropCoin(6, opponent)
    gameField.dropCoin(6, player)
    gameField.dropCoin(6, player)
    gameField.dropCoin(6, player)

    MiniMax.getNextMove(controller) should be(NoMovePossible)
  }
}
