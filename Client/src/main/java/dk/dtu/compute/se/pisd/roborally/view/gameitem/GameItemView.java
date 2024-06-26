package dk.dtu.compute.se.pisd.roborally.view.gameitem;

import dk.dtu.compute.se.pisd.roborally.model.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Represents a single game item in the game item list view.
 *@author Daniel Overballe Lerche, s235095
 *@author Asma Maryam, s230716
 */
public class GameItemView extends HBox {

    private static final String JOIN_BUTTON_MESSAGE = "Join Game";
    private final Button joinGameButton;
    private final Text gameNameText;
    private final Game game;

    /**
     * Constructor for the GameItemView class.
     */
    public GameItemView(Game game) {
        this.game = game;
        joinGameButton = new Button(JOIN_BUTTON_MESSAGE);
        gameNameText = new Text(game.getGameName());
        HBox.setMargin(gameNameText, new Insets(0, 10, 0, 0));
        paddingProperty().set(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(gameNameText, joinGameButton);
        this.setAlignment(Pos.CENTER_RIGHT);
    }

    /**
     * Sets the action of the join game button.
     * @param action The action to be set.
     */
    public void setJoinGameButtonAction(Runnable action) {
        this.joinGameButton.setOnAction(e -> action.run());
    }

    /**
     * @return The game associated with the game item view.
     */
    public Game getGame() {
        return game;
    }
}
