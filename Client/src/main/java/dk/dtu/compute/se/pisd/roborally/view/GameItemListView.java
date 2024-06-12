package dk.dtu.compute.se.pisd.roborally.view;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of game items fetched from the server.
 * @author Daniel Overballe Lerche
 */
public class GameItemListView extends ScrollPane {

    private final VBox gameItemContainer;
    private List<GameItemView> gameItems;

    public GameItemListView() {
        gameItemContainer = new VBox();
        gameItemContainer.alignmentProperty().set(Pos.CENTER_RIGHT);
        this.setMinWidth(200);
        this.setMinHeight(400);
        gameItems = new ArrayList<>();
        this.setContent(gameItemContainer);
    }

    /**
     * Sets the game items to be displayed in the list.
     * @param gameItems the game items to be displayed
     */
    public void setGameItems(List<GameItemView> gameItems) {
        this.gameItems = gameItems;
        updateGameItems();
    }

    private void updateGameItems() {
        gameItemContainer.getChildren().clear();
        for (GameItemView gameItem : gameItems) {
            gameItemContainer.getChildren().add(gameItem);
        }
    }
}
