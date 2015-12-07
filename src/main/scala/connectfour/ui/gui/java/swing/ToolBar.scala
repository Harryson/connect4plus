package connectfour.ui.gui.java.swing

import connectfour.ui.gui.java.swing.events.{RedoEvent, UndoEvent, NewGameEvent}
import connectfour.util.observer.IObserver
import javax.swing._
import java.awt._
import connectfour.controller.Connect4GameController

//TODO: Für was braucht man das Frame?
class ToolBar(observer: IObserver, frame: Frame) extends JPanel {
  private val toolBar = new JToolBar("Toolbar")

  setBackground(Color.WHITE)
  toolBar.setBackground(Color.WHITE)
  add(toolBar)

  addButtons

  def addButtons() {
    val controller = Connect4GameController.getCurrentInstance
    
    val newGame = "new game"
    var button = makeNavigationButton(newGame, "New Game", "New Game")
    button.addMouseListener(new NewGameEvent(controller, observer))
    toolBar.add(button)

    val undo = "previous"
    button = makeNavigationButton(undo, "Undo", "Undo")
    button.addMouseListener(new UndoEvent(observer))
    toolBar.add(button)

    val redo = "next"
    button = makeNavigationButton(redo, "Redo", "Redo")
    button.addMouseListener(new RedoEvent(observer))
    toolBar.add(button)
  }

  private def makeNavigationButton(actionCommand: String, toolTipText: String, altText: String) = {
    // Create and initialize the button.
    val button = new JButton
    button.setBackground(Color.WHITE)
    button.setActionCommand(actionCommand)
    button.setToolTipText(toolTipText)
    button.setText(altText)

    button
  }
}