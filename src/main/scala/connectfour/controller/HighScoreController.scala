package connectfour.controller

import java.io.{ InputStreamReader, BufferedReader }
import java.util.logging.Logger
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import java.io.UnsupportedEncodingException
import org.apache.http.client.ClientProtocolException
import java.io.IOException

/**
 * Created by maharr on 26.10.15.
 */
//TODO: Kann auch ein Singelton (object) sein.
class HighScoreController extends IHighScoreController {

  private val HIGHSCORE_URL = "http://localhost:9000/addHighscoreJson"
  private var client: DefaultHttpClient = null
  private val log = Logger.getLogger(classOf[HighScoreController].getName)

  override def sendHighScore(gameName: String, playerName: String, highScore: Int): Unit = {
    client = new DefaultHttpClient
    val post: HttpPost = new HttpPost(HIGHSCORE_URL)
    var input: StringEntity = null

    val s = String.format("{\"game\":\"%s\",\"player\":\"%s\",\"score\":\"%s\"}", gameName, playerName, highScore.toString)
    System.out.println(s)
    input = new StringEntity(s)

    input.setContentType("application/json")
    post.setEntity(input)
    try {
      val response: HttpResponse = client.execute(post)

      val br = new BufferedReader(new InputStreamReader(response.getEntity.getContent))

      var output: String = null
      System.out.println("Output from Server .... \n")

      while ({
        output = br.readLine
        output
      } != null) {
        log.info(output)
      }
    } catch {
      case e: UnsupportedEncodingException => log.info(e.getMessage());
      case e: ClientProtocolException => log.info(e.getMessage());
      case e: IOException => log.info(e.getMessage());
    } finally {
      client.getConnectionManager.shutdown
    }
  }
}
