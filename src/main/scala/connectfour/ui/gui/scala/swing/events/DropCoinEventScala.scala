package connectfour.ui.gui.scala.swing.events


import controller.DropCoinScalaSwingEvent

import scala.swing.Publisher

/**
 * Created by maharr on 19.12.15.
 */
class DropCoinEventScala extends Publisher{
  def dropCoin {
    publish(new DropCoinScalaSwingEvent)
  }
}
