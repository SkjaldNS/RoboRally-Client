package dk.dtu.compute.se.pisd.roborally.model;

public class GameSession {
    private int gameId;
    private int playerId;
    private int mapId;
    private boolean isAdmin;

    public GameSession(int gameId, int playerId, int mapId, boolean isAdmin) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.mapId = mapId;
        this.isAdmin = isAdmin;
    }

    public GameSession(int gameId, int playerId, boolean isAdmin) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.mapId = 0;
        this.isAdmin = isAdmin;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
