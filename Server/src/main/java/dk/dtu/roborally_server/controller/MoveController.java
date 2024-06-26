package dk.dtu.roborally_server.controller;

import dk.dtu.roborally_server.model.Choice;
import dk.dtu.roborally_server.model.Move;
import dk.dtu.roborally_server.repository.MoveRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class is a controller that handles the HTTP requests from the client related to the moves of the game.
 * It is a RESTful controller that handles GET and POST requests.
 * The class is used to get the moves of a specific turn of a game and to create a move in a game.
 */
@RestController
@RequestMapping("games/{gameId}/moves")
public class MoveController {

    private MoveRepository moveRepository;

    /**
     * Constructor that initializes the MoveController object.
     * @param moveRepository Repository that handles the operations of the moves in the database.
     */
    public MoveController(MoveRepository moveRepository) {this.moveRepository = moveRepository;}

    /**
     * Method that handles the GET request to get the moves of a specific turn of a game.
     * @param gameId Id of the game.
     * @param turnId Id of the turn.
     * @return ResponseEntity with the list of moves of the turn.
     */
    @GetMapping
    @RequestMapping("/{turnId}")
    public ResponseEntity<List<Move>> getMoves(@PathVariable("gameId") Long gameId, @PathVariable("turnId") Long turnId){
        List<Move> listMove = moveRepository.findMoveByGameIdAndTurnId(gameId, turnId);
        return ResponseEntity.ok(listMove);
    }

    /**
     * Method that handles the POST request to create a move in a game.
     * @param move Move object that contains the information of the move.
     * @param gameId Id of the game.
     * @return ResponseEntity with the status of the operation.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "")
    public ResponseEntity<String> createChoice(@RequestBody Move move, @PathVariable("gameId") Long gameId){
        move.setGameId(gameId);
        if (move.getTurnId() == null || move.getPlayerId() == null || move.getReg1() == null || move.getReg2() == null || move.getReg3() == null || move.getReg4() == null || move.getReg5() == null)
            return ResponseEntity.badRequest().body("GameId, TurnId, PlayerId, Reg1, Reg2, Reg3, Reg4 and Reg5 must be provided");
        if (moveRepository.findMoveByGameIdAndTurnIdAndPlayerId(move.getGameId(), move.getTurnId(), move.getPlayerId()) != null) {
            System.out.println("Move already exists: " + move.getGameId() + " " + move.getTurnId() + " " + move.getPlayerId());
            return ResponseEntity.badRequest().body("Move already exists");
        }

        moveRepository.save(move);
        return ResponseEntity.ok().build();
    }
}