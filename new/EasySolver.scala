package connectfour.model

/**
 * Created by maharr on 14.10.15.
 */
class EasySolver extends SolverPlugin {
  private var doNextColumn = 3
  //TODO warum macht man das im Konstuktor bei Java???
  private val deepSearch = this.difficulty
  private var firstMove = true
  private val difficulty = 3
  private var computer: Computer = null

  override def solve(computer: Computer): Int = {
    this.computer = computer

    if (this.firstMove && this.computer.getGameField.isEmpty)
      this.firstMove = false
    else
      maxValue(this.difficulty)

    this.doNextColumn
  }

  def maxValue(restDeep: Int) = {
    val computed = -Int.MaxValue

    var i = 0
    while (i < GameField.DEFAULT_COLUMNS) {
      val previousState = computer.saveState

      if (computer.getGameField.getPlayerOnTurn != this)
        computer.getGameField.changePlayerTurn
      else {
        computer.setGameField(previousState)

      }

      i += 1; i - 1
    }


  }
}
