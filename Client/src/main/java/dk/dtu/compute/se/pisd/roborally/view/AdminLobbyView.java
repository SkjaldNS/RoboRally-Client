package dk.dtu.compute.se.pisd.roborally.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Represents the view for the admin lobby screen
 * containing the player list, map selection and the start game button.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class AdminLobbyView extends VBox {

    //call player Item View and Player List View.
    public AdminLobbyView() {
        AdminLobbyMap adminLobbyMap = new AdminLobbyMap();
        PlayerListView playerListView = new PlayerListView();
        AdminLobbyBottom adminLobbyBottom = new AdminLobbyBottom();
        HBox filler = new HBox();
        HBox.setHgrow(filler, Priority.ALWAYS);
        HBox content = new HBox(playerListView, filler, adminLobbyMap);
        getChildren().addAll(content, adminLobbyBottom);
    }

}



