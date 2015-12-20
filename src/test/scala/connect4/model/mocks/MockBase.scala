package connect4.model.mocks

import modelinterfaces.Player
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by stefano on 20.12.15.
  */
class MockBase extends FlatSpec with Matchers {
  protected var controller: MockController = _
  protected var gameField: MockGameField = _
  protected var player: Player = _
  protected var opponent: Player = _

  def reset {
    controller = new MockController("Hugo", "Boss")
    gameField = controller.getGameField
    player = controller.player1
    opponent = controller.player2
  }
}
