package connect4.model

import connectfour.model.Connect4Player
import org.scalatest._

/**
 * Created by maharr on 14.01.16.
 */
class Connect4PlayerTest extends FlatSpec with Matchers {

  val player: Connect4Player = new Connect4Player("Hugo");

  "Player's name " should "should be 'Hugo'" in {
    player.toString should be("Hugo")
  }

  // TODO Seec
  //  "A" sould {
  //    val bool = true
  //
  //    "have" in {
  //      bool must b_ ==(true)
  //    }
  //  }
}
