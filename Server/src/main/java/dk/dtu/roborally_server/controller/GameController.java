package dk.dtu.roborally_server.controller;


import dk.dtu.roborally_server.model.Game;
import dk.dtu.roborally_server.repository.GameRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    @GetMapping
    @RequestMapping(value = "")
    public ResponseEntity<List<Game>> getGames() {
        List<Game> gameList = gameRepository.findAll();
        return ResponseEntity.ok(gameList);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "")
    public ResponseEntity<String> createGame(Game game) {
        if(game.getGameName() == null)
            return ResponseEntity.badRequest().body("Name must be provided");
        if(gameRepository.findGameByGameName(game.getGameName()) != null)
            return ResponseEntity.badRequest().body("Game already exists");
        gameRepository.save(game);
        return ResponseEntity.ok().build();
    }

}
