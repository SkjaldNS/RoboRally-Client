package dk.dtu.compute.se.pisd.roborally.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AdminLobbyBottom extends HBox {
    public AdminLobbyBottom() {
        Button closeButton = new Button("Close");
        Button startGameButton = new Button("Start Game");
        HBox test = new HBox();
        test.setVisible(false);
        HBox.setHgrow(test, Priority.ALWAYS);
        getChildren().addAll(closeButton, test, startGameButton);
    }
}
