package connectfour.persistence.couchdb;

import connectfour.model.*;
import connectfour.util.observer.IObserverWithArguments;

/**
 * Created by Jakub Werner on 01/02/14.
 */
public class CouchDbUtil {

    public static PlayerCouchDb convertPlayer(Player player) {
        if(player !=null) {
            if (player instanceof Computer) {
                return new PlayerCouchDb(player.name(), true);
            } else {
                return new PlayerCouchDb(player.name(), false);
            }
        }
        return null;
    }

    public static Player convertPlayer(PlayerCouchDb player, IObserverWithArguments observerWithArguments) {
        if (player != null) {
            if (player.isComputer()) {
                return new Computer(observerWithArguments, player.getName());
            } else {
                return new Human(player.getName());
            }
        }
        return null;
    }

    public static SaveGame convertSaveGame(SaveGameCouchDb saveGameCouchDb, IObserverWithArguments controller) {
        return new SaveGame(saveGameCouchDb.getSaveGameName(), convertGameField(saveGameCouchDb.getGameFieldCouchdb(),controller),convertPlayer(saveGameCouchDb.getPlayer(), controller), convertPlayer(saveGameCouchDb.getOpponent(), controller));
    }

    public static SaveGameCouchDb convertSaveGame(SaveGame saveGame) {
        return new SaveGameCouchDb(saveGame.saveGameName(), saveGame.gameField(), saveGame.player1(), saveGame.player2());

    }

    public static GameField convertGameField(GameFieldCouchDb gameFieldCouchDb, IObserverWithArguments observerWithArguments) {
        PlayerCouchDb playerCouchDb = gameFieldCouchDb.getPlayerCouchDb();
        PlayerCouchDb opponentCouchDb = gameFieldCouchDb.getOpponentCouchDb();
        Player player = convertPlayer(gameFieldCouchDb.getPlayerCouchDb(), observerWithArguments);
        Player opponent = convertPlayer(gameFieldCouchDb.getOpponentCouchDb(), observerWithArguments);
        GameField gf = new GameField(player, opponent);
        gf.setGameField(convertGameFieldMatrix(gameFieldCouchDb.getGameFieldCouchDb(), player, opponent, playerCouchDb, opponentCouchDb));
        gf.setGameIsWon(gameFieldCouchDb.isGameWon());
        gf.setPlayerWon(convertPlayer(gameFieldCouchDb.getPlayerWon(),observerWithArguments));

        if (gameFieldCouchDb.getPlayerOnTurnCouchDb() == playerCouchDb) {
            gf.setPlayerOnTurn(player);
        } else if (gameFieldCouchDb.getPlayerOnTurnCouchDb() == opponentCouchDb) {
            gf.setPlayerOnTurn(opponent);
        }
        return gf;
    }

    public static GameFieldCouchDb convertGameField(GameField gameField) throws CloneNotSupportedException {
        return new GameFieldCouchDb(gameField.player(), gameField.opponent(), gameField.getCopyOfGamefield(), gameField.getPlayerOnTurn(), gameField.getModCount(), gameField.getWinner(), gameField.isGameWon());
    }


    public static PlayerCouchDb[][] convertGameFieldMatrix(Player[][] gameField, Player player, Player opponent, PlayerCouchDb playerCouchDb, PlayerCouchDb opponentCouchDb) {
        PlayerCouchDb[][] gameFieldCouchDb = new PlayerCouchDb[GameField$.MODULE$.DEFAULT_COLUMNS()][GameField$.MODULE$.DEFAULT_COLUMNS()];
        for (int i = 0; i < GameField$.MODULE$.DEFAULT_ROWS(); i++) {
            for (int j = 0; j < GameField$.MODULE$.DEFAULT_COLUMNS(); j++) {

                if (player.equals(gameField[i][j])) {
                    gameFieldCouchDb[i][j] = playerCouchDb;
                } else if (opponent.equals(gameField[i][j])) {
                    gameFieldCouchDb[i][j] = opponentCouchDb;
                } else if (gameField[i][j] == null) {
                    gameFieldCouchDb[i][j] = null;
                } else {
                    throw new IllegalStateException("Can't convert GameField to GameFieldCouchDb!");
                }

            }
        }
        return gameFieldCouchDb;
    }

    public static Player[][] convertGameFieldMatrix(PlayerCouchDb[][] gameField, Player player, Player opponent, PlayerCouchDb playerCouchDb, PlayerCouchDb opponentCouchDb) {
        Player[][] gf = new Player[GameField$.MODULE$.DEFAULT_ROWS()][GameField$.MODULE$.DEFAULT_COLUMNS()];
        for (int i = 0; i < GameField$.MODULE$.DEFAULT_ROWS(); i++) {
            for (int j = 0; j < GameField$.MODULE$.DEFAULT_COLUMNS(); j++) {

                if (playerCouchDb.equals(gameField[i][j])) {
                    gf[i][j] = player;
                } else if (opponentCouchDb.equals(gameField[i][j])) {
                    gf[i][j] = opponent;
                } else if (gameField[i][j] == null) {
                    gf[i][j] = null;
                } else {
                    throw new IllegalStateException("Can't convert GameFieldCouchDb to GameField!");
                }

            }
        }
        return gf;
    }
}
