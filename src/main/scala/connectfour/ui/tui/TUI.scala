package connectfour.ui.tui

import java.util.Scanner
import com.google.inject.Inject
import connectfour.controller.IController
import connectfour.ui.UI
import connectfour.util.observer.IObserver
import modelinterfaces.Player
import org.junit.runner.Computer
import connectfour.model.GameField
import controller.GameController

/**
 * Created by maharr on 01.11.15.
 */
class TUI(controller: GameController) extends UI with IObserver{
  private val newline: String = System.getProperty("line.separator")
  private var (player1, player2) = controller.getPlayers


  override def drawGameField {
    //(player1, player2) = controller.getPlayers
    
    val winner = controller.getWinner 
    if (winner != "") {
      System.out.printf("%s hat gewonnen\n\n", winner)
      return
    }
    else {
      System.out.printf("%s ist dran\n\n", controller.getPlayerOnTurn)
    }
    System.out.printf(this.renderGameField + "\n")
    if (controller.getPlayerOnTurn.isInstanceOf[Computer]) {
      return
    }
    val scanner: Scanner = new Scanner(System.in)
    System.out.print("Eingabe: ")
    val userInput: String = scanner.next
    System.out.println("\n\n")
    scanner.close
    val exit: String = "quit"
    if (userInput == exit) {
      System.exit(0)
    }
    this.parseUserInput(userInput)
  }

  private def parseUserInput(userInput: String) {
    if (userInput.isInstanceOf[Int]) {
      val column: Int = userInput.toInt - 1
      if (!controller.dropCoin(column)) {
        System.out.println("Ungueltige Eingabe!\n")
        this.drawGameField
      }
    }
    else {
      System.out.println("Ungueltige Eingabe!\n")
      this.drawGameField
    }
  }

  def renderGameField: String = {
    val row: Int = GameField.DEFAULT_ROWS - 1
    val col: Int = GameField.DEFAULT_COLUMNS
    val playingField: StringBuilder = new StringBuilder
    val begin: String = "|"
    val empty: String = "_"
    val end: String = "_|"
    val coin1: String = "O"
    val coin2: String = "X"
    System.out.println("  1   2   3   4   5   6   7")
    var currentRow: Int = row
    while (currentRow >= 0) {
      playingField.append(begin)
      var currentColumn: Int = 0
      while (currentColumn < col) {
        playingField.append(empty)
        val player: Player = this.controller.getPlayerAt(currentRow, currentColumn)
        if (player == null) {
          playingField.append(empty)
        }
        else if (player == player1) {
          playingField.append(coin1)
        }
        else if (player == player2) {
          playingField.append(coin2)
        }
        else {
          playingField.append("FEHLER!")
        }
        playingField.append(end)
        currentColumn += 1
      }
      playingField.append(newline)
      currentRow -= 1; currentRow + 1
    }
    return playingField.toString
  }

  override def update {
    System.out.println(this.renderGameField)
  }
}
