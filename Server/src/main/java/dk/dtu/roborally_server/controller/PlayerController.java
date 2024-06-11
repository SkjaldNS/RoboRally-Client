package dk.dtu.roborally_server.controller;

import dk.dtu.roborally_server.model.Player;
import dk.dtu.roborally_server.repository.PlayerReposity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private PlayerReposity playerReposity;

    public PlayerController(PlayerReposity playerReposity) {
        this.playerReposity = playerReposity;
    }
    @GetMapping
    @RequestMapping(value = "")
    public ResponseEntity<List<Player>> getPlayers(){
        List<Player> playerList = playerReposity.findAll();
        return ResponseEntity.ok(playerList);
    }
}