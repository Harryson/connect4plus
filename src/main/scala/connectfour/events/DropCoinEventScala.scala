package connectfour.events

import scala.swing.Publisher

/**
 * Created by maharr on 19.12.15.
 */
class DropCoinEventScala extends Publisher{
  def dropCoin() {
    publish(new DropCoinScalaSwingEvent)
  }
}