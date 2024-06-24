package dk.dtu.compute.se.pisd.roborally.view.userlobby;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.controller.RestController;
import dk.dtu.compute.se.pisd.roborally.view.ViewObserver;
import dk.dtu.compute.se.pisd.roborally.view.playeritem.PlayerListView;
import dk.dtu.compute.se.pisd.roborally.view.PreLobbyView;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Represents the view for the user lobby screen
 * containing the player list and the map image.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class UserLobbyView extends VBox {

    private final PlayerListView playerListView;
    private final UserLobbyMap userLobbyMap;

    public UserLobbyView(UserLobbyBottom userLobbyBottom, UserLobbyMap userLobbyMap, PlayerListView playerListView) {
        this.playerListView = playerListView;
        this.userLobbyMap = userLobbyMap;
        HBox filler = new HBox();
        this.paddingProperty().set(new Insets(10, 10, 10, 10));
        HBox.setHgrow(filler, Priority.ALWAYS);
        Text playerListTitle = new Text("Players");
        playerListTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        VBox playerListContainer = new VBox(playerListTitle, playerListView);
        HBox content = new HBox(playerListContainer, filler, userLobbyMap);
        getChildren().addAll(content, userLobbyBottom);
    }

    public PlayerListView getPlayerListView() {
        return playerListView;
    }

    public UserLobbyMap getUserLobbyMap() {
        return userLobbyMap;
    }

}
