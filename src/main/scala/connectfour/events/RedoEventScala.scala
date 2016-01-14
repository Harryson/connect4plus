package connectfour.events

import scala.swing.Publisher

/**
 * Created by maharr on 24.12.15.
 */
class RedoEventScala extends Publisher {
  def redo() {
    publish(new RedoScalaSwingEvent)
  }
}