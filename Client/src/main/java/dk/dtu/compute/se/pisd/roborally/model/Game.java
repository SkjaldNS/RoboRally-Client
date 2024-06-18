package dk.dtu.compute.se.pisd.roborally.model;

import com.google.gson.annotations.Expose;

public class Game {
    @Expose
    private int gameId;
    @Expose
    private String gameName;
    @Expose
    private int boardId;
    @Expose
    private int gameStatus;
    @Expose
    private int turnId;
    @Expose
    private int numPlayers;

    public Game(String gameName) {
        this.gameName = gameName;
        this.gameStatus = 0;
        this.numPlayers = 0;
        this.turnId = 0;
    }

    public int getGameID() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setGameId(int gameID) {
        this.gameId = gameID;
    }

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardID) {
        this.boardId = boardID;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getTurnId() {
        return turnId;
    }

    public void setTurnId(int turnID) {
        this.turnId = turnID;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }
}
