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

    @Test
    public void testGetGame() throws Exception {
        Game game = new Game();
        when(gameRepository.findGameByGameId(anyLong())).thenReturn(game);

        mockMvc.perform(get("/games/1"))
                .andExpect(status().isOk());

        verify(gameRepository, times(1)).findGameByGameId(anyLong());
    }


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