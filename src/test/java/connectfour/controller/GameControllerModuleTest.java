package connectfour.controller;

import com.google.inject.AbstractModule;
import connectfour.model.MiddleSolver;
import connectfour.model.SolverPlugin;
import connectfour.util.observer.IObserverWithArguments;

public class GameControllerModuleTest extends AbstractModule {

    @Override
    protected void configure() {
        bind(IController.class).to(GameController.class);
        bind(IObserverWithArguments.class).to(GameController.class);
        bind(IHighScoreController.class).to(HighScoreController.class);
        bind(SolverPlugin.class).to(MiddleSolver.class);
    }
}
