package connectfour.model

/**
 * Created by maharr on 14.10.15.
 */
class EasySolver extends SolverPlugin {
  private val difficulty = 3
  private val deepSearch = this.difficulty
  private var computer: Computer = null
  private var firstMove = true
  private var doNextColumn = 3

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

    for

  }
}
