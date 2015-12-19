package connectfour.ui.gui.scala.swing.events

import connectfour.controller.DropCoinScalaSwingEvent

import scala.swing.Publisher

/**
 * Created by maharr on 19.12.15.
 */
class DropCoinEventScala extends Publisher{
  def dropCoin: Unit = {
    publish(new DropCoinScalaSwingEvent)
  }
}
