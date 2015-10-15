package connectfour.model;

import com.google.inject.AbstractModule;
import connectfour.controller.MiddleSolver;

public class SolverModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SolverPlugin.class).to(MiddleSolver.class);
    }
}
