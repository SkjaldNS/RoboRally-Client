package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.RestController;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of player items fetched from the server.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class PlayerListView extends ScrollPane {

    private List<PlayerItemView> playerItemViews;
    private final VBox playerListContainer;
    public PlayerListView() {
        playerItemViews = new ArrayList<>();
        playerListContainer = new VBox();
        playerListContainer.getChildren().addAll(playerItemViews);
        this.setMinWidth(200);
        setContent(playerListContainer);
    }

    public void setPlayerItemViews(List<PlayerItemView> playerItemViews) {
        this.playerItemViews = playerItemViews;
        updatePlayerList();
    }

    private void updatePlayerList() {
        playerListContainer.getChildren().clear();
        playerListContainer.getChildren().addAll(playerItemViews);
    }
}
