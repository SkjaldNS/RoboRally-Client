package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.roborally.model.GameSession;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the GameSession class.
 */
public class GameSessionTest {

    @Test
    public void testConstructor() {
        int gameId = 101;
        int playerId = 202;
        GameSession gameSession = new GameSession(gameId, playerId);

        assertEquals(gameId, gameSession.getGameId());
        assertEquals(playerId, gameSession.getPlayerId());
    }

    @Test
    public void testGetGameId() {
        int gameId = 101;
        GameSession gameSession = new GameSession(gameId, 202);

        assertEquals(gameId, gameSession.getGameId());
    }

    @Test
    public void testSetGameId() {
        int gameId = 101;
        GameSession gameSession = new GameSession(gameId, 202);
        int newGameId = 303;

        gameSession.setGameId(newGameId);
        assertEquals(newGameId, gameSession.getGameId());
    }

    @Test
    public void testGetPlayerId() {
        int playerId = 202;
        GameSession gameSession = new GameSession(101, playerId);

        assertEquals(playerId, gameSession.getPlayerId());
    }

    @Test
    public void testSetPlayerId() {
        int playerId = 202;
        GameSession gameSession = new GameSession(101, playerId);
        int newPlayerId = 404;

        gameSession.setPlayerId(newPlayerId);
        assertEquals(newPlayerId, gameSession.getPlayerId());
    }
}

