package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.controller.RestController;

import java.util.List;
import java.util.concurrent.*;

public class DataUpdater {

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private static final int POLLING_INTERVAL_SECONDS = 1;

    private ScheduledFuture<?> timer;

    private ScheduledFuture<?> playerListFuture;
    private ScheduledFuture<?> gameStateFuture;

    private static DataUpdater instance;

    public static DataUpdater getInstance() {
        if (instance == null) {
            instance = new DataUpdater();
        }
        return instance;
    }

    public void startTimer(int seconds, Runnable task) {
        timer = executorService.schedule(task, seconds, TimeUnit.SECONDS);
    }

    public void stopTimer() {
        timer.cancel(false);
    }

    private void startPlayerList(Runnable task) {
        playerListFuture = executorService.scheduleAtFixedRate(task, 0, POLLING_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    public void startLobbyPolling(Runnable playerListTask, Runnable gameListTask) {
        startPlayerList(playerListTask);
        startGamePolling(gameListTask);
    }

    public void startGamePolling(Runnable task) {
        gameStateFuture = executorService.scheduleAtFixedRate(task, 0, POLLING_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    public void stopLobbyPolling() {
        playerListFuture.cancel(false);
        gameStateFuture.cancel(false);
    }

    private void pollGameState() {

    }

    public void stopPlayerListPolling() {
        playerListFuture.cancel(false);
    }

    private void stopGamePolling() {
        gameStateFuture.cancel(false);
    }
}
