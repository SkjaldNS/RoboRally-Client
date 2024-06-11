package dk.dtu.roborally_server.controller;

import dk.dtu.roborally_server.model.Choice;
import dk.dtu.roborally_server.model.Move;
import dk.dtu.roborally_server.repository.MoveRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("games/{gameId}/moves")
public class MoveController {

    private MoveRepository moveRepository;

    public MoveController(MoveRepository moveRepository) {this.moveRepository = moveRepository;}

    @GetMapping
    @RequestMapping( "{turnId}")
    public ResponseEntity<List<Move>> getMoves(@PathVariable("gameId") Long gameId, @PathVariable("turnId") Long turnId){
        List<Move> listMove = moveRepository.findMoveByGameIdAndTurnId(gameId, turnId);
        return ResponseEntity.ok(listMove);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "")
    public ResponseEntity<String> createChoice(@RequestBody Move move, @PathVariable("gameId") Long gameId){
        move.setGameId(gameId);
        if (move.getTurnId() == null || move.getPlayerId() == null || move.getReg1() == null || move.getReg2() == null || move.getReg3() == null || move.getReg4() == null || move.getReg5() == null)
            return ResponseEntity.badRequest().body("GameId, TurnId, PlayerId, Reg1, Reg2, Reg3, Reg4 and Reg5 must be provided");
        if (moveRepository.findMoveByGameIdAndTurnIdAndPlayerId(move.getGameId(), move.getTurnId(), move.getPlayerId()) != null)
            return ResponseEntity.badRequest().body("Move already exists");
        moveRepository.save(move);
        return ResponseEntity.ok().build();
    }
}