package connectfour.model;

import connectfour.util.observer.IObserverWithArguments;

public interface Player extends IObserverWithArguments {

    void setName(String string);
    
    String getName();
    
    int getMove();

    public GameField getGameField();
    
    public void setGameField(final GameField gameField);
}