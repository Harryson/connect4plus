package undomanager

import scala.collection.mutable.Stack

/**
 * Created by stefano on 13.02.14.
 */
class UndoManager {
  var commands: Stack[Command] = Stack()

  def addCommand(command: Command) =
    commands = commands push command

  def undoCommand = commands.pop.execute
}