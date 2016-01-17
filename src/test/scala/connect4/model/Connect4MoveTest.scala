package connect4.model

import connect4.model.mocks.MockBase
import connectfour.controller.Connect4Move

/**
 * Created by maharr on 16.01.16.
 */
class Connect4MoveTest extends MockBase {

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

  "Connect4Move " should
    "drop a coin successful in column 0" in {
    reset

    val move = new Connect4Move(controller, 0).execute

    move should be("Successful drop in column 0")
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

  "Connect4Move " should
    "drop a coin unsuccessful in column 7" in {
    reset

    val move = new Connect4Move(controller, 7).execute

    move should be("Drop in column 7 is not possible!")
  }

}
