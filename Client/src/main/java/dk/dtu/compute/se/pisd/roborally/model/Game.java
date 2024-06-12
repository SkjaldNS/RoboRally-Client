package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;

/**
 * Represents a game in the RoboRally game.
 */
public class Game extends Subject {

    private final int gameId;
    private final int boardId;
    private final GameStatus status;
    private final int turnId;

    public Game(int gameId, int boardId, GameStatus status, int turnId) {
        this.gameId = gameId;
        this.boardId = boardId;
        this.status = status;
        this.turnId = turnId;
    }

    public int getGameId() {
        return gameId;
    }

    public int getBoardId() {
        return boardId;
    }

    public GameStatus getStatus() {
        return status;
    }

    public int getTurnId() {
        return turnId;
    }
}
