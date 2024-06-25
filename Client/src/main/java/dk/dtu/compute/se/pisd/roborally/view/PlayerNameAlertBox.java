package dk.dtu.compute.se.pisd.roborally.view;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class PlayerNameAlertBox extends TextInputDialog {

    public PlayerNameAlertBox() {
        super();
        this.setTitle("Player Name");
        this.setHeaderText("Enter your name:");
        this.setContentText("Name:");
    }

    public String getPlayerName() {
        String playerName = null;
        while (playerName == null || playerName.trim().isEmpty()) {
            Optional<String> result = this.showAndWait();
            if (result.isPresent()) {
                playerName = result.get();
                if (playerName.trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("No name entered");
                    alert.setContentText("Please enter a name");
                    alert.showAndWait();
                }
            } else {
                break; // Cancel button clicked
            }
        }
        return playerName;
    }
}
