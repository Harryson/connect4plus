package manager

import scala.collection.mutable

/**
 * Created by stefano on 13.02.14.
 */
class UndoManager {
  //TODO: var and private
  var commands: mutable.Stack[() => Unit] = mutable.Stack()

  def canUndo: Boolean = {
    !commands.isEmpty
  }

  def addCommand(command: () => Unit) {
    commands = commands.push(command)
  }

  def undoCommand(redoManager: RedoManager) {
    if (canUndo) {
      val command1 = commands.pop
      command1()
      redoManager.addCommand(command1)

      if (commands.nonEmpty) {
        var command2 = commands.pop
        redoManager.addCommand(command2)
        command2()
      }
    }
  }

  def undoCommand() {
    val command = commands.pop
    command()
  }
}