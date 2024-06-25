package dk.dtu.compute.se.pisd.roborally.view.userlobby;

import dk.dtu.compute.se.pisd.roborally.view.PreLobbyView;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/**
 * Represents the bottom part of the user lobby view
 * which includes the close button.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class UserLobbyBottom extends HBox {

    private final Button closeButton;

    /**
     * Constructor for the UserLobbyBottom class
     */
    public UserLobbyBottom(PreLobbyView preLobbyView) {
        closeButton = new Button("Close");
        HBox filler = new HBox();
        filler.setVisible(false);
        HBox.setHgrow(filler, Priority.ALWAYS);
        getChildren().addAll(closeButton, filler);
    }

    /**
     * Sets the action of the close button
     * @param action the action to be set
     */
    public void setCloseButtonAction(Runnable action) {
        this.closeButton.setOnAction(e -> action.run());
    }
}
