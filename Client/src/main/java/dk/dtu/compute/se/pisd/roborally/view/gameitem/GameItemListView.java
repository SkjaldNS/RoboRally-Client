package dk.dtu.compute.se.pisd.roborally.view.gameitem;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of game items fetched from the server.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class GameItemListView extends ScrollPane {

    private final VBox gameItemContainer;
    private final List<GameItemView> gameItems;

    /**
     * Constructor for the GameItemListView class.
     */
    public GameItemListView() {
        gameItemContainer = new VBox();
        gameItemContainer.alignmentProperty().set(Pos.CENTER_RIGHT);
        this.setMinWidth(200);
        this.setMinHeight(200);
        this.setContent(gameItemContainer);
        gameItems = new ArrayList<>();
    }

    /**
     * Sets the game items to be displayed in the list.
     * @param gameItems The game items to be displayed.
     */
    public void setGameItems(List<GameItemView> gameItems) {
        this.gameItems.clear();
        this.gameItems.addAll(gameItems);
        updateGameItems();
    }

    /**
     * Updates the game items displayed in the list.
     */
    private void updateGameItems() {
        gameItemContainer.getChildren().clear();
        for (GameItemView gameItem : gameItems) {
            gameItemContainer.getChildren().add(gameItem);
        }
    }
}
