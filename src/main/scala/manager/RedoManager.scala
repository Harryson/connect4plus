package manager

import scala.collection.mutable.Stack

/**
 * Created by maharr on 24.12.15.
 */
class RedoManager {
  var commands: Stack[() => Unit] = Stack()

  def addCommand(command: () => Unit): Unit = {
    commands = commands push command
  }

  def redoCommand() {
    val command = commands.pop
    command()
  }
}