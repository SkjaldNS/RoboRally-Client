package dk.dtu.roborally_server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-docs")
public class DocumentationController {

    String documentation;
    public DocumentationController() {
        this.documentation = s;
    }

    @GetMapping
    @RequestMapping(value = "")
    public ResponseEntity<String> getDocumentation() {
        return ResponseEntity.ok(documentation);
    }

    String s = "Welcome to the RoboRally API documentation!\n"
            +"\n"
            +"The API is divided into four controllers: GameController, PlayerController, ChoiceController, and MoveController\n"
            +"The GameController is used to create, update, delete and get games.\n"
            +"The PlayerController is used to create, update, delete and get players.\n"
            +"The ChoiceController is used to create and get choices.\n"
            +"The MoveController is used to create and get moves.\n"
            +"The API is RESTful and uses JSON for communication.\n"
            +"\n"
            +"The GameController has the following endpoints:\n"
            +"GET /games: Get all games\n"
            +"POST /games: Create a game\n"
            +"PUT /games: Update a game\n"
            +"DELETE /games: Delete a game\n"
            +"\n"
            +"The PlayerController has the following endpoints:\n"
            +"GET /players: Get all players\n"
            +"POST /players: Create a player\n"
            +"PUT /players: Update a player\n"
            +"DELETE /players: Delete a player\n"
            +"\n"
            +"The ChoiceController has the following endpoints:\n"
            +"GET /games/{gameId}/choices/{turnId}/{playerId}: Get a choice\n"
            +"POST /games/{gameId}/choices: Create a choice\n"
            +"\n"
            +"The MoveController has the following endpoints:\n"
            +"GET /games/{gameId}/moves/{turnId}: Get all moves\n"
            +"POST /games/{gameId}/moves: Create a move\n"
            +"\n";
}
