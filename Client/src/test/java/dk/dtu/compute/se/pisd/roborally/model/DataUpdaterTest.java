package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.roborally.controller.RestController;
import dk.dtu.compute.se.pisd.roborally.model.DataUpdater;
import dk.dtu.compute.se.pisd.roborally.model.Game;
import dk.dtu.compute.se.pisd.roborally.model.Player;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataUpdaterTest {

    private RestController mockRestController;
    private DataUpdater dataUpdater;
    private Game mockGame;
    private List<Player> mockPlayers;

    @BeforeEach
    void setUp() throws Exception {
        mockRestController = mock(RestController.class);
        mockGame = mock(Game.class);
        mockPlayers = List.of(mock(Player.class), mock(Player.class));

        when(mockRestController.getGame(anyInt())).thenReturn(mockGame);
        when(mockRestController.getPlayers(anyInt())).thenReturn(mockPlayers);

        dataUpdater = new DataUpdater(1, mockRestController);
    }
/*
    @Test
    void testUpdatePlayers() {
        List<Player> newMockPlayers = List.of(mock(Player.class), mock(Player.class), mock(Player.class));
        dataUpdater.updatePlayers(newMockPlayers);
        assertEquals(newMockPlayers, dataUpdater.getPlayers());
    }

    @Test
    void testUpdateGame() {
        Game newMockGame = mock(Game.class);
        dataUpdater.updateGame(newMockGame);
        assertEquals(newMockGame, dataUpdater.getGame());
    }
*/
    @Test
    void testPeriodicUpdates() throws Exception {
        // Allow some time for the worker thread to perform updates
        TimeUnit.SECONDS.sleep(6);

        verify(mockRestController, atLeast(1)).getPlayers(anyInt());
        verify(mockRestController, atLeast(1)).getGame(anyInt());
    }

    @AfterEach
    void tearDown() {
        dataUpdater.stopUpdater();
    }
}
