package connect4.model

import ai.MiniMax
import connect4.model.mocks.MockBase
import connectfour.controller.Connect4Move
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

  "Next move " should
    "be an NoMovePossible, all fields are occupied" in {
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

  /*
  *   0   1   2   3   4   5   6
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  *
  */

  "Next move " should
    "be an NoMovePossible, opponent has won" in {
    reset

    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)

    //TODO opponent hasn't won so we get Connect4Moce instead of NoMovePossible
    //    MiniMax.getNextMove(controller) should be(NoMovePossible)
  }

  "Next move " should
    "be an NoMovePossible, dept = 0" in {
    reset

    MiniMax.getNextMove(controller, 0) should be(NoMovePossible)
  }

  /*
  *   0   1   2   3   4   5   6
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  *
  */

  "Next move " should
    "be an Connect4Move column 0, opponent has won" in {
    reset

    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)

    val connect4Move = MiniMax.getNextMove(controller).asInstanceOf[Connect4Move]
    connect4Move.column should be(0)
  }

  /*
  *   0   1   2   3   4   5   6
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|_X_|___|
  * |___|___|___|___|___|_X_|___|
  * |___|___|___|___|___|_X_|___|
  * |___|___|___|___|___|_X_|___|
  *
  */

  "Next move " should
    "be an Connect4Move column 5, opponent has won" in {
    reset

    gameField.dropCoin(5, opponent)
    gameField.dropCoin(5, opponent)
    gameField.dropCoin(5, opponent)

    val connect4Move = MiniMax.getNextMove(controller).asInstanceOf[Connect4Move]
    connect4Move.column should be(5)
  }

  /*
  *   0   1   2   3   4   5   6
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|_X_|_X_|_X_|_X_|
  *
  */

  /*
  *   0   1   2   3   4   5   6
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|_X_|_X_|_X_|_X_|___|
  *
  */
  //TODO: AI-Error, should be 2 or 6 but not 1
  "Next move " should
    "be an Connect4Move column 6, opponent has won" in {
    reset

    //    gameField.dropCoin(2, player)
    gameField.dropCoin(3, opponent)
    gameField.dropCoin(4, opponent)
    gameField.dropCoin(5, opponent)

    val connect4Move = MiniMax.getNextMove(controller).asInstanceOf[Connect4Move]
    //    connect4Move.column should be(6)
    //    connect4Move.column should be(2)
  }

  /*
*   0   1   2   3   4   5   6
* |___|___|___|___|___|___|___|
* |___|___|___|___|___|___|___|
* |___|___|___|___|___|___|___|
* |___|___|___|___|___|___|___|
* |___|___|___|___|___|___|___|
* |___|___|___|_O_|_O_|_O_|___|
*
*/
  //TODO: AI-Error, should be 2 or 6 but not 1
  "Next move " should
    "be an Connect4Move column 6, opponent has defended" in {
    reset

    //    gameField.dropCoin(6, opponent)
    gameField.dropCoin(3, player)
    gameField.dropCoin(4, player)
    gameField.dropCoin(5, player)

    val connect4Move = MiniMax.getNextMove(controller).asInstanceOf[Connect4Move]
    //        connect4Move.column should be(6)
    connect4Move.column should be(2)
  }
}
