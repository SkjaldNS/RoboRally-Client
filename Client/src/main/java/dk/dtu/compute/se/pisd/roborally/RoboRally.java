/*
 *  This file is part of the initial project provided for the
 *  course "Project in Software Development (02362)" held at
 *  DTU Compute at the Technical University of Denmark.
 *
 *  Copyright (C) 2019, 2020: Ekkart Kindler, ekki@dtu.dk
 *
 *  This software is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 *
 *  This project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package dk.dtu.compute.se.pisd.roborally;

import dk.dtu.compute.se.pisd.roborally.controller.ClientController;
import dk.dtu.compute.se.pisd.roborally.controller.RestController;
import dk.dtu.compute.se.pisd.roborally.controller.AppController;
import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.*;
import dk.dtu.compute.se.pisd.roborally.view.*;
import dk.dtu.compute.se.pisd.roborally.view.adminlobby.AdminLobbyBottom;
import dk.dtu.compute.se.pisd.roborally.view.adminlobby.AdminLobbyMap;
import dk.dtu.compute.se.pisd.roborally.view.adminlobby.AdminLobbyView;
import dk.dtu.compute.se.pisd.roborally.view.gameitem.GameItemListView;
import dk.dtu.compute.se.pisd.roborally.view.gameitem.GameItemView;
import dk.dtu.compute.se.pisd.roborally.view.playeritem.PlayerItemView;
import dk.dtu.compute.se.pisd.roborally.view.playeritem.PlayerListView;
import dk.dtu.compute.se.pisd.roborally.view.userlobby.UserLobbyBottom;
import dk.dtu.compute.se.pisd.roborally.view.userlobby.UserLobbyMap;
import dk.dtu.compute.se.pisd.roborally.view.userlobby.UserLobbyView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * ...
 *
 * @authork Ekkart Kindler, ekki@dtu.dk
 *
 */
public class RoboRally extends Application {

    private static final int MIN_APP_WIDTH = 600;
    private Stage stage;
    private BorderPane boardRoot;
    private GameSession gameSession;

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        AppController appController = new AppController(this);

        // create the primary scene with the a menu bar and a pane for
        // the board view (which initially is empty); it will be filled
        // when the user creates a new game or loads a game
        RoboRallyMenuBar menuBar = new RoboRallyMenuBar(appController);
        boardRoot = new BorderPane();
        VBox vbox = new VBox(menuBar, boardRoot);
        vbox.setMinWidth(MIN_APP_WIDTH);
        Scene primaryScene = new Scene(vbox);

        RestController restController = new ClientController();
        PreLobbyView preLobbyView = createPreLobbyView(appController, restController);
        boardRoot.setCenter(preLobbyView);

        stage.setScene(primaryScene);
        stage.setTitle("RoboRally");
        stage.setOnCloseRequest(
                e -> {
                    e.consume();
                    try {
                        appController.exit();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
        stage.setResizable(false);
        stage.sizeToScene();
        stage.show();
    }

    private AdminLobbyView createAdminLobbyView(AppController appController, PreLobbyView preLobbyView){
        AdminLobbyBottom adminLobbyBottom = new AdminLobbyBottom();
        AdminLobbyMap adminLobbyMap = new AdminLobbyMap();
        PlayerListView playerListView = new PlayerListView();

        AdminLobbyView adminLobbyView = new AdminLobbyView(playerListView, adminLobbyMap, adminLobbyBottom);

        adminLobbyBottom.setCloseButtonAction( () -> {
            boardRoot.setCenter(createPreLobbyView(appController, new ClientController()));
            DataUpdater.getInstance().stopLobbyPolling();
        });


        adminLobbyBottom.setStartGameButtonAction(() -> {
            try {
                appController.newGame();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        return adminLobbyView;
    }

    private PreLobbyView createPreLobbyView(AppController appController, RestController restController) {
        GameItemListView gameItemListView = new GameItemListView();
        PreLobbyView preLobbyView = new PreLobbyView(gameItemListView);

        preLobbyView.setRefreshGameListButtonAction(() -> {
            List<GameItemView> gameItemViews;
            try {
                gameItemViews = restController.getGames()
                        .stream()
                        .map(GameItemView::new)
                        .toList();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if(gameItemViews.isEmpty()) return;

            for (GameItemView gameItemView : gameItemViews) {
                gameItemView.setJoinGameButtonAction(() -> {
                    UserLobbyView userLobbyView = createUserLobbyView(preLobbyView, restController);

                    boardRoot.setCenter(userLobbyView);
                    try {
                        int gameId = gameItemView.getGame().getGameID();
                        System.out.println("Game ID: " + gameId);
                        int playerId = restController.postPlayer("Placeholder... Niko is a btich", gameId);
                        gameSession = new GameSession(gameId, playerId);
                        DataUpdater.getInstance().startLobbyPolling(() -> {
                            try {
                                List<PlayerItemView> playerItemViews = restController.getPlayers(gameId).stream().map(
                                        player -> new PlayerItemView(player.getPlayerID())).toList();
                                Platform.runLater(() -> userLobbyView.getPlayerListView().setPlayerItemViews(playerItemViews));
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }, () -> System.out.println("Jubi"));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            gameItemListView.setGameItems(gameItemViews);
        });

        preLobbyView.setCreateGameButtonAction(() -> {
            AdminLobbyView adminLobbyView = createAdminLobbyView(appController, preLobbyView);
            boardRoot.setCenter(adminLobbyView);
            Game game = new Game("Placeholder... bitch");
            System.out.println(game.getGameID());
            try {
                int gameId = restController.postGame(game);
                game.setGameId(gameId);
                int playerId = restController.postPlayer("Placeholder... Niko is a btich", gameId);
                Player player = new Player(null, 0, "Placeholder... Niko is a btich");
                player.setPlayerID(playerId);
                gameSession = new GameSession(gameId, playerId);
                DataUpdater.getInstance().startLobbyPolling(() -> {
                    try {
                        List<PlayerItemView> playerItemViews = restController.getPlayers(gameId)
                                .stream()
                                .map(player1 -> new PlayerItemView(player1.getPlayerID()))
                                        .toList();
                        Platform.runLater(() -> adminLobbyView.getPlayerListView().setPlayerItemViews(playerItemViews));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }, () -> System.out.println("Game stuff"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        return preLobbyView;
    }

    private UserLobbyView createUserLobbyView(PreLobbyView preLobbyView, RestController restController) {
        UserLobbyMap userLobbyMap = new UserLobbyMap();
        PlayerListView playerListView = new PlayerListView();
        UserLobbyBottom userLobbyBottom = new UserLobbyBottom(preLobbyView);

        UserLobbyView userLobbyView = new UserLobbyView(userLobbyBottom, userLobbyMap, playerListView);

        userLobbyBottom.setCloseButtonAction(() -> {
            boardRoot.setCenter(createPreLobbyView(new AppController(this), new ClientController()));
            DataUpdater.getInstance().stopLobbyPolling();
        });

        return userLobbyView;
    }

    public void createBoardView(GameController gameController) {
        // if present, remove old BoardView
        boardRoot.getChildren().clear();

        if (gameController != null) {
            // create and add view for new board
            BoardView boardView = new BoardView(gameController);
            boardRoot.setCenter(boardView);
        }

        stage.sizeToScene();
    }

    @Override
    public void stop() throws Exception {
        super.stop();

        // XXX just in case we need to do something here eventually;
        //     but right now the only way for the user to exit the app
        //     is delegated to the exit() method in the AppController,
        //     so that the AppController can take care of that.
    }

    public static void main(String[] args) {
        launch(args);
    }
}