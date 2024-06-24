package dk.dtu.roborally_server.controller;

import dk.dtu.roborally_server.model.Choice;
import dk.dtu.roborally_server.repository.ChoiceRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("games/{gameId}/choices")
public class ChoiceController {

    private ChoiceRepository choiceRepository;

    public ChoiceController(ChoiceRepository choiceRepository) {this.choiceRepository = choiceRepository;}

    @GetMapping
    @RequestMapping( "/{turnId}/{playerId}")
    public ResponseEntity<Choice> getChoices(@PathVariable("gameId") Long gameId, @PathVariable("turnId") Long turnId, @PathVariable("playerId") Long playerId){
        Choice choice = choiceRepository.findChoiceByGameIdAndTurnIdAndPlayerId(gameId, turnId, playerId);
        return ResponseEntity.ok(choice);
    }

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
