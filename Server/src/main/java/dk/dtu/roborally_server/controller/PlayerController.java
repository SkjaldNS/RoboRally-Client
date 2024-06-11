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

    public PlayerController(PlayerRepository playerReposity) {
        this.playerRepository = playerRepository;
    }
    @GetMapping
    @RequestMapping(value = "")
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
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "")
    public ResponseEntity<String> createPlayer(@RequestBody Player player) {
        if(player.getPlayerName() == null)
            return ResponseEntity.badRequest().body("Name and GameId must be provided");
        if(playerRepository.findPlayerByName(player.getPlayerName())!= null)
            return ResponseEntity.badRequest().body("Player already exists");
        playerRepository.save(player);
        return ResponseEntity.ok().build();
    }
}