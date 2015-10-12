package connectfour.model

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import connectfour.util.observer.IObserverWithArguments;

class Computer(controllerObserver: IObserverWithArguments, playerName: String) extends PlayerAbstract(playerName) {

		this.addObserver(controllerObserver);
    val injector = Guice.createInjector(new SolverModule);
    
    @Inject
     val solver = injector.getInstance(classOf[SolverPlugin]);

	def saveState: GameField = {
		try {
			getGameField().clone();
		} catch {
		  case e: CloneNotSupportedException => null
		}
	}

	override def update(arg: Object) {
		val gameField: GameField = arg.asInstanceOf[GameField]
		this.setGameField(gameField);
		
		if (gameField.getPlayerOnTurn().equals(this)) {
			val columnToDrop = solver.solve(this)
			this.notifyObservers(columnToDrop)
		}
	}
}

object Computer {
  val IF_WINNER_TURN_VALUE = +1000000;
  val IF_LOOSER_TURN_VALUE = -1000000;
}