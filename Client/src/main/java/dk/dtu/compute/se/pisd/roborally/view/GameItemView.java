package dk.dtu.compute.se.pisd.roborally.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 *
 * @author Daniel Overballe Lerche
 */
public class GameItemView extends HBox {

    private static final String JOIN_BUTTON_MESSAGE = "Join Game";
    private final int gameId;
    private final Button joinGameButton;
    private final Text gameNameText;

    public GameItemView(int gameId) {
        this.gameId = gameId;
        joinGameButton = new Button(JOIN_BUTTON_MESSAGE);
        gameNameText = new Text("Game " + gameId);
        HBox.setMargin(gameNameText, new Insets(0, 10, 0, 0));
        paddingProperty().set(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(gameNameText, joinGameButton);
        this.alignmentProperty().set(Pos.CENTER_RIGHT);
    }

    public int getGameId() {
        return gameId;
    }
}
