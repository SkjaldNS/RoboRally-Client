package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.roborally.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * @author Asma Maryam, s230716@dtu.dk
 */
class PlayerTest {

    private Player player;
    private Deck.Board mockBoard;
    private Space mockSpace;

    @BeforeEach
    void setUp() {
        mockBoard = mock(Deck.Board.class);
        mockSpace = mock(Space.class);
        player = new Player(mockBoard, 1, "TestPlayer");
    }

    @Test
    void testPlayerInitialization() {
        assertEquals("TestPlayer", player.getName());
        assertEquals(1, player.getRobotId());
        assertEquals(Heading.SOUTH, player.getHeading());
        assertNull(player.getSpace());
        assertEquals(0, player.getPowerUpCnt());
        assertEquals(0, player.getCheckpointCollected());
        assertNotNull(player.getProgram());
        assertEquals(Player.NO_REGISTERS, player.getProgram().length);
    }

    @Test
    void testSetAndGetSpace() {

    }

    @Test
    void testSetAndGetHeading() {
        player.setHeading(Heading.NORTH);
        assertEquals(Heading.NORTH, player.getHeading());

        player.setHeading(Heading.EAST);
        assertEquals(Heading.EAST, player.getHeading());
    }

    @Test
    void testPowerUpCnt() {
        assertEquals(0, player.getPowerUpCnt());

        player.oneUpPowerUpCnt();
        assertEquals(1, player.getPowerUpCnt());

        player.oneUpPowerUpCnt();
        assertEquals(2, player.getPowerUpCnt());
    }

    @Test
    void testSetAndGetLastCommand() {
        Command mockCommand = mock(Command.class);
        player.setLastCommand(mockCommand);
        assertEquals(mockCommand, player.getLastCommand());

        player.setLastCommand(Command.AGAIN);
        assertNotEquals(Command.AGAIN, player.getLastCommand());
    }

    @Test
    void testSetAndGetCheckpointCollected() {
        assertEquals(0, player.getCheckpointCollected());

        player.setCheckpoint(1);
        assertEquals(1, player.getCheckpointCollected());
    }

    @Test
    void testSetAndGetCurrentCommand() {
        Command mockCommand = mock(Command.class);
        player.setCurrentCommand(mockCommand);
        assertEquals(mockCommand, player.getCurrentCommand());
    }
}
