package dk.dtu.roborally_server.controllerTests;

import dk.dtu.roborally_server.controller.GameController;
import dk.dtu.roborally_server.model.Game;
import dk.dtu.roborally_server.repository.GameRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * This class contains unit tests for the GameController class.
 * It tests the functionality of getting, creating, updating, and deleting games.
 * Each test case is designed to test a specific functionality of the GameController.
 *
 * The class uses the MockMvc class from the Spring Test framework to perform HTTP requests to the GameController.
 * The responses from the GameController are then checked for correctness.
 *
 * The GameRepository is mocked to isolate the tests from the database.
 * This allows the tests to be run without any existing database setup and ensures that the tests do not affect the database.
 *
 * The setup method is run before each test to setup the test environment.
 * It initializes the GameController and MockMvc objects and resets the GameRepository mock.
 */
public class GameControllerTest {

    private GameRepository gameRepository;
    private GameController gameController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        gameRepository = mock(GameRepository.class);
        gameController = new GameController(gameRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(gameController).build();
    }

    /**
     * This test checks the functionality of getting a game by its ID.
     * It performs a GET request to the /games/{gameId} endpoint and checks that the status is OK.
     *
     * The GameRepository is mocked to return a specific game when the findGameByGameId method is called.
     * This allows the test to check that the GameController correctly calls the GameRepository and handles the returned game.
     */
    @Test
    public void testGetGame() throws Exception {
        Game game = new Game();
        when(gameRepository.findGameByGameId(anyLong())).thenReturn(game);

        mockMvc.perform(get("/games/1"))
                .andExpect(status().isOk());

        verify(gameRepository, times(1)).findGameByGameId(anyLong());
    }

    /**
     * This test checks the functionality of creating a new game.
     * It performs a POST request to the /games endpoint with a JSON body containing the game data and checks that the status is OK.
     *
     * The GameRepository is mocked to return null when the findGameByGameId method is called, indicating that the game does not exist.
     * This allows the test to check that the GameController correctly calls the GameRepository and handles the case where the game does not exist.
     */
    @Test
    public void testCreateGame() throws Exception {
        // Create a new game object with no ID and a name
        Game game = new Game();
        game.setGameName("Test Game");

        // Mock the findGameByGameId method to return null, indicating that the game does not exist
        when(gameRepository.findGameByGameId(anyLong())).thenReturn(null);

        // Mock the save method to return a new Game object with a generated ID
        when(gameRepository.save(any(Game.class))).thenAnswer(invocation -> {
            Game savedGame = invocation.getArgument(0);
            savedGame.setGameId(1L); // Set the ID to a specific value
            return savedGame;
        });

        // Perform a POST request to the createGame method
        MvcResult result = mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"gameName\":\"Test Game\"}"))
                .andExpect(status().isOk())
                .andReturn();

        // Verify that the save method was called once
        verify(gameRepository, times(1)).save(any(Game.class));

        // Check that the gameId is 1
        assertEquals("1", result.getResponse().getContentAsString());
    }
    /**
     * This test checks the functionality of getting all games.
     * It performs a GET request to the /games endpoint and checks that the status is OK and the returned list has the correct size.
     *
     * The GameRepository is mocked to return a list of games when the findAll method is called.
     * This allows the test to check that the GameController correctly calls the GameRepository and handles the returned list of games.
     */
    @Test
    public void testGetGames() throws Exception {
        // Create a list of games
        List<Game> games = new ArrayList<>();
        games.add(new Game());
        games.add(new Game());

        // Mock the findAll method to return the list of games
        when(gameRepository.findAll()).thenReturn(games);

        // Perform a GET request to the getGames method
        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))); // Expect the list to have 2 games

        // Verify that the findAll method was called once
        verify(gameRepository, times(1)).findAll();
    }
    /**
     * This test checks the functionality of updating a game.
     * It performs a PUT request to the /games endpoint with a JSON body containing the updated game data and checks that the status is OK.
     *
     * The GameRepository is mocked to return a specific game when the findGameByGameId method is called, indicating that the game exists.
     * This allows the test to check that the GameController correctly calls the GameRepository and handles the existing game.
     */
    @Test
    public void testUpdateGame() throws Exception {
        // Create a new game object with an ID and a name
        Game game = new Game();
        game.setGameId(1L);
        game.setGameName("Test Game");

        // Mock the findGameByGameId method to return the game, indicating that the game exists
        when(gameRepository.findGameByGameId(anyLong())).thenReturn(game);

        // Mock the save method to return the same game object
        when(gameRepository.save(any(Game.class))).thenReturn(game);

        // Perform a PUT request to the updateGame method
        mockMvc.perform(put("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"gameId\":1,\"gameName\":\"Updated Game\"}"))
                .andExpect(status().isOk());

        // Verify that the save method was called once
        verify(gameRepository, times(1)).save(any(Game.class));
    }
    /**
     * This test checks the functionality of deleting a game.
     * It performs a DELETE request to the /games/{gameId} endpoint and checks that the status is OK.
     *
     * The GameRepository is mocked to return a specific game when the findGameByGameId method is called, indicating that the game exists.
     * This allows the test to check that the GameController correctly calls the GameRepository and handles the existing game.
     */
    @Test
    public void testDeleteGame() throws Exception {
        // Create a new game object with an ID and a name
        Game game = new Game();
        game.setGameId(1L);
        game.setGameName("Test Game");

        // Mock the findGameByGameId method to return the game, indicating that the game exists
        when(gameRepository.findGameByGameId(anyLong())).thenReturn(game);

        // Perform a DELETE request to the deleteGame method
        mockMvc.perform(delete("/games/1"))
                .andExpect(status().isOk());

        // Verify that the delete method was called once
        verify(gameRepository, times(1)).delete(any(Game.class));
    }
    /**
     * This test checks the functionality of deleting a game that does not exist.
     * It performs a DELETE request to the /games/{gameId} endpoint and checks that the status is BadRequest.
     *
     * The GameRepository is mocked to return null when the findGameByGameId method is called, indicating that the game does not exist.
     * This allows the test to check that the GameController correctly calls the GameRepository and handles the case where the game does not exist.
     */
    @Test
    public void testDeleteGameNotFound() throws Exception {
        // Mock the findGameByGameId method to return null, indicating that the game does not exist
        when(gameRepository.findGameByGameId(anyLong())).thenReturn(null);

        // Perform a DELETE request to the deleteGame method
        mockMvc.perform(delete("/games/1"))
                .andExpect(status().isBadRequest());

        // Verify that the delete method was not called
        verify(gameRepository, times(0)).delete(any(Game.class));
    }
}