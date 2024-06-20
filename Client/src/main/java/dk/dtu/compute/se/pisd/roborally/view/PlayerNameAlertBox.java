package dk.dtu.compute.se.pisd.roborally.view;

import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class PlayerNameAlertBox extends TextInputDialog {

    public PlayerNameAlertBox() {
        super();
    }

    public String getPlayerName() {
        this.setTitle("Player Name");
        this.setHeaderText("Enter your name:");
        this.setContentText("Name:");
        Optional<String> result = this.showAndWait();
        return result.orElse(null);
    }
}
