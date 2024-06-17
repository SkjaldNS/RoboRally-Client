package dk.dtu.compute.se.pisd.roborally.view.adminlobby;

import dk.dtu.compute.se.pisd.roborally.view.playeritem.PlayerListView;
import dk.dtu.compute.se.pisd.roborally.view.PreLobbyView;
import javafx.geometry.Insets;
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
    public AdminLobbyView(PlayerListView playerListView, AdminLobbyMap adminLobbyMap, AdminLobbyBottom adminLobbyBottom) {
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



