package dk.dtu.roborally_server.controllerTests;

import dk.dtu.roborally_server.controller.ChoiceController;
import dk.dtu.roborally_server.model.Choice;
import dk.dtu.roborally_server.repository.ChoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This class contains unit tests for the ChoiceController class.
 * It tests the functionality of getting and creating choices for a specific game and turn.
 * Each test case is designed to test a specific functionality of the ChoiceController.
 *
 * The class uses the MockMvc class from the Spring Test framework to perform HTTP requests to the ChoiceController.
 * The responses from the ChoiceController are then checked for correctness.
 *
 * The ChoiceRepository is mocked to isolate the tests from the database.
 * This allows the tests to be run without any existing database setup and ensures that the tests do not affect the database.
 *
 * The setup method is run before each test to setup the test environment.
 * It initializes the ChoiceController and MockMvc objects and resets the ChoiceRepository mock.
 */
public class ChoiceControllerTest {

    private ChoiceRepository choiceRepository;
    private ChoiceController choiceController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        choiceRepository = mock(ChoiceRepository.class);
        choiceController = new ChoiceController(choiceRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(choiceController).build();
    }

    /**
     * This test checks the functionality of getting a choice for a specific game, turn and player.
     * It performs a GET request to the /games/{gameId}/choices/{turnId}/{playerId} endpoint and checks that the status is OK.
     *
     * The ChoiceRepository is mocked to return a specific choice when the findChoiceByGameIdAndTurnIdAndPlayerId method is called.
     * This allows the test to check that the ChoiceController correctly calls the ChoiceRepository and handles the returned choice.
     */
    @Test
    public void testGetChoices() throws Exception {
        Choice choice = new Choice();
        when(choiceRepository.findChoiceByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong())).thenReturn(choice);

        mockMvc.perform(get("/games/1/choices/1/1"))
                .andExpect(status().isOk());

        verify(choiceRepository, times(1)).findChoiceByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong());
    }
    /**
     * This test checks the functionality of creating a choice for a specific game, turn and player.
     * It performs a POST request to the /games/{gameId}/choices endpoint with a JSON body containing the choice data and checks that the status is OK.
     *
     * The ChoiceRepository is mocked to return null when the findChoiceByGameIdAndTurnIdAndPlayerId method is called, indicating that the choice does not exist.
     * This allows the test to check that the ChoiceController correctly calls the ChoiceRepository and handles the case where the choice does not exist.
     */
    @Test
    public void testCreateChoice() throws Exception {
        when(choiceRepository.findChoiceByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong())).thenReturn(null);

        mockMvc.perform(post("/games/1/choices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"gameId\":1,\"turnId\":1,\"playerId\":1,\"choiceType\":\"LEFT\"}"))
                .andExpect(status().isOk());

        verify(choiceRepository, times(1)).save(any(Choice.class));
    }

    /**
     * This test checks the functionality of creating a choice without providing a gameId.
     * It performs a POST request to the /games/{gameId}/choices endpoint with a JSON body containing the choice data and checks that the status is BadRequest.
     */
    @Test
    public void testCreateChoiceWithoutGameId() throws Exception {
        mockMvc.perform(post("/games/1/choices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"turnId\":1,\"playerId\":null,\"choiceType\":\"LEFT\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("GameId, TurnId, PlayerId and Choice must be provided"));
    }

    /**
     * This test checks the functionality of creating a choice that already exists.
     * It performs a POST request to the /games/{gameId}/choices endpoint with a JSON body containing the choice data and checks that the status is BadRequest.
     *
     * The ChoiceRepository is mocked to return a specific choice when the findChoiceByGameIdAndTurnIdAndPlayerId method is called, indicating that the choice already exists.
     */
    @Test
    public void testCreateChoiceThatAlreadyExists() throws Exception {
        Choice choice = new Choice();
        when(choiceRepository.findChoiceByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong())).thenReturn(choice);

        mockMvc.perform(post("/games/1/choices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"gameId\":1,\"turnId\":1,\"playerId\":1,\"choiceType\":\"LEFT\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Choice already exists"));

        verify(choiceRepository, times(1)).findChoiceByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong());
    }
}