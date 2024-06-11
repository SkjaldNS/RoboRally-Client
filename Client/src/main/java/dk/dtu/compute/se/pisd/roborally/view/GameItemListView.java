package dk.dtu.compute.se.pisd.roborally.view;

import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Overballe Lerche
 */
public class GameItemListView extends ScrollPane {

    private final VBox gameItemContainer;
    private List<GameItemView> gameItems;

    public GameItemListView() {
        gameItemContainer = new VBox();
        gameItemContainer.alignmentProperty().set(Pos.CENTER_RIGHT);
        this.setMaxWidth(200);
        this.setMaxHeight(200);
        gameItems = new ArrayList<>();
        this.setContent(gameItemContainer);
    }

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
