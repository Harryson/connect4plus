package connectfour.model

import scala.beans.BeanProperty

/**
 * Created by maharr on 14.10.15.
 */

/**
 * @param saveGameName Unique savegame name, otherwise it will be overwritten!
 * @param gameField gameField to save
 * @param player1 player 1 to save
 * @param player2 player 2 to save
 */

class SaveGame(@BeanProperty val saveGameName: String, @BeanProperty val gameField: GameField,
               @BeanProperty val player1: Player, @BeanProperty val player2: Player)
