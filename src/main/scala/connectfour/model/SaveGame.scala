package connectfour.model

<<<<<<< HEAD
/**
 * @param saveGameName Unique savegame name, otherwise it will be overwritten!
 * @param gameField gameField to save
 * @param player1 player 1 to save
 * @param player2 player 2 to save
 */
class SaveGame(val saveGameName: String, val gameField: GameField, val player1: Player, val player2: Player)
=======
import scala.beans.BeanProperty

/**
 * Created by maharr on 14.10.15.
 */
class SaveGame(@BeanProperty val saveGameName: String, @BeanProperty val gameField: GameField,
               @BeanProperty val player1: Player, @BeanProperty val player2: Player) {
}
>>>>>>> 4e5c8a9d74a4b592e8988980d25742fb7721db43
