package manager

import scala.collection.mutable

/**
 * Created by stefano on 13.02.14.
 */
class UndoManager {
  var commands: mutable.Stack[() => Unit] = mutable.Stack()

  def addCommand(command: () => Unit) {
    commands = commands push command
  }

  def undoCommand() {
    val command = commands.pop
    command()
  }
}