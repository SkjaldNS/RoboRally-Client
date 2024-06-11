package dk.dtu.compute.se.pisd.roborally.view;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class PlayerItemView extends HBox {

    private Text playerName;
    public PlayerItemView(int playerId) {
        playerName = new Text("Player " + playerId);
        getChildren().add(playerName);
    }

}
