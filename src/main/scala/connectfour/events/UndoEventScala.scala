package connectfour.events

import scala.swing.Publisher

/**
 * Created by maharr on 24.12.15.
 */
class UndoEventScala extends Publisher {
  def undo() {
    publish(new UndoScalaSwingEvent)
  }
}