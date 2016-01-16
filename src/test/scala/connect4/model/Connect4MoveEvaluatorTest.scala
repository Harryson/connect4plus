package connect4.model

import connect4.model.mocks.MockBase
import connectfour.controller.Connect4MoveEvaluator._
import connectfour.controller.{Connect4Move, Connect4MoveEvaluator}

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
    //    gameField.dropCoin(0, opponent)

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

    val score = Connect4MoveEvaluator.getScore(gameField, opponent)

    score should be(Int.MaxValue)
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

  //  "Connect4MoveEvaluator " should
  //    "be not more moves in column 0 possible" in {
  //    reset
  //
  //    gameField.dropCoin(0, opponent)
  //    gameField.dropCoin(0, player)
  //    gameField.dropCoin(0, opponent)
  //    gameField.dropCoin(0, player)
  //    gameField.dropCoin(0, opponent)
  //    gameField.dropCoin(0, player)
  //
  //
  //
  //    val boolean = noMovePossible(gameField, player)
  //
  //    //TODO: get move in column 0 but it should be without it
  //    //    generateListOfMoves should be(listOfMoves)
  //  }


  //  Connect4MoveEvaluator.
  //  Connect4MoveEvaluator.playerHasWon()
  //  Connect4MoveEvaluator.verticalMoveIsPossible()

}
