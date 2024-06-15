package dk.dtu.compute.se.pisd.roborally.view;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents the map display part of the user lobby view.
 */
public class UserLobbyMap extends VBox {

    private ImageView mapPreview;
    private Rectangle mapPreviewBackground;

    public UserLobbyMap() {
        // Placeholder for map preview
        mapPreview = new ImageView();
        mapPreview.setFitWidth(200);
        mapPreview.setFitHeight(200);
        mapPreview.setStyle("-fx-border-color: black; -fx-border-width: 10px;");

        mapPreviewBackground = new Rectangle(200, 200);
        mapPreviewBackground.setFill(Color.LIGHTGRAY);
        getChildren().addAll(mapPreviewBackground);
        this.alignmentProperty().set(Pos.CENTER_RIGHT);

        // Load the initial map image
        updateMapPreview("map_image/map_1.png");
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
}
