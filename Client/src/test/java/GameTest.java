

import dk.dtu.compute.se.pisd.roborally.model.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the Game class.
 */
public class GameTest {

    @Test
    public void testGameId() {
        Game game = new Game();
        int gameId = 123;
        game.setGameId(gameId);
        assertEquals(gameId, game.getGameID());
    }

    @Test
    public void testGameName() {
        Game game = new Game();
        String gameName = "RoboRally";
        game.setGameName(gameName);
        assertEquals(gameName, game.getGameName());
    }

    @Test
    public void testBoardId() {
        Game game = new Game();
        int boardId = 456;
        game.setBoardId(boardId);
        assertEquals(boardId, game.getBoardId());
    }

    @Test
    public void testGameStatus() {
        Game game = new Game();
        int gameStatus = 1; // Example status
        game.setGameStatus(gameStatus);
        assertEquals(gameStatus, game.getGameStatus());
    }

    @Test
    public void testTurnId() {
        Game game = new Game();
        int turnId = 789;
        game.setTurnId(turnId);
        assertEquals(turnId, game.getTurnId());
    }

    @Test
    public void testNumPlayers() {
        Game game = new Game();
        int numPlayers = 4;
        game.setNumPlayers(numPlayers);
        assertEquals(numPlayers, game.getNumPlayers());
    }
}
