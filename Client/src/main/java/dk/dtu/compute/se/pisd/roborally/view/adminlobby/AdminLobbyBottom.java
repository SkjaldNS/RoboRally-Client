package dk.dtu.compute.se.pisd.roborally.view.adminlobby;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Represents the bottom part of the admin lobby view
 * which includes the close and start game button.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class AdminLobbyBottom extends HBox {

    private final Button closeButton;
    private final Button startGameButton;

    /**
     * Constructor for the AdminLobbyBottom class.
     */
    public AdminLobbyBottom() {
        closeButton = new Button("Close");
        startGameButton = new Button("Start Game");
        getChildren().addAll(closeButton, startGameButton);
    }

    /**
     * @return the start game button.
     */
    public Button getStartGameButton() {
        return startGameButton;
    }

    /**
     * Sets the action of the close button.
     * @param action the action to be set.
     */
    public void setCloseButtonAction(Runnable action) {
        this.closeButton.setOnAction(e -> action.run());
    }

    /**
     * Sets the action of the start game button.
     * @param action the action to be set.
     */
    public void setStartGameButtonAction(Runnable action) {
        this.startGameButton.setOnAction(e -> action.run());
    }
}
