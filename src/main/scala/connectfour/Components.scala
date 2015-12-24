package connectfour

import connectfour.controller.Connect4GameControllerComponent
import connectfour.ui.gui.scala.swing.ScalaSwingGUIComponent
import connectfour.ui.tui.TUIComponent

/**
 * Created by maharr on 23.12.15.
 */
trait Components
  extends TUIComponent
  with ScalaSwingGUIComponent
  with Connect4GameControllerComponent