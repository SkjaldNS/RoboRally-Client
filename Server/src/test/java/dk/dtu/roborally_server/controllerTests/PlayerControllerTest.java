package dk.dtu.roborally_server.controllerTests;

import dk.dtu.roborally_server.controller.PlayerController;
import dk.dtu.roborally_server.model.Player;
import dk.dtu.roborally_server.repository.PlayerRepository;
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
 * This class contains unit tests for the PlayerController class.
 * It tests the functionality of getting, creating, updating, and deleting players for a specific game.
 * Each test case is designed to test a specific functionality of the PlayerController.
 *
 * The class uses the MockMvc class from the Spring Test framework to perform HTTP requests to the PlayerController.
 * The responses from the PlayerController are then checked for correctness.
 *
 * The PlayerRepository is mocked to isolate the tests from the database.
 * This allows the tests to be run without any existing database setup and ensures that the tests do not affect the database.
 *
 * The setup method is run before each test to setup the test environment.
 * It initializes the PlayerController and MockMvc objects and resets the PlayerRepository mock.
 */
public class PlayerControllerTest {
    private PlayerController playerController;
    private PlayerRepository playerRepository;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        playerRepository = mock(PlayerRepository.class);
        playerController = new PlayerController(playerRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }
    /**
     * This test checks the functionality of getting players for a specific game.
     * It performs a GET request to the /games/{gameId}/players endpoint and checks that the status is OK.
     *
     * The PlayerRepository is mocked to return a list of players when the findPlayersByGameId method is called.
     * This allows the test to check that the PlayerController correctly calls the PlayerRepository and handles the returned players.
     */
    @Test
    public void testGetPlayers() throws Exception {
        // Create a list of players
        List<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());

        // Mock the findPlayersByGameId method to return the list of players
        when(playerRepository.findPlayersByGameId(anyLong())).thenReturn(players);

        // Perform a GET request to the getPlayers method
        mockMvc.perform(get("/games/1/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))); // Expect the list to have 2 players

        // Verify that the findPlayersByGameId method was called once
        verify(playerRepository, times(1)).findPlayersByGameId(anyLong());
    }
    /**
     * This test checks the functionality of creating a player for a specific game.
     * It performs a POST request to the /games/{gameId}/players endpoint with a plain text body containing the player name and checks that the status is OK.
     *
     * The PlayerRepository is mocked to return the same player object when the save method is called.
     * This allows the test to check that the PlayerController correctly calls the PlayerRepository and handles the new player.
     */
    @Test
    public void testCreatePlayer() throws Exception {
        // Create a new player object
        Player player = new Player();
        player.setGameId(1L);
        player.setPlayerName("Test Player");
        player.setPlayerId(1L);

        // Mock the save method to return the same player object
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        // Perform a POST request to the createPlayer method
        mockMvc.perform(post("/games/1/players")
                        .contentType(MediaType.TEXT_PLAIN)
                        .content("Test Player"))
                .andExpect(status().isOk());

        // Verify that the save method was called once
        verify(playerRepository, times(1)).save(any(Player.class));
    }
    /**
     * This test checks the functionality of updating a player for a specific game.
     * It performs a PUT request to the /games/{gameId}/players/{playerId} endpoint with a JSON body containing the updated player data and checks that the status is OK.
     *
     * The PlayerRepository is mocked to return the same player object when the findPlayerByIdAndGameId method is called.
     * This allows the test to check that the PlayerController correctly calls the PlayerRepository and handles the existing player.
     */
    @Test
    public void testUpdatePlayer() throws Exception {
        // Create a new player object
        Player player = new Player();
        player.setGameId(1L);
        player.setPlayerName("Test Player");
        player.setPlayerId(1L);

        // Mock the findPlayerByIdAndGameId method to return the same player object
        when(playerRepository.findPlayerByIdAndGameId(anyLong(), anyLong())).thenReturn(player);

        // Mock the save method to return the same player object
        when(playerRepository.save(any(Player.class))).thenReturn(player);

        // Perform a PUT request to the updatePlayer method
        mockMvc.perform(put("/games/1/players/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"gameId\":1,\"playerName\":\"Updated Player\"}"))
                .andExpect(status().isOk());

        // Verify that the save method was called once
        verify(playerRepository, times(1)).save(any(Player.class));
    }
    /**
     * This test checks the functionality of deleting a player for a specific game.
     * It performs a DELETE request to the /games/{gameId}/players/{playerId} endpoint and checks that the status is OK.
     *
     * The PlayerRepository is mocked to return the same player object when the findPlayerByIdAndGameId method is called.
     * This allows the test to check that the PlayerController correctly calls the PlayerRepository and handles the existing player.
     */
    @Test
    public void testDeletePlayer() throws Exception {
        // Create a new player object
        Player player = new Player();
        player.setGameId(1L);
        player.setPlayerName("Test Player");
        player.setPlayerId(1L);

        // Mock the findPlayerByIdAndGameId method to return the same player object
        when(playerRepository.findPlayerByIdAndGameId(anyLong(), anyLong())).thenReturn(player);

        // Perform a DELETE request to the deletePlayer method
        mockMvc.perform(delete("/games/1/players/1"))
                .andExpect(status().isOk());

        // Verify that the delete method was called once
        verify(playerRepository, times(1)).delete(any(Player.class));
    }
    /**
     * This test checks the functionality of deleting all players for a specific game.
     * It performs a DELETE request to the /games/{gameId}/players endpoint and checks that the status is OK.
     *
     * The PlayerRepository is mocked to return a list of players when the findPlayersByGameId method is called.
     * This allows the test to check that the PlayerController correctly calls the PlayerRepository and handles the existing players.
     */
    @Test
    public void testDeletePlayers() throws Exception {
        // Create a list of players
        List<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());

        // Mock the findPlayersByGameId method to return the list of players
        when(playerRepository.findPlayersByGameId(anyLong())).thenReturn(players);

        // Perform a DELETE request to the deletePlayers method
        mockMvc.perform(delete("/games/1/players"))
                .andExpect(status().isOk());

        // Verify that the deletePlayersByGameId method was called once
        verify(playerRepository, times(1)).deletePlayersByGameId(anyLong());
    }
}