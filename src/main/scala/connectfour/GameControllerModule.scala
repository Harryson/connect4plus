package connectfour

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import connectfour.controller.{HighScoreController, IHighScoreController, GameController, IController}
import connectfour.model.{MiddleSolver, SolverPlugin}
import connectfour.util.observer.IObserverWithArguments

/**
 * Created by maharr on 19.10.15.
 */
class GameControllerModule extends AbstractModule{
  @Override protected def configure {
    bind(classOf[IController]).to(classOf[GameController])
    bind(classOf[IObserverWithArguments]).to(classOf[GameController])
    bind(classOf[IHighScoreController]).to(classOf[HighScoreController])
    bind(classOf[SolverPlugin]).to(classOf[MiddleSolver])
  }
}
