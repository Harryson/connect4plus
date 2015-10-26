package connectfour.controller

/**
 * Created by maharr on 19.10.15.
 */
trait IHighScoreController {
  def sendHighScore(sendHighScore: String, playerName: String, highScore: Int)
}
