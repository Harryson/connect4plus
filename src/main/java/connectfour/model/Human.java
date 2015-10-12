package connectfour.model;

public class Human extends PlayerAbstract {
    private int move = 0;

    public Human(String playerName) {
        super(playerName);
    }
    
    @Override
    public int getMove() {
        return move;
    }

    @Override
    public void update(Object arg) { }
}