package connectfour.model

/**
 * Created by maharr on 14.10.15.
 */
trait SolverPlugin {
  def solve(computer: Computer): Int
}
