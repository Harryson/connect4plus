package connectfour.model

import java.awt.Color

class Human (playerName: String) extends PlayerAbstract(playerName) {
    override var color = Color.RED

    override def update(arg: Any) {}
}