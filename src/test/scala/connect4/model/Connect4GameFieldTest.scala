package connect4.model

import connect4.model.mocks.MockBase
import connectfour.controller.Connect4GameField
import connectfour.model.{Connect4Computer, Connect4Player}

/**
 * Created by maharr on 16.01.16.
 */
class Connect4GameFieldTest extends MockBase {

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

  "GameField " should
    "change player successful" in {
    reset

    val newGameField = new Connect4GameField(new Connect4Player("Human"), new Connect4Computer("Computer"))
    val currentPlayer = newGameField.getPlayerOnTurn

    if (currentPlayer.name == "Human") {
      val newPlayer = newGameField.changePlayerOnTurn
      newPlayer.name == "Computer" should be(true)
    }

    if (currentPlayer.name == "Computer") {
      val newPlayer = newGameField.changePlayerOnTurn
      newPlayer.name == "Human" should be(true)
    }

    if (currentPlayer.name == "Human") {
      val newPlayer = newGameField.changePlayerOnTurn
      newPlayer.name == "Human" should be(false)
    }
/*
    if (currentPlayer.name == "Computer") {
      val newPlayer = newGameField.changePlayerOnTurn
      newPlayer.name == "Computer" should be(false)
    }*/
  }

  //TODO
  "GameField " should
    "clone successful" in {
    reset

    //    val newGamefield = new Connect4GameField(new Connect4Player("Human"), new Connect4Computer("Computer"))
    //    newGamefield.dropCoin(1)
    //
    //    val anotherGamefield = new Connect4GameField(new Connect4Player("Human"), new Connect4Computer("Computer"))
    //
    //    val clone = newGamefield.cloneGameField()
    //
    //    clone == newGamefield should be(true)
    //    clone equals newGamefield should be(true)
    //    clone eq newGamefield should be(false)
    //    clone == anotherGamefield should be (false)
  }





  //  newGamefield.dropCoin(0)
  //  newGamefield.getPlayerOnTurn
  //  newGamefield.getWinner
  //  newGamefield.gameField

}
