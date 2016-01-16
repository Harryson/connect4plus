package connect4.model

import connect4.model.mocks.MockBase
import connectfour.controller.Drop

/**
 * Created by maharr on 16.01.16.
 */
class DropTest extends MockBase {
  implicit def sentenceToInt(str: String) = new Drop(controller, str)

  "Drop " should
    "be successful at column = 2" in {
    reset
    val column: Int = 2;
    val sentence = "Drop coin at " + column

    sentence.dropCoin should be("Successful drop in column 2")
  }

  "Drop " should
    "be failed at column = 8" in {
    reset
    val column: Int = 8;
    val sentence = "Drop coin at " + column

    sentence.dropCoin should be("Drop in column 8 is not possible!")
  }
}
