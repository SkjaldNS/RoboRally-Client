package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.model.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the view for the pre-lobby screen
 * containing the game item list and the create game button.
 * @author Daniel Overballe Lerche
 */
public class PreLobbyView extends HBox {

    private static final String CREATE_GAME_BUTTON_MESSAGE = "Create Game";
    private static final String GAME_ITEM_LIST_TITLE = "Games";
    private static final String GAME_ITEM_LIST_REFRESH_MESSAGE = "Refresh Game List";
    private final GameItemListView gameItemListView;
    private final Button createGameButton;
    private final Button refreshGameListButton;
    private final Text gameItemListTitle;

    // TODO - Give a controller to the view that handles game fetching
    public PreLobbyView(BorderPane boardRoot) {
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

        VBox gameItemListViewContainer = new VBox(refreshGameListButton, gameItemListTitle, gameItemListView);

        this.paddingProperty().set(new Insets(10, 10, 10, 10));
        HBox filler = new HBox();
        HBox.setHgrow(filler, Priority.ALWAYS);
        this.getChildren().addAll(gameItemListViewContainer, filler, createGameButton);

        createGameButton.setOnAction(e -> {
            boardRoot.setCenter(new AdminLobbyView());
        });

        refreshGameListButton.setOnAction(e -> {
            gameItemListView.setGameItems(
                    new ArrayList<>()
                 // TODO - Implement fetching of games
            );
        });
    }
}
