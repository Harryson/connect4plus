package connectfour.ui.gui.java.swing.controller

import connectfour.ui.gui.java.swing.widgets.ArrowCell
import connectfour.util.observer.IObserverWithArguments
import scala.collection.mutable.ArrayBuffer


object ArrowManager extends IObserverWithArguments {
   private var currentColumn = 0
   var arrowCells : ArrayBuffer[ArrowCell] = _
   
   def markColumnWhereMouseHasEntered(col: Int) {
        if (col != currentColumn) {
            // Hide arrow in old column
            arrowCells(currentColumn).showArrow(false)
            arrowCells(currentColumn).repaint()
            
            // Show arrow in new column
            arrowCells(col).showArrow(true)
            arrowCells(col).repaint()
            
            this.currentColumn = col
        }
    }
    
    override def update(arg: Any) {
        val column = arg.asInstanceOf[Int]
        markColumnWhereMouseHasEntered(column)
    }
}