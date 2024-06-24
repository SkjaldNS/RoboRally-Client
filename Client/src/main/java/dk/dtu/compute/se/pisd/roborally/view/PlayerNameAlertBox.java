package dk.dtu.compute.se.pisd.roborally.view;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class PlayerNameAlertBox extends TextInputDialog {

    /**
     * Constructor for the PlayerNameAlertBox
     */
    public PlayerNameAlertBox() {
        super();
    }

    /**
     * Method to get the player name
     * @return the player name
     */
    public String getPlayerName() {
        this.setTitle("Player Name");
        this.setHeaderText("Enter your name:");
        this.setContentText("Name:");
        Optional<String> result = this.showAndWait();
        return result.orElse(null);
    }
}
