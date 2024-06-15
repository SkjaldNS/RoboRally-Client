package dk.dtu.compute.se.pisd.roborally.view;

import dk.dtu.compute.se.pisd.roborally.controller.RestController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Represents the bottom part of the admin lobby view
 * which includes the close and start game button.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class AdminLobbyBottom extends HBox {

    private final Button closeButton;
    private final Button startGameButton;

    public AdminLobbyBottom(PreLobbyView preLobbyView) {
        closeButton = new Button("Close");
        startGameButton = new Button("Start Game");
        HBox test = new HBox();
        test.setVisible(false);
        HBox.setHgrow(test, Priority.ALWAYS);
        getChildren().addAll(closeButton, test, startGameButton);
    }

    public Button getCloseButton() {
        return closeButton;
    }

    public Button getStartGameButton() {
        return startGameButton;
    }
}
