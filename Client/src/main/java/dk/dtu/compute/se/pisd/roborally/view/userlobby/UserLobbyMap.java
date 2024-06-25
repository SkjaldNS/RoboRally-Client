package dk.dtu.compute.se.pisd.roborally.view.userlobby;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Represents the map display part of the user lobby view.
 * @author Daniel Overballe Lerche, s235095
 * @author Asma Maryam, s230716
 */
public class UserLobbyMap extends VBox {

    private ImageView mapPreview;

    /**
     * Constructor for the UserLobbyMap class.
     */
    public UserLobbyMap() {
        // Placeholder for map preview
        mapPreview = new ImageView();
        mapPreview.setFitWidth(200);
        mapPreview.setFitHeight(200);
        mapPreview.setStyle("-fx-border-color: black; -fx-border-width: 10px;");
        this.alignmentProperty().set(Pos.CENTER_RIGHT);
    }

    /**
     * Updates the map preview based on the board id.
     * @param boardId The id of the board to display.
     */
    public void updateMap(int boardId) {
        switch (boardId) {
            case 0:
                updateMapPreview("map_image/map_1.png");
                break;
            case 1:
                updateMapPreview("map_image/map_2.png");
                break;
            default:
                updateMapPreview("map_image/map_1.png");
                break;
        }
    }

    /**
     * Updates the map preview based on the image path.
     * @param imagePath The path of the image to display.
     */
    private void updateMapPreview(String imagePath) {
        try {
            // Correctly load the image from the resources folder
            Image mapImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath));

            // Remove the placeholder and set the image
            if (!getChildren().contains(mapPreview)) {
                getChildren().add(mapPreview);
            }
            mapPreview.setImage(mapImage);
        } catch (Exception e) {
            System.err.println("Could not load image: " + imagePath);
        }
    }
}
