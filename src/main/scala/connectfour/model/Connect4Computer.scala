package connectfour.model

import controller.GameController
import ai.MinMax
import observer.{Observable, Observer}
import modelinterfaces.Player

/**
 * Created by stefano on 19.02.14.
 */
class Connect4Computer(override val name: String, controller: GameController, observable: Observable) extends Observer with Player {
  observable.addObserver(this)

  private def draw = {
    if (controller.getPlayerOnTurn == this)
      MinMax.getNextMove(controller).execute
  }

  override def update = draw

  override def toString: String = name
}