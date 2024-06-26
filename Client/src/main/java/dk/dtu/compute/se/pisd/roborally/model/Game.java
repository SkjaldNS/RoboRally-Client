package dk.dtu.compute.se.pisd.roborally.model;


public class Game {
    private int gameId;
    private String gameName;
    private int boardId;
    private int gameStatus;
    private int turnId;
    private int numberOfPlayers;

    public Game(String gameName) {
        this.gameName = gameName;
        this.gameStatus = 0;
        this.numberOfPlayers = 0;
        this.turnId = 0;
    }

    /**
     * Get the game ID
     * @return the game ID
     */
    public int getGameID() {
        return gameId;
    }

    /**
     * Get the game name
     * @return the game name
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Set the game name
     * @param gameName the game name
     */
    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    /**
     * Set the game ID
     * @param gameID the game ID
     */
    public void setGameId(int gameID) {
        this.gameId = gameID;
    }

    /**
     * Get the board ID
     * @return the board ID
     */
    public int getBoardId() {
        return boardId;
    }

    /**
     * Set the board ID
     * @param boardID the board ID
     */
    public void setBoardId(int boardID) {
        this.boardId = boardID;
    }

    /**
     * Get the game status
     * @return the game status
     */
    public int getGameStatus() {
        return gameStatus;
    }

    /**
     * Set the game status
     * @param gameStatus the game status
     */
    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * Get the turn ID
     * @return the turn ID
     */
    public int getTurnId() {
        return turnId;
    }

    /**
     * Set the turn ID
     * @param turnID the turn ID
     */
    public void setTurnId(int turnID) {
        this.turnId = turnID;
    }

    /**
     * Get the number of players
     * @return the number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Set the number of players
     * @param numPlayers the number of players
     */
    public void setNumberOfPlayers(int numPlayers) {
        this.numberOfPlayers = numPlayers;
    }

    public boolean isGameInPreLobby() {
        return gameStatus == 0;
    }
}
