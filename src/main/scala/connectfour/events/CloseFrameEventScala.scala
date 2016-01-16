package connectfour.events

import scala.swing.Publisher

/**
 * Created by maharr on 16.01.16.
 */
class CloseFrameEventScala extends Publisher {
  def close() {
    publish(new CloseFrameScalaSwingEvent)
  }
}
