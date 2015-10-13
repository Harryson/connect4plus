package connectfour.model

/**
 * @param saveGameName Unique savegame name, otherwise it will be overwritten!
 * @param gameField gameField to save
 * @param player1 player 1 to save
 * @param player2 player 2 to save
 */
class SaveGame(val saveGameName: String, val gameField: GameField, val player1: Player, val player2: Player)