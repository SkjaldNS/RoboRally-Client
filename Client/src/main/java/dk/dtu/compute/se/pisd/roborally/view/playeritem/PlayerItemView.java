package dk.dtu.compute.se.pisd.roborally.view.playeritem;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Represents a single player item in the player list view.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class PlayerItemView extends HBox {

    private final long playerId;
    private final String playerName;
    private final Text playerNameText;
    public PlayerItemView(long playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
        playerNameText = new Text(playerName);
        getChildren().add(playerNameText);
    }

    public long getPlayerId() {
        return playerId;
    }

    public Text getPlayerNameText() {
        return playerNameText;
    }
}
