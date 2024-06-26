package dk.dtu.compute.se.pisd.roborally.controller;

import java.util.List;
import java.util.concurrent.*;

/**
 * The DataUpdater class is responsible for scheduling and managing various tasks that need to be run periodically.
 * It uses a ScheduledExecutorService to schedule tasks at fixed rate or after a certain delay.
 */
public class DataUpdateController {

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private static final int POLLING_INTERVAL_SECONDS = 1;
    private static final int PROGRAM_EXECUTION_SECONDS = 2;
    private ScheduledFuture<?> playerListFuture;
    private ScheduledFuture<?> gameStateFuture;
    private ScheduledFuture<?> moveFuture;
    private ScheduledFuture<?> choiceFuture;
    private ScheduledFuture<?> programExecutionFuture;

    private static DataUpdateController instance = new DataUpdateController();

    private DataUpdateController() {}

    /**
     * Returns the singleton instance of the DataUpdater class.
     *
     * @return the singleton instance of the DataUpdater class
     */
    public static DataUpdateController getInstance() {
        synchronized (DataUpdateController.class) {
            if (instance == null) {
                instance = new DataUpdateController();
            }
        }
        return instance;
    }
    /**
     * Starts a task that polls the player list at a fixed rate.
     *
     * @param task the task to run
     */
    private void startPlayerList(Runnable task) {
        playerListFuture = executorService.scheduleAtFixedRate(task, 0, POLLING_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * Starts tasks that poll the player list and game list at a fixed rate.
     *
     * @param playerListTask the task that polls the player list
     * @param gameListTask the task that polls the game list
     */
    public void startLobbyPolling(Runnable playerListTask, Runnable gameListTask) {
        startPlayerList(playerListTask);
        startGamePolling(gameListTask);
    }

    /**
     * Starts a task that polls the game state at a fixed rate.
     *
     * @param task the task to run
     */
    public void startGamePolling(Runnable task) {
        gameStateFuture = executorService.scheduleAtFixedRate(task, 0, POLLING_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * Stops the tasks that poll the player list and game list.
     */
    public void stopLobbyPolling() {
        playerListFuture.cancel(false);
        gameStateFuture.cancel(false);
    }

    /**
     * Starts a task that polls the move at a fixed rate.
     *
     * @param task the task to run
     */
    public void startMovePolling(Runnable task) {
        moveFuture = executorService.scheduleWithFixedDelay(task, 0, POLLING_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * Stops the task that polls the move.
     */
    public void stopMovePolling() {
        moveFuture.cancel(true);
    }

    /**
     * Starts a task that polls the choice at a fixed rate.
     *
     * @param task the task to run
     */
    public void startChoicePolling(Runnable task) {
        choiceFuture = executorService.scheduleWithFixedDelay(task, 0, POLLING_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    /**
     * Stops the task that polls the choice.
     */
    public void stopChoicePolling() {
        choiceFuture.cancel(false);
    }

    /**
     * Stops the executor service, effectively stopping all tasks.
     */
    public void stopExecutorService() {
        executorService.shutdown();
    }

    public void startProgramExecution(Runnable task) {
        programExecutionFuture = executorService.scheduleWithFixedDelay(task,0, PROGRAM_EXECUTION_SECONDS, TimeUnit.SECONDS);
    }

    public void stopProgramExecution() {
        programExecutionFuture.cancel(true);
    }
}
