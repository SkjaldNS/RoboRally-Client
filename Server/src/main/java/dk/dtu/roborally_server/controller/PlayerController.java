package dk.dtu.roborally_server.controller;

import dk.dtu.roborally_server.model.Player;
import dk.dtu.roborally_server.repository.PlayerRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    @GetMapping
    @RequestMapping(value = "/getPlayers")
    public ResponseEntity<List<Player>> getPlayers(){
        List<Player> playerList = playerRepository.findAll();
        return ResponseEntity.ok(playerList);
    }
    /*
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "")
    public ResponseEntity<String> createPlayer(@RequestBody Player player) {
        if(player.getPlayerName() == null || player.getGameId() == null)
            return ResponseEntity.badRequest().body("Name and GameId must be provided");
        if(playerRepository.findPlayerByNameAndGameId(player.getPlayerName(), player.getGameId()) != null)
            return ResponseEntity.badRequest().body("Player already exists");
        playerRepository.save(player);
        return ResponseEntity.ok().build();
    }
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