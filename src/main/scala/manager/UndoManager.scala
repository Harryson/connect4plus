package manager

import scala.collection.mutable.Stack

/**
 * Created by stefano on 13.02.14.
 */
class UndoManager {
  var commands: Stack[() => Unit] = Stack()

  def addCommand(command: () => Unit) {
    commands = commands push command
  }

  def undoCommand() {
    val command = commands.pop
    command()
  }
}