package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.AbstractRestController;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Represents the view for the user lobby screen
 * containing the player list and the map image.
 */
public class UserLobbyView extends VBox {

    public UserLobbyView(PreLobbyView preLobbyView, BorderPane boardRoot, AbstractRestController restController) {
        UserLobbyMap userLobbyMap = new UserLobbyMap();
        PlayerListView playerListView = new PlayerListView(restController);
        UserLobbyBottom userLobbyBottom = new UserLobbyBottom(preLobbyView, boardRoot);
        HBox filler = new HBox();
        this.paddingProperty().set(new Insets(10, 10, 10, 10));
        HBox.setHgrow(filler, Priority.ALWAYS);
        Text playerListTitle = new Text("Players");
        playerListTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        VBox playerListContainer = new VBox(playerListTitle, playerListView);
        HBox content = new HBox(playerListContainer, filler, userLobbyMap);
        getChildren().addAll(content, userLobbyBottom);
    }
}
