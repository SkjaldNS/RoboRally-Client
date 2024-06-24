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