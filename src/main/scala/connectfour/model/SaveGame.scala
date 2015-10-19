package connectfour.model

import scala.beans.BeanProperty

/**
 * Created by maharr on 14.10.15.
 */
class SaveGame(@BeanProperty val saveGameName: String, @BeanProperty val gameField: GameField,
               @BeanProperty val player1: Player, @BeanProperty val player2: Player) {
}
