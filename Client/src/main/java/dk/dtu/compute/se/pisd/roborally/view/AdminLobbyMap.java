package dk.dtu.compute.se.pisd.roborally.view;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents the map selection part of the admin lobby view
 * which includes a combobox for selecting a map and a preview of the selected map.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class AdminLobbyMap extends VBox {
    public AdminLobbyMap() {
        ComboBox<String> mapSelection = new ComboBox<>();
        mapSelection.getItems().addAll("Map 1");
        mapSelection.setPromptText("Select Map");

        // Placeholder for map preview
        ImageView mapPreview = new ImageView();
        mapPreview.setFitWidth(200);
        mapPreview.setFitHeight(200);
        mapPreview.setStyle("-fx-border-color: black; -fx-border-width: 10px;");

        Rectangle mapPreviewBackground = new Rectangle(200, 200);
        mapPreviewBackground.setFill(Color.LIGHTGRAY);
        getChildren().addAll(mapSelection, mapPreviewBackground);
        this.alignmentProperty().set(Pos.CENTER_RIGHT);
    }
}
