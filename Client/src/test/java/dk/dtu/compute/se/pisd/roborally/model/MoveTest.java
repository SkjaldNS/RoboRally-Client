package dk.dtu.compute.se.pisd.roborally.model;

import static org.junit.Assert.*;

import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.Move;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MoveTest {

    private Move move;
    private Command mockCommand;

    @Before
    public void setUp() {
        move = new Move();
        mockCommand = Mockito.mock(Command.class);
    }

    @Test
    public void testSetAndGetReg1() {
        move.setReg1(mockCommand);
        assertEquals(mockCommand, move.getReg1());
    }

    @Test
    public void testSetAndGetReg2() {
        move.setReg2(mockCommand);
        assertEquals(mockCommand, move.getReg2());
    }

    @Test
    public void testSetAndGetReg3() {
        move.setReg3(mockCommand);
        assertEquals(mockCommand, move.getReg3());
    }

    @Test
    public void testSetAndGetReg4() {
        move.setReg4(mockCommand);
        assertEquals(mockCommand, move.getReg4());
    }

    @Test
    public void testSetAndGetReg5() {
        move.setReg5(mockCommand);
        assertEquals(mockCommand, move.getReg5());
    }

    @Test
    public void testSetAndGetGameID() {
        int gameID = 123;
        move.setGameID(gameID);
        assertEquals(gameID, move.getGameID());
    }

    @Test
    public void testSetAndGetPlayerID() {
        int playerID = 456;
        move.setPlayerID(playerID);
        assertEquals(playerID, move.getPlayerID());
    }

    @Test
    public void testSetAndGetTurnID() {
        int turnID = 789;
        move.setTurnID(turnID);
        assertEquals(turnID, move.getTurnID());
    }

    @Test
    public void testIsChoice() {
        Mockito.when(mockCommand.isInteractive()).thenReturn(true);
        assertTrue(move.isChoice(mockCommand));

        Mockito.when(mockCommand.isInteractive()).thenReturn(false);
        assertFalse(move.isChoice(mockCommand));
    }

    @Test
    public void testSetChoice() {


    }
}