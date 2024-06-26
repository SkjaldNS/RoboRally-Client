package dk.dtu.compute.se.pisd.roborally.model;

/**
 * The GameSession class represents a session of the game.
 * It contains the game ID, player ID, map ID, and a flag indicating whether the player is an admin.
 */
public class GameSession {
    private int gameId;
    private int playerId;
    private int mapId;
    private boolean isAdmin;

    /**
     * Constructs a new GameSession with the given game ID, player ID, map ID, and admin flag.
     *
     * @param gameId the game ID
     * @param playerId the player ID
     * @param mapId the map ID
     * @param isAdmin the admin flag
     */
    public GameSession(int gameId, int playerId, int mapId, boolean isAdmin) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.mapId = mapId;
        this.isAdmin = isAdmin;
    }

    /**
     * Constructs a new GameSession with the given game ID, player ID, and admin flag.
     * The map ID is set to 0.
     *
     * @param gameId the game ID
     * @param playerId the player ID
     * @param isAdmin the admin flag
     */
    public GameSession(int gameId, int playerId, boolean isAdmin) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.mapId = 0;
        this.isAdmin = isAdmin;
    }

    /**
     * @return the game ID
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Sets the game ID.
     * @param gameId the game ID
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the player ID
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Sets the player ID.
     * @param playerId the player ID
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the map ID
     */
    public int getMapId() {
        return mapId;
    }

    /**
     * Sets the map ID.
     * @param mapId the map ID
     */
    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    /**
     * @return the admin flag
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Sets the admin flag.
     * @param admin the admin flag
     */
    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
