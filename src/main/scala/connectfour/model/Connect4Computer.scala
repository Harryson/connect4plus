package connectfour.model

import modelinterfaces.Player


/**
 * Created by stefano on 19.02.14.
 */
class Connect4Computer(override val name: String) extends Player {

  override def toString: String = name
}