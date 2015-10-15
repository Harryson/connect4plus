package connectfour.model;

import com.google.inject.AbstractModule;

public class SolverModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(SolverPlugin.class).to(MiddleSolver.class);
    }
}
