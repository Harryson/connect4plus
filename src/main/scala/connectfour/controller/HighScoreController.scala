package connectfour.controller

import java.io.{InputStreamReader, BufferedReader}
import java.util.logging.Logger

import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient

/**
 * Created by maharr on 26.10.15.
 */
//TODO: Kann auch ein Singelton (object) sein.
class HighScoreController extends IHighScoreController{
  private val HIGHSCORE_URL: String = "http://localhost:9000/addHighscoreJson"
  private var client: DefaultHttpClient = null
  private val log: Logger = Logger.getLogger(classOf[HighScoreController].getName)

  override def sendHighScore(gameName: String, playerName: String, highScore: Int): Unit = {
    client = new DefaultHttpClient
    val post: HttpPost = new HttpPost(HIGHSCORE_URL)
    var input: StringEntity = null

    val s: String = String.format("{\"game\":\"%s\",\"player\":\"%s\",\"score\":\"%s\"}", gameName, playerName, highScore.toString)
    System.out.println(s)
    input = new StringEntity(s)

    input.setContentType("application/json")
    post.setEntity(input)

    val response: HttpResponse = client.execute(post)

    val br: BufferedReader = new BufferedReader(new InputStreamReader(response.getEntity.getContent))

    var output: String = null
    System.out.println("Output from Server .... \n")

    while ( {
      output = br.readLine
      output
    } != null) {
      log.info(output)
    }

    client.getConnectionManager.shutdown
  }
}
