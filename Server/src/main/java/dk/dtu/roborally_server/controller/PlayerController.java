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
@RequestMapping("/players")
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
    @GetMapping
    @RequestMapping(value = "/getPlayers")
    public ResponseEntity<List<Player>> getPlayers(){
        List<Player> playerList = playerRepository.findAll();
        return ResponseEntity.ok(playerList);
    }
    /**
     * Handles POST requests to create a new player with the given name.
     * @param playerName the name of the player to create
     * @return a ResponseEntity containing the player's ID and an HTTP status code
     */
    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    @RequestMapping(value = "/createPlayer/{playerName}")
    public ResponseEntity<String> createPlayer(@PathVariable String playerName) {
        if(playerName == null || playerName.isEmpty())
            return ResponseEntity.badRequest().body("Name must be provided");
        Player player = playerRepository.findPlayerByPlayerName(playerName);
        if(player != null)
            return ResponseEntity.badRequest().body("Player already exists");
        player = new Player();
        player.setPlayerName(playerName);
        playerRepository.save(player);
        return ResponseEntity.ok(player.getPlayerId().toString());
    }
    /**
     * Handles PUT requests to update the player with the given ID.
     * @param playerId the ID of the player to update
     * @param updatedPlayer the new data for the player
     * @return a ResponseEntity containing an HTTP status code
     */
    @PutMapping
    @RequestMapping(value = "/updatePlayer/{playerId}")
    public ResponseEntity<String> updatePlayer(@PathVariable Long playerId,@RequestBody Player updatedPlayer) {
        Player existingPlayer = playerRepository.findPlayerById(playerId).orElse(null);
        if(updatedPlayer == null){
            return ResponseEntity.badRequest().body("updated player data must be provided");
        }
        if(existingPlayer == null)
            return ResponseEntity.badRequest().body("Player does not exist");
        updatedPlayer.setId(playerId);
        playerRepository.save(updatedPlayer);
        return ResponseEntity.ok().build();
    }

    /**
     * Handles DELETE requests to delete the player with the given ID.
     * @param playerId the ID of the player to delete
     * @return a ResponseEntity containing an HTTP status code
     */
    @DeleteMapping
    @RequestMapping(value = "/deletePlayer/{playerId}")
    public ResponseEntity<String> deletePlayer(@PathVariable Long playerId) {
        if(playerId == null)
            return ResponseEntity.badRequest().body("player id be provided");
        if(playerRepository.findPlayerById(playerId).isEmpty())
            return ResponseEntity.badRequest().body("Player does not exist");
        playerRepository.delete(playerRepository.findPlayerById(playerId).get());
        return ResponseEntity.ok().build();
    }
}