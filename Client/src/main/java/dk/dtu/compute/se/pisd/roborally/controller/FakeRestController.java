package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.Game;
import dk.dtu.compute.se.pisd.roborally.model.GameStatus;

import java.util.List;

public class FakeRestController implements RestController {

    @Override
    public List<Integer> getPlayerIds() {
        return List.of(1, 2, 3, 4, 5, 6);
    }

    @Override
    public List<Game> getGames() {
        return List.of(
                new Game(1, 0, GameStatus.PRELOBBY, 0),
                new Game(2, 0, GameStatus.PRELOBBY, 0),
                new Game(3, 0, GameStatus.PRELOBBY, 0),
                new Game(4, 0, GameStatus.PRELOBBY, 0),
                new Game(5, 0, GameStatus.PRELOBBY, 0),
                new Game(6, 0, GameStatus.PRELOBBY, 0)
        );
    }

    @Override
    public void createGame() {
        System.out.println("Game created");
    }

    @Override
    public void joinGame(int gameId) {
        System.out.println("Joined game " + gameId);
    }

    @Override
    public void startGame(int gameId) {
        System.out.println("Started game " + gameId);
    }
}
