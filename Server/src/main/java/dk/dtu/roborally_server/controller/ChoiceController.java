package dk.dtu.roborally_server.controller;

import dk.dtu.roborally_server.model.Choice;
import dk.dtu.roborally_server.repository.ChoiceRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * ChoiceController is a RestController that handles the HTTP requests related to the Choice model.
 */
@RestController
@RequestMapping("games/{gameId}/choices")
public class ChoiceController {

    private ChoiceRepository choiceRepository;

    /**
     * Constructor for the ChoiceController.
     * @param choiceRepository The repository that the controller should use.
     */
    public ChoiceController(ChoiceRepository choiceRepository) {this.choiceRepository = choiceRepository;}

    /**
     * GetMapping for getting all choices in a game.
     * @param gameId The id of the game.
     * @return ResponseEntity with a list of choices.
     */
    @GetMapping
    @RequestMapping( "/{turnId}/{playerId}")
    public ResponseEntity<Choice> getChoices(@PathVariable("gameId") Long gameId, @PathVariable("turnId") Long turnId, @PathVariable("playerId") Long playerId){
        Choice choice = choiceRepository.findChoiceByGameIdAndTurnIdAndPlayerId(gameId, turnId, playerId);
        return ResponseEntity.ok(choice);
    }

    /**
     * PostMapping for creating a choice.
     * @param choice The choice to be created.
     * @param gameId The id of the game.
     * @return ResponseEntity with a message.
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = "")
    public ResponseEntity<String> createChoice(@RequestBody Choice choice, @PathVariable("gameId") Long gameId) {
        choice.setGameId(gameId);
        if(choice.getTurnId() == null || choice.getPlayerId() == null || choice.getChoiceType() == null)
            return ResponseEntity.badRequest().body("GameId, TurnId, PlayerId and Choice must be provided");
        if(choiceRepository.findChoiceByGameIdAndTurnIdAndPlayerId(choice.getGameId(), choice.getTurnId(), choice.getPlayerId()) != null)
            return ResponseEntity.badRequest().body("Choice already exists");
        choiceRepository.save(choice);
        return ResponseEntity.ok().build();
    }
}
