package manager

import scala.collection.mutable

/**
 * Created by maharr on 24.12.15.
 */
//TODO
class RedoManager {
  //TODO: var and private
  var commands: mutable.Stack[() => Unit] = mutable.Stack()

  def canRedo: Boolean = {
    !commands.isEmpty
  }

  def addCommand(command: () => Unit): Unit = {
    commands = commands.push(command)
  }

  def redoCommand(undoManager: UndoManager) {
    if (canRedo) {
      val command1 = commands.pop
      undoManager.addCommand(command1)
      command1()

      if (commands.nonEmpty) {
        val command2 = commands.pop
        undoManager.addCommand(command2)
        command2()
      }
    }
  }
}