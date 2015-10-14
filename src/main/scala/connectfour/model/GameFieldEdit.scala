package connectfour.model

import javax.swing.undo.AbstractUndoableEdit

import connectfour.controller.IController

/**
 * Created by maharr on 12.10.15.
 */
class GameFieldEdit(controller: IController, previousState: GameField, newState: GameField, name: String) extends AbstractUndoableEdit {

  //TODO throws CannotRedoException
  override def redo() = {
    super.redo()
    this.controller.useState(newState)
  }

  //TODO throws CannotRedoException
  override def undo() = {
    super.undo()
    this.controller.useState(previousState)
  }

}
