package connectfour.model;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import connectfour.util.observer.IObserverWithArguments;

public class Computer extends PlayerAbstract {
    public static final int IF_WINNER_TURN_VALUE = +1000000;
    public static final int IF_LOOSER_TURN_VALUE = -1000000;

    @Inject
    private SolverPlugin solver;

	public Computer(final IObserverWithArguments controllerObserver, String playerName) {
		super(playerName);
		this.addObserver(controllerObserver);
        final Injector injector = Guice.createInjector(new SolverModule());
        solver = injector.getInstance(SolverPlugin.class);
	}

	public GameField saveState() {
		GameField state = null;
		try {
			state = getGameField().clone();
		} catch (CloneNotSupportedException e1) {
		}
		return state;

	}

	@Override
	public void update(final Object arg) {
		GameField gameField = (GameField) arg;
		this.setGameField(gameField);
		if (gameField.getPlayerOnTurn().equals(this)) {
			int columnToDrop = solver.solve(this);
			this.notifyObservers(columnToDrop);
		}
	}
}