package connectfour.model

import ai.MiniMax
import connectfour.controller._
import connectfour.events.{NewGameScalaSwingEvent, DropCoinScalaSwingEvent}
import modelinterfaces.Player

import scala.swing.Reactor


/**
 * Created by stefano on 19.02.14.
 */
class Connect4Computer(override val name: String) extends Player {

  override def toString: String = name
}