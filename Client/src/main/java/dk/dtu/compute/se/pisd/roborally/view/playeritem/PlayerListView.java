package dk.dtu.compute.se.pisd.roborally.view.playeritem;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.controller.RestController;
import dk.dtu.compute.se.pisd.roborally.model.DataUpdater;
import dk.dtu.compute.se.pisd.roborally.view.ViewObserver;
import javafx.geometry.Pos;
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

    /**
     * Constructor for the PlayerListView class.
     */
    public PlayerListView() {
        playerItemViews = new ArrayList<>();
        playerListContainer = new VBox();
        playerListContainer.alignmentProperty().set(Pos.CENTER_RIGHT);
        playerListContainer.getChildren().addAll(playerItemViews);
        this.setMinWidth(200);
        this.setMinHeight(200);
        setContent(playerListContainer);
    }

    /**
     * Sets the player item views.
     * @param playerItemViews The player item views to set.
     */
    public void setPlayerItemViews(List<PlayerItemView> playerItemViews) {
        this.playerItemViews = playerItemViews;
        updatePlayerList();
    }

    /**
     * Updates the player list.
     */
    private void updatePlayerList() {
        playerListContainer.getChildren().clear();
        playerListContainer.getChildren().addAll(playerItemViews);
    }
}
