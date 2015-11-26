package connectfour.model

import ai.MiniMax
import connectfour.util.observer.IObserver
import connectfour.util.observer.Observable
import controller.GameController
import modelinterfaces.Player

/**
 * Created by stefano on 19.02.14.
 */
class Connect4Computer(override val name: String, controller: GameController, observable: Observable) extends IObserver with Player {
  observable.addObserver(this)

  private def draw = {
    if (controller.getPlayerOnTurn == this)
      MiniMax.getNextMove(controller).execute
  }

  override def update = draw

  override def toString: String = name
}