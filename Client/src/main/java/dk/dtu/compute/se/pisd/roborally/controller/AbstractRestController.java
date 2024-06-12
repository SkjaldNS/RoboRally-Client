package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.Game;

import java.util.List;

public interface AbstractRestController {

    List<Integer> getPlayerIds();

    List<Game> getGames();

    void createGame();

    void joinGame(int gameId);
}
