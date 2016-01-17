package connect4.model

import connect4.model.mocks.MockBase
import connectfour.controller.Connect4Move
import connectfour.controller.Connect4MoveEvaluator._

/**
 * Created by maharr on 16.01.16.
 */
class Connect4MoveEvaluatorTest extends MockBase {

  /*
  *   0   1   2   3   4   5   6
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  *
  */

  "Connect4MoveEvaluator " should
    "be generate possible moves in column 0 to 6" in {
    reset

    val listOfMoves = new Connect4Move(controller, 0) ::
      new Connect4Move(controller, 1) ::
      new Connect4Move(controller, 2) ::
      new Connect4Move(controller, 3) ::
      new Connect4Move(controller, 4) ::
      new Connect4Move(controller, 5) ::
      new Connect4Move(controller, 6) ::
      Nil

    val generateListOfMoves = generatePossibleMoves(controller, gameField, opponent)

    generateListOfMoves should be(listOfMoves)
  }

  /*
  *   0   1   2   3   4   5   6
  * |_O_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_O_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_O_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  *
  */

  "Connect4MoveEvaluator " should
    "be generate possible moves in column 1 to 6" in {
    reset

    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, player)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, player)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, player)

    val listOfMoves = new Connect4Move(controller, 1) ::
      new Connect4Move(controller, 2) ::
      new Connect4Move(controller, 3) ::
      new Connect4Move(controller, 4) ::
      new Connect4Move(controller, 5) ::
      new Connect4Move(controller, 6) ::
      Nil

    val generateListOfMoves = generatePossibleMoves(controller, gameField, opponent)

    generateListOfMoves should be(listOfMoves)
  }

  /*
  *   0   1   2   3   4   5   6
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  *
  */

  "Connect4MoveEvaluator " should
    "be generate score 0" in {
    reset

    val score = getScore(gameField, opponent)

    score should be(0)
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

  "Connect4MoveEvaluator " should
    "be generate score Int.MaxValue" in {
    reset

    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)

    val score = getScore(gameField, opponent)

    score should be(Int.MaxValue)
  }

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

  "Connect4MoveEvaluator " should
    "be not more moves in game field possible" in {
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

    val boolean = noMovePossible(gameField, opponent)

    boolean should be(true)
  }

  /*
  *   0   1   2   3   4   5   6
  * |_O_|_X_|_O_|_X_|_O_|___|_O_|
  * |_O_|_X_|_O_|_X_|_O_|_X_|_O_|
  * |_O_|_X_|_O_|_X_|_O_|_X_|_O_|
  * |_X_|_O_|_X_|_O_|_X_|_O_|_X_|
  * |_X_|_O_|_X_|_O_|_X_|_O_|_X_|
  * |_X_|_O_|_X_|_O_|_X_|_O_|_X_|
  *
  */

  "Connect4MoveEvaluator " should
    "be one more moves in game field is possible at column 5" in {
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

    // column 6
    gameField.dropCoin(6, opponent)
    gameField.dropCoin(6, opponent)
    gameField.dropCoin(6, opponent)
    gameField.dropCoin(6, player)
    gameField.dropCoin(6, player)
    gameField.dropCoin(6, player)

    val boolean = noMovePossible(gameField, opponent)

    boolean should be(false)
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

  "Connect4MoveEvaluator " should
    "be opponent has won" in {
    reset

    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)

    val won = playerHasWon(gameField, opponent)

    won should be(true)
  }

  /*
  *   0   1   2   3   4   5   6
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |___|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  *
  */

  "Connect4MoveEvaluator " should
    "be opponent hasn't won" in {
    reset

    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)

    val won = playerHasWon(gameField, opponent)

    won should be(false)
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

  "Connect4MoveEvaluator " should
    "be player hasn't won" in {
    reset

    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)

    val won = playerHasWon(gameField, player)

    won should be(false)
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

  "Connect4MoveEvaluator " should
    "be vertical move possible" in {
    reset

    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)

    //TODO was soll die Methode machen
    val possible = verticalMoveIsPossible(gameField, 0)

    possible should be(true)
  }
  //  Connect4MoveEvaluator

  /*
  *   0   1   2   3   4   5   6
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|___|___|___|___|___|___|
  * |_X_|_X_|_X_|_X_|_X_|_X_|_X_|
  *
  */

  "Connect4MoveEvaluator " should
    "be vertical move impossible" in {
    reset

    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(0, opponent)
    gameField.dropCoin(1, opponent)
    gameField.dropCoin(2, opponent)
    gameField.dropCoin(3, opponent)
    gameField.dropCoin(4, opponent)
    gameField.dropCoin(5, opponent)
    gameField.dropCoin(6, opponent)

    //TODO was soll die Methode machen
    val possible = verticalMoveIsPossible(gameField, 0)

    possible should be(true)
  }
}
