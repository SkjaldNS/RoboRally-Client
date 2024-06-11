package dk.dtu.compute.se.pisd.roborally.view;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

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



