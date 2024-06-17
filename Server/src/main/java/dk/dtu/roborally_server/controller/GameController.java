package dk.dtu.roborally_server.controller;


import dk.dtu.roborally_server.model.Game;
import dk.dtu.roborally_server.repository.GameRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    @GetMapping
    public ResponseEntity<List<Game>> getGames() {
        List<Game> gameList = gameRepository.findAll();
        return ResponseEntity.ok(gameList);
    }


    @GetMapping
    @RequestMapping(value = "/getGame/{gameId}")
    public ResponseEntity<Game> getGame(@PathVariable("gameId") Long gameId) {
        Game game = gameRepository.findGameByGameId(gameId);
        return ResponseEntity.ok(game);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createGame(@RequestBody Game game) {
        if(game.getGameName() == null)
            return ResponseEntity.badRequest().body("Name must be provided");
        if(gameRepository.findGameByGameName(game.getGameName()) != null)
            return ResponseEntity.badRequest().body("Game already exists");
        gameRepository.save(game);
        return ResponseEntity.ok(game.getGameId().toString());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateGame(@RequestBody Game game) {
        if(game.getGameId() == null) {
            return ResponseEntity.badRequest().body("Game Id must be provided");
        }
        if(game.getGameName() == null) {
            return ResponseEntity.badRequest().body("Name must be provided");
        }
        if(gameRepository.findGameByGameId(game.getGameId()) == null) {
            return ResponseEntity.badRequest().body("Game does not exist");
        }
        gameRepository.save(game);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping
    @RequestMapping(value = "/{gameId}")
    public ResponseEntity<String> deleteGame(@PathVariable("gameId") Long gameId) {
        if(gameId == null)
            return ResponseEntity.badRequest().body("Game Id must be provided");
        Game game = gameRepository.findGameByGameId(gameId);
        if(game == null)
            return ResponseEntity.badRequest().body("Game does not exist");
        gameRepository.delete(game);
        return ResponseEntity.ok().build();
    }

}
// Create json for game
// {
//     "gameName": "game1",
//     "boardId": 1,
//     "gameStatus": "WAITING",
//     "turnID": 1,
//     "maxPlayers": 4
// }