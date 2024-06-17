package dk.dtu.compute.se.pisd.roborally.view.adminlobby;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
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

    private final ImageView mapPreview;
    private final Rectangle mapPreviewBackground;
    public AdminLobbyMap() {
        ComboBox<String> mapSelection = new ComboBox<>();
        mapSelection.getItems().addAll("Map 1");
        mapSelection.setPromptText("Select Map");

        // Placeholder for map preview
        mapPreview = new ImageView();
        mapPreview.setFitWidth(200);
        mapPreview.setFitHeight(200);
        mapPreview.setStyle("-fx-border-color: black; -fx-border-width: 10px;");

        mapPreviewBackground = new Rectangle(200, 200);
        mapPreviewBackground.setFill(Color.LIGHTGRAY);
        getChildren().addAll(mapSelection, mapPreviewBackground);
        this.alignmentProperty().set(Pos.CENTER_RIGHT);

        // Add event handler for ComboBox selection changes
        mapSelection.setOnAction(event -> {
            String selectedMap = mapSelection.getValue();
            if ("Map 1".equals(selectedMap)) {
                updateMapPreview("map_image/map_1.png");
            }
        });
    }
    private void updateMapPreview(String imagePath) {
        try {
            // Correctly load the image from the resources folder
            Image mapImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));

            // Remove the placeholder and set the image
            getChildren().remove(mapPreviewBackground);
            getChildren().add(mapPreview);
            mapPreview.setImage(mapImage);
        } catch (Exception e) {
            System.err.println("Could not load image: " + imagePath);
        }
    }

    public ImageView getMapPreview() {
        return mapPreview;
    }
}