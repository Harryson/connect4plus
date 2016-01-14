package connectfour.events

import scala.swing.Publisher

/**
 * Created by maharr on 19.12.15.
 */
class NewGameEventScala extends Publisher {
  def newGame() {
    publish(new NewGameScalaSwingEvent)
  }
}