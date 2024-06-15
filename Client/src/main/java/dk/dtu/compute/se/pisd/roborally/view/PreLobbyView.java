package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.AbstractRestController;
import dk.dtu.compute.se.pisd.roborally.model.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents the view for the pre-lobby screen
 * containing the game item list and the create game button.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class PreLobbyView extends HBox {

    private static final String CREATE_GAME_BUTTON_MESSAGE = "Create Game";
    private static final String GAME_ITEM_LIST_TITLE = "Games";
    private static final String GAME_ITEM_LIST_REFRESH_MESSAGE = "Refresh Game List";
    private final GameItemListView gameItemListView;
    private final Button createGameButton;
    private final Button refreshGameListButton;
    private final Text gameItemListTitle;
    private final BorderPane boardRoot;
    private final AbstractRestController restController;

    public PreLobbyView(BorderPane boardRoot, AbstractRestController restController) {
        this.boardRoot = boardRoot;
        this.restController = restController;
        gameItemListView = new GameItemListView();

        // Title for the game item list
        gameItemListTitle = new Text(GAME_ITEM_LIST_TITLE);
        gameItemListTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        // Refresh button
        refreshGameListButton = new Button(GAME_ITEM_LIST_REFRESH_MESSAGE);

        // Create button
        createGameButton = new Button(CREATE_GAME_BUTTON_MESSAGE);
        createGameButton.setMaxSize(400, 100);
        createGameButton.setAlignment(Pos.CENTER_RIGHT);

        VBox gameItemListViewContainer = new VBox(10, refreshGameListButton, gameItemListTitle, gameItemListView);
        gameItemListViewContainer.setPadding(new Insets(10));

        this.setPadding(new Insets(10, 10, 10, 10));
        HBox filler = new HBox();
        HBox.setHgrow(filler, Priority.ALWAYS);
        this.getChildren().addAll(gameItemListViewContainer, filler, createGameButton);

        createGameButton.setOnAction(e -> {
            restController.createGame();
            switchToAdminLobbyView();
        });

        refreshGameListButton.setOnAction(e -> loadGameItems());

        // Initial load of game items
        loadGameItems();
    }

    private void loadGameItems() {
        List<Game> games = restController.getGames();
        List<GameItemView> gameItemViews = games.stream()
                .map(game -> new GameItemView(game, restController, () -> switchToUserLobbyView(game)))
                .collect(Collectors.toList());
        gameItemListView.setGameItems(gameItemViews);
    }

    private void switchToAdminLobbyView() {
        boardRoot.setCenter(new AdminLobbyView(this, boardRoot, restController));
    }

    private void switchToUserLobbyView(Game game) {
        boardRoot.setCenter(new UserLobbyView(this, boardRoot, restController));
    }
}
