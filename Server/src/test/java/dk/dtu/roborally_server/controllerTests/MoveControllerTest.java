package dk.dtu.roborally_server.controllerTests;

import dk.dtu.roborally_server.controller.MoveController;
import dk.dtu.roborally_server.model.Move;
import dk.dtu.roborally_server.repository.MoveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This class contains unit tests for the MoveController class.
 * It tests the functionality of getting and creating moves for a specific game and turn.
 * Each test case is designed to test a specific functionality of the MoveController.
 *
 * The class uses the MockMvc class from the Spring Test framework to perform HTTP requests to the MoveController.
 * The responses from the MoveController are then checked for correctness.
 *
 * The MoveRepository is mocked to isolate the tests from the database.
 * This allows the tests to be run without any existing database setup and ensures that the tests do not affect the database.
 *
 * The setup method is run before each test to setup the test environment.
 * It initializes the MoveController and MockMvc objects and resets the MoveRepository mock.
 */
public class MoveControllerTest {
    private MoveController moveController;
    private MoveRepository moveRepository;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        moveRepository = mock(MoveRepository.class);
        moveController = new MoveController(moveRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(moveController).build();
    }
    /**
     * This test checks the functionality of getting moves for a specific game and turn.
     * It performs a GET request to the /games/{gameId}/moves/{turnId} endpoint and checks that the status is OK.
     *
     * The MoveRepository is mocked to return a list of moves when the findMoveByGameIdAndTurnId method is called.
     * This allows the test to check that the MoveController correctly calls the MoveRepository and handles the returned moves.
     */
    @Test
    public void testGetMoves() throws Exception {
        // Create a list of moves
        List<Move> moves = new ArrayList<>();
        moves.add(new Move());
        moves.add(new Move());

        // Mock the findMoveByGameIdAndTurnId method to return the list of moves
        when(moveRepository.findMoveByGameIdAndTurnId(anyLong(), anyLong())).thenReturn(moves);

        // Perform a GET request to the getMoves method
        mockMvc.perform(get("/games/1/moves/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))); // Expect the list to have 2 moves

        // Verify that the findMoveByGameIdAndTurnId method was called once
        verify(moveRepository, times(1)).findMoveByGameIdAndTurnId(anyLong(), anyLong());
    }
    /**
     * This test checks the functionality of creating a move for a specific game, turn and player.
     * It performs a POST request to the /games/{gameId}/moves endpoint with a JSON body containing the move data and checks that the status is OK.
     *
     * The MoveRepository is mocked to return null when the findMoveByGameIdAndTurnIdAndPlayerId method is called, indicating that the move does not exist.
     * This allows the test to check that the MoveController correctly calls the MoveRepository and handles the case where the move does not exist.
     */
    @Test
    public void testCreateChoice() throws Exception {
        // Create a new move object
        Move move = new Move();
        move.setGameId(1L);
        move.setTurnId(1L);
        move.setPlayerId(1L);
        move.setReg1(Move.Command.FORWARD);
        move.setReg2(Move.Command.FORWARD);
        move.setReg3(Move.Command.FORWARD);
        move.setReg4(Move.Command.FORWARD);
        move.setReg5(Move.Command.FORWARD);

        // Mock the findMoveByGameIdAndTurnIdAndPlayerId method to return null, indicating that the move does not exist
        when(moveRepository.findMoveByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong())).thenReturn(null);

        // Mock the save method to return the same move object
        when(moveRepository.save(any(Move.class))).thenReturn(move);

        // Perform a POST request to the createChoice method
        mockMvc.perform(post("/games/1/moves")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"gameId\":1,\"turnId\":1,\"playerId\":1,\"reg1\":\"FORWARD\",\"reg2\":\"FORWARD\",\"reg3\":\"FORWARD\",\"reg4\":\"FORWARD\",\"reg5\":\"FORWARD\"}"))
                .andExpect(status().isOk());

        // Verify that the save method was called once
        verify(moveRepository, times(1)).save(any(Move.class));
    }
    /**
     * This test checks the functionality of creating a move with missing fields.
     * It performs a POST request to the /games/1/moves endpoint with a JSON body where all the required fields are null.
     * It expects a BadRequest status in response with the message "GameId, TurnId, PlayerId, Reg1, Reg2, Reg3, Reg4 and Reg5 must be provided".
     */
    @Test
    public void testCreateChoiceWithMissingFields() throws Exception {
        mockMvc.perform(post("/games/1/moves")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"turnId\":null,\"playerId\":null,\"reg1\":null,\"reg2\":null,\"reg3\":null,\"reg4\":null,\"reg5\":null}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("GameId, TurnId, PlayerId, Reg1, Reg2, Reg3, Reg4 and Reg5 must be provided"));
    }
    /**
     * This test checks the functionality of creating a move that already exists.
     * It mocks the findMoveByGameIdAndTurnIdAndPlayerId method to return a non-null value, indicating that the move already exists.
     * It then performs a POST request to the /games/1/moves endpoint with a JSON body containing valid data for a move.
     * It expects a BadRequest status in response with the message "Move already exists".
     */
    @Test
    public void testCreateChoiceWithExistingMove() throws Exception {
        when(moveRepository.findMoveByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong())).thenReturn(new Move());

        mockMvc.perform(post("/games/1/moves")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"turnId\":1,\"playerId\":1,\"reg1\":1,\"reg2\":1,\"reg3\":1,\"reg4\":1,\"reg5\":1}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Move already exists"));

        verify(moveRepository, times(1)).findMoveByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong());
    }

}