package connect4.model

import connect4.model.mocks.MockBase

class GameFieldWinTest extends MockBase {

  /**
   * 0
   * 0X
   * 0XX
   * 0XX00
   */

  "The gamefield" should
    "return 'Boss' (KI) as winner, because he won vertically from bottom to top" in {
    reset
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(1, player)
    gameField.dropCoin(1, opponent)
    gameField.dropCoin(2, player)
    gameField.dropCoin(3, opponent)
    gameField.dropCoin(2, player)
    gameField.dropCoin(2, opponent)
    gameField.dropCoin(3, player)
    gameField.dropCoin(4, opponent)
    gameField.dropCoin(3, player)
    gameField.dropCoin(3, opponent)

    gameField.getWinner should be("Boss")
  }

  "The gamefield" should
    "return 'Boss' (KI) as winner, because he won horizontically" in {
    reset
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(1, player)
    gameField.dropCoin(1, opponent)
    gameField.dropCoin(2, player)
    gameField.dropCoin(2, opponent)
    gameField.dropCoin(2, player)
    gameField.dropCoin(4, opponent)
    gameField.dropCoin(3, player)
    gameField.dropCoin(3, opponent)
    gameField.dropCoin(5, player)
    gameField.dropCoin(4, opponent)

    gameField.getWinner should be("Boss")
  }

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

  "The gamefield" should
    "return '' as winner in this complex match" in {
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
    gameField.dropCoin(6, player)

    gameField.getWinner should be("")
  }

  /*
  *   0   1   2   3   4   5   6
  * |___|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_O_|___|___|_O_|_X_|___|___|
  * |_X_|___|___|_O_|_O_|___|___|
  * |_X_|___|_X_|_O_|_O_|_X_|_O_|
  * |_X_|___|_X_|_O_|_O_|_O_|_X_|
  *
  */

  "The gamefield" should
    "return 'Hugo' as winner in this complex match" in {
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
    gameField.dropCoin(3, player)

    gameField.dropCoin(4, player)
    gameField.dropCoin(4, player)
    gameField.dropCoin(4, player)
    gameField.dropCoin(4, opponent)

    gameField.dropCoin(5, player)
    gameField.dropCoin(5, opponent)

    gameField.dropCoin(6, opponent)
    gameField.dropCoin(6, player)

    gameField.getWinner should be("Hugo")
  }
}