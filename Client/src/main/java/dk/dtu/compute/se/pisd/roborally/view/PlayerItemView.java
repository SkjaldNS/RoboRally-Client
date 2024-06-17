package dk.dtu.compute.se.pisd.roborally.view;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Represents a single player item in the player list view.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class PlayerItemView extends HBox {

    private Text playerName;
    public PlayerItemView(int playerId) {
        playerName = new Text("Player " + playerId);
        getChildren().add(playerName);
    }

}
