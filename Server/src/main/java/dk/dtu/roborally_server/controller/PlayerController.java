package dk.dtu.roborally_server.controller;

import dk.dtu.roborally_server.model.Player;
import dk.dtu.roborally_server.repository.PlayerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}