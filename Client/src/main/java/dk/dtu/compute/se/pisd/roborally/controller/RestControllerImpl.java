package dk.dtu.compute.se.pisd.roborally.controller;

import dk.dtu.compute.se.pisd.roborally.model.Game;
import dk.dtu.compute.se.pisd.roborally.model.GameStatus;

import java.util.ArrayList;
import java.util.List;

public class RestControllerImpl implements RestController {

    private final List<Game> games = new ArrayList<>();

    public RestControllerImpl() {
        // Initialize with some mock data
        games.add(new Game(1, 1, GameStatus.PRELOBBY, 0));
        games.add(new Game(2, 2, GameStatus.PRELOBBY, 1));
    }

    @Override
    public List<Game> getGames() {
        return new ArrayList<>(games);
    }

    @Override
    public void joinGame(int gameId) {
        System.out.println("Joining game with ID: " + gameId);
    }

    @Override
    public void startGame(int gameId) {
        System.out.println("Starting game with ID: " + gameId);
    }

    @Override
    public List<Integer> getPlayerIds() {
        // Provide a mock implementation or fetch from your data source
        return List.of(1, 2, 3, 4);
    }

    @Override
    public void createGame() {
        int newGameId = games.size() + 1;
        games.add(new Game(newGameId, newGameId, GameStatus.PRELOBBY, 0));
        System.out.println("Created game with ID: " + newGameId);
    }
}
