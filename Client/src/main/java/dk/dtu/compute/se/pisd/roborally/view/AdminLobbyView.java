package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.AbstractRestController;
import javafx.geometry.Insets;
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

    //call player Item View and Player List View.
    public AdminLobbyView(PreLobbyView preLobbyView, BorderPane boardRoot, AbstractRestController restController) {
        AdminLobbyMap adminLobbyMap = new AdminLobbyMap();
        PlayerListView playerListView = new PlayerListView(restController);
        AdminLobbyBottom adminLobbyBottom = new AdminLobbyBottom(preLobbyView, boardRoot);
        HBox filler = new HBox();
        this.paddingProperty().set(new Insets(10, 10, 10, 10));
        HBox.setHgrow(filler, Priority.ALWAYS);
        Text playerListTitle = new Text("Players");
        playerListTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        VBox playerListContainer = new VBox(playerListTitle, playerListView);
        HBox content = new HBox(playerListContainer, filler, adminLobbyMap);
        getChildren().addAll(content, adminLobbyBottom);
    }

}



