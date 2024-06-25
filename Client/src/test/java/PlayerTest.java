import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import dk.dtu.compute.se.pisd.roborally.model.*;

class PlayerTest {

    private Board board;
    private Player player;

    @BeforeEach
    void setUp() {
        board = new Board(10, 10);
        player = new Player(board, "Alice", true);
        player.setPlayerID(1);
        player.setRobotId(1);
        player.initPlayer();
        Move move = new Move();
        move.setReg1(Command.AGAIN);
        move.setReg2(Command.FORWARD);
        move.setReg3(Command.FAST_FORWARD);
        move.setReg4(Command.OPTION_LEFT_RIGHT);
        move.setReg5(Command.U_TURN);
        player.setProgramField(move);
    }
    /**
     * @author Asma Maryam, s230716@dtu.dk
     * @author Turan Talayhan, s224746@student.dtu.dk
     */
    @Test
    void testConstructorInitialization() {
        assertEquals("Alice", player.getName(), "Player name should be initialized to Alice.");
        assertEquals(1, player.getRobotId(), "Player robot ID should be initialized to 1.");
        assertNull(player.getSpace(), "Player space should initially be null.");
        assertEquals(Heading.SOUTH, player.getHeading(), "Player heading should initially be SOUTH.");
        assertNotNull(player.getProgram(), "Program should not be null.");
    }
    /**
     * @author Asma Maryam, s230716@dtu.dk
     * @author Turan Talayhan, s224746@student.dtu.dk
     */
    @Test
    void testSpaceManagement() {
        Space space = new Space(board, 5, 5);
        player.setSpace(space);
        assertEquals(space, player.getSpace(), "Player space should be set to the provided space.");
        assertEquals(player, space.getPlayer(), "Space should reference back to the player.");
    }
    /**
     * @author Asma Maryam, s230716@dtu.dk
     * @author Turan Talayhan, s224746@student.dtu.dk
     */
    @Test
    void testHeadingManagement() {
        player.setHeading(Heading.NORTH);
        assertEquals(Heading.NORTH, player.getHeading(), "Player heading should be updated to NORTH.");
    }

    @Test
    void setProgramField() {
        Player player = new Player(board, "Alice", true);
        player.initPlayer();
        Move move = new Move();
        move.setReg1(Command.AGAIN);
        move.setReg2(Command.FORWARD);
        move.setReg3(Command.FAST_FORWARD);
        move.setReg4(Command.OPTION_LEFT_RIGHT);
        move.setReg5(Command.U_TURN);

        player.setProgramField(move);

        assertEquals(Command.AGAIN, player.getProgramField(0).getCard().command, "Program field 0 should be set to AGAIN.");
        assertEquals(Command.FORWARD, player.getProgramField(1).getCard().command, "Program field 1 should be set to FORWARD.");
        assertEquals(Command.FAST_FORWARD, player.getProgramField(2).getCard().command, "Program field 2 should be set to FAST_FORWARD.");
        assertEquals(Command.OPTION_LEFT_RIGHT, player.getProgramField(3).getCard().command, "Program field 3 should be set to OPTION_LEFT_RIGHT.");
        assertEquals(Command.U_TURN, player.getProgramField(4).getCard().command, "Program field 4 should be set to U_TURN.");
    }
}