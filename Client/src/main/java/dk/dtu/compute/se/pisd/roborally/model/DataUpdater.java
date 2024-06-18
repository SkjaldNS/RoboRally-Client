package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.controller.RestController;

import java.util.List;

public class DataUpdater extends Subject {

    private List<Player> players;
    private Game game;
    private final RestController restController;
    private Thread worker;

    public DataUpdater(int gameId, RestController restController) throws Exception {
        this.game = restController.getGame(gameId);
        this.players = restController.getPlayers(gameId);
        this.restController = restController;
        startUpdater(5);
    }

    public void updatePlayers(List<Player> players) {
        this.players = players;
        notifyChange();
    }

    public void updateGame(Game game) {
        this.game = game;
        notifyChange();
    }

    private void startUpdater(int seconds) {
        worker = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(seconds * 1000L);
                    players = fetchPlayers();
                    game = fetchGame();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                notifyChange();
            }
        });
        worker.start();
    }

    private List<Player> fetchPlayers() throws Exception {
        return restController.getPlayers(game.getGameID());
    }

    private Game fetchGame() throws Exception {
        return restController.getGame(game.getGameID());
    }

    public void stopUpdater() {
        worker.interrupt();
    }
}
