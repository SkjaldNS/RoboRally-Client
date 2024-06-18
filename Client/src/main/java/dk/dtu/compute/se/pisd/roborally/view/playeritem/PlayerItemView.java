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
    private final Text playerName;
    public PlayerItemView(long playerId) {
        this.playerId = playerId;
        playerName = new Text("Player " + playerId);
        getChildren().add(playerName);
    }

    public long getPlayerId() {
        return playerId;
    }

    public Text getPlayerName() {
        return playerName;
    }
}
