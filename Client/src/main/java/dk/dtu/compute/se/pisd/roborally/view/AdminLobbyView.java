package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.RestController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Represents the view for the admin lobby screen
 * containing the player list, map selection and the start game button.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class AdminLobbyView extends VBox {

    private final AdminLobbyMap adminLobbyMap;
    private final PlayerListView playerListView;
    private final AdminLobbyBottom adminLobbyBottom;

    //call player Item View and Player List View.
    public AdminLobbyView(PreLobbyView preLobbyView) {
        adminLobbyMap = new AdminLobbyMap();
        playerListView = new PlayerListView();
        adminLobbyBottom = new AdminLobbyBottom(preLobbyView);
        HBox filler = new HBox();
        HBox.setHgrow(filler, Priority.ALWAYS);
        Text playerListTitle = new Text("Players");
        playerListTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        VBox playerListContainer = new VBox(playerListTitle, playerListView);
        HBox content = new HBox(playerListContainer, filler, adminLobbyMap);
        getChildren().addAll(content, adminLobbyBottom);
    }

    public AdminLobbyMap getAdminLobbyMap() {
        return adminLobbyMap;
    }

    public PlayerListView getPlayerListView() {
        return playerListView;
    }

    public AdminLobbyBottom getAdminLobbyBottom() {
        return adminLobbyBottom;
    }

}



