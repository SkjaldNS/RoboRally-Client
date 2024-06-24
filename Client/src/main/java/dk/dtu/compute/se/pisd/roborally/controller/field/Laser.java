package dk.dtu.compute.se.pisd.roborally.controller.field;

import com.google.gson.annotations.Expose;
import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import org.jetbrains.annotations.NotNull;

/**
 * The Laser class represents a laser in the game.
 * The type can be SINGLE, DOUBLE, or TRIPPLE.
 * The heading represents the direction of the laser.
 *
 * @author Nikolaj Schæbel / s220471
 */
public class Laser extends FieldAction {

    public enum Type {
        SINGLE, DOUBLE, TRIPPLE;
    }
    private Type type;
    private Heading heading;

    /**
     * Returns the heading of the laser.
     *
     * @return the heading of the laser
     */
    public Heading getHeading() {
        return heading;
    }

    /**
     * Sets the heading of the laser.
     *
     * @param heading the new heading of the laser
     */
    public void setHeading(Heading heading) {
        this.heading = heading;
    }

    /**
     * Performs the action of the laser on a given space in the game.
     *
     * @param gameController the game controller
     * @param space the space on which the action is performed
     * @return true if the action was successful, false otherwise
     */
    @Override
    public boolean doAction(@NotNull GameController gameController, @NotNull Space space) {
        // TODO needs to be implemented
        return false;
    }

}