package manager

import scala.collection.mutable

/**
 * Created by stefano on 13.02.14.
 */
class UndoManager {
  //TODO: var and private
  var commands: mutable.Stack[() => Unit] = mutable.Stack()

  def canUndo: Boolean = {
    commands.isEmpty
  }

  def addCommand(command: () => Unit) {
    commands = commands.push(command)
  }

  def undoCommand(redoManager: RedoManager) {
    val command1 = commands.pop
    redoManager.addCommand(command1)
    command1()

    if (commands.nonEmpty) {
      var command2 = commands.pop
      redoManager.addCommand(command2)
      command2()
    }
  }

  def undoCommand() {
    val command = commands.pop
    command()
  }
}