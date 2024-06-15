package dk.dtu.compute.se.pisd.roborally.view;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Represents the bottom part of the user lobby view
 * which includes the close button.
 */
public class UserLobbyBottom extends HBox {

    public UserLobbyBottom(PreLobbyView preLobbyView, BorderPane boardRoot) {
        Button closeButton = new Button("Close");
        HBox filler = new HBox();
        filler.setVisible(false);
        HBox.setHgrow(filler, Priority.ALWAYS);
        getChildren().addAll(closeButton, filler);

        closeButton.setOnAction(e -> switchToPreLobby(boardRoot, preLobbyView));
    }

    private void switchToPreLobby(BorderPane boardRoot, PreLobbyView preLobbyView) {
        boardRoot.setCenter(preLobbyView);
    }
}
