package connect4.model

import connect4.model.mocks.MockBase

class Connect4ComputerTest extends MockBase{
   /*
    *   0   1   2   3   4   5   6
    * |___|___|___|___|___|___|___|
    * |_X_|___|___|___|___|___|___|
    * |_O_|___|___|___|_X_|___|___|
    * |_X_|___|___|_O_|_O_|___|___|
    * |_X_|___|_X_|_O_|_O_|_X_|_O_|
    * |_X_|___|_X_|_O_|_O_|_O_|_X_|
    *
    */

  "Computer" should
    "should prevent 'Hugo' from winning in column 3" in {
    reset
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, player)
    gameField.dropCoin(0, opponent)

    gameField.dropCoin(2, opponent)
    gameField.dropCoin(2, opponent)

    gameField.dropCoin(3, player)
    gameField.dropCoin(3, player)
    gameField.dropCoin(3, player)

    gameField.dropCoin(4, player)
    gameField.dropCoin(4, player)
    gameField.dropCoin(4, player)
    gameField.dropCoin(4, opponent)

    gameField.dropCoin(5, player)
    gameField.dropCoin(5, opponent)

    gameField.dropCoin(6, opponent)

    if (controller.getPlayerOnTurn == player) {
      controller.dropCoin(6)
    } else {
      gameField.dropCoin(6, player)
      controller.dropCoinEventScala.dropCoin()
    }

    controller.getPlayerAt(3, 3) should be(opponent)
  }

}
