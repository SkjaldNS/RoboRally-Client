package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.AbstractRestController;
import dk.dtu.compute.se.pisd.roborally.model.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Represents a single game item in the game item list view.
 *
 */
public class GameItemView extends HBox {

    private static final String JOIN_BUTTON_MESSAGE = "Join Game";
    private final Button joinGameButton;
    private final Text gameNameText;

    public GameItemView(Game game, AbstractRestController restController, Runnable onClickAction) {
        joinGameButton = new Button(JOIN_BUTTON_MESSAGE);
        gameNameText = new Text("Game " + game.getGameId());
        HBox.setMargin(gameNameText, new Insets(0, 10, 0, 0));
        paddingProperty().set(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(gameNameText, joinGameButton);
        this.setAlignment(Pos.CENTER_RIGHT);

        joinGameButton.setOnAction(e -> {
            restController.joinGame(game.getGameId());
            onClickAction.run();
        });

        this.setOnMouseClicked(e -> onClickAction.run());
    }
}