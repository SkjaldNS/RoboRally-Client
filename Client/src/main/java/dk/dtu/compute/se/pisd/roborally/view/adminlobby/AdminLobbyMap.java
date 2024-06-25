package dk.dtu.compute.se.pisd.roborally.view.adminlobby;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;

/**
 * Represents the map selection part of the admin lobby view
 * which includes a combobox for selecting a map and a preview of the selected map.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class AdminLobbyMap extends VBox {

    private final ImageView mapPreview;
    private final ComboBox<String> mapSelection;

    /**
     * Constructor for AdminLobbyMap
     */
    public AdminLobbyMap() {
        mapSelection = new ComboBox<>();
        mapSelection.getItems().addAll("Risky Crossing", "Fractionation");
        mapSelection.setValue("Risky Crossing");

        // Placeholder for map preview
        mapPreview = new ImageView();
        mapPreview.setFitWidth(200);
        mapPreview.setFitHeight(200);
        mapPreview.setStyle("-fx-border-color: black; -fx-border-width: 10px;");

        getChildren().addAll(mapSelection);
        this.alignmentProperty().set(Pos.CENTER_RIGHT);
        updateMapPreview("map_image/map_1.png");
        // Add event handler for ComboBox selection changes
        mapSelection.setOnAction(event -> {
            String selectedMap = mapSelection.getValue();
            if ("Risky Crossing".equals(selectedMap)) {
                updateMapPreview("map_image/map_1.png");
            } else if ("Fractionation".equals(selectedMap)) {
                updateMapPreview("map_image/map_2.png");
            }
        });
    }

    /**
     * @return the selected map id
     */
    public int getSelectedMapId() {
        String selectedMap = mapSelection.getValue();
        if ("Risky Crossing".equals(selectedMap)) {
            return 0;
        } else if ("Fractionation".equals(selectedMap)) {
            return 1;
        }
        return -1;
    }

    /**
     * Updates the map preview image based on the selected map
     * @param imagePath the path to the image
     */
    private void updateMapPreview(String imagePath) {
        try {
            // Correctly load the image from the resources folder
            Image mapImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));

            // Remove the placeholder and set the image
            if(!getChildren().contains(mapPreview)) {
                getChildren().add(mapPreview);
            }
            mapPreview.setImage(mapImage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.err.println("Could not load image: " + imagePath);
        }
    }

    /**
     * @return the map preview image view
     */
    public ImageView getMapPreview() {
        return mapPreview;
    }
}