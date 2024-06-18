package dk.dtu.roborally_server.controller;

import dk.dtu.roborally_server.model.Player;
import dk.dtu.roborally_server.repository.PlayerRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * PlayerController is a REST controller that handles HTTP requests related to players.
 * It provides endpoints for creating, updating, deleting, and retrieving players.
 * It uses PlayerRepository to interact with the database.
 * @author : Marcus Langkilde (s195080)
 */
@RestController
@RequestMapping("/games/{gameId}/players")
public class PlayerController {

    private PlayerRepository playerRepository;

    /**
     * Constructs a new PlayerController with the given PlayerRepository.
     * @param playerRepository the PlayerRepository to use for database interaction
     */
    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    /**
     * Handles GET requests to retrieve all players.
     * @return a ResponseEntity containing a list of all players and an HTTP status code
     */
    @GetMapping("")
    public ResponseEntity<List<Player>> getPlayers(@PathVariable Long gameId){
        List<Player> playerList = playerRepository.findPlayersByGameId(gameId);
        return ResponseEntity.ok(playerList);
    }
    /**
     * Handles POST requests to create a new player with the given name.
     * @param playerName the name of the player to create
     * @return a ResponseEntity containing the player's ID and an HTTP status code
     */
    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "")
    public ResponseEntity<Player> createPlayer(@PathVariable Long gameId) {
        List<Player> players = playerRepository.findPlayersByGameId(gameId);
        if(players != null) {
            return ResponseEntity.badRequest().body(null);
        }

        Player player = new Player();
        player.setGameId(gameId);
        playerRepository.save(player);

        return ResponseEntity.ok(player);
    }
    /**
     * Handles PUT requests to update the player with the given ID.
     * @param playerId the ID of the player to update
     * @param updatedPlayer the new data for the player
     * @return a ResponseEntity containing an HTTP status code
     */
    @PutMapping(value = "/{playerId}")
    public ResponseEntity<String> updatePlayer(@PathVariable Long gameId, @PathVariable Long playerId, @RequestBody Player updatedPlayer) {
        Player player = playerRepository.findPlayerByIdAndGameId(playerId, gameId);
        if(player == null) {
            return ResponseEntity.badRequest().body("Player does not exist");
        }
        updatedPlayer.setId(playerId);
        playerRepository.save(updatedPlayer);
        return ResponseEntity.ok().build();
    }

    /**
     * Handles DELETE requests to delete the player with the given ID.
     * @param playerId the ID of the player to delete
     * @return a ResponseEntity containing an HTTP status code
     */
    @DeleteMapping(value = "/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long gameId, @PathVariable Long playerId) {
        Player player = playerRepository.findPlayerByIdAndGameId(playerId, gameId);
        if(player == null)
            return ResponseEntity.badRequest().body("Player not found");
        playerRepository.delete(player);
        return ResponseEntity.ok().build();
    }
}