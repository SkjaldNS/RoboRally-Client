package dk.dtu.compute.se.pisd.roborally.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 *
 * @author Daniel Overballe Lerche
 */
public class PreLobbyView extends VBox {

    private static final String CREATE_GAME_BUTTON_MESSAGE = "Create Game";
    private Button createGameButton;

    public PreLobbyView(GameItemListView gameItemListView) {
        createGameButton = new Button(CREATE_GAME_BUTTON_MESSAGE);
        this.alignmentProperty().set(Pos.CENTER_RIGHT);
        this.paddingProperty().set(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(gameItemListView, createGameButton);

        createGameButton.setOnAction(e -> {
            gameItemListView.setGameItems(
                    List.of(
                            new GameItemView(1),
                            new GameItemView(4)
                    )
            );
        });
    }

}
