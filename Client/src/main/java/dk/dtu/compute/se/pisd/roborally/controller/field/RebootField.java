package dk.dtu.compute.se.pisd.roborally.controller.field;

import com.google.gson.annotations.Expose;
import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;

/**
 * The RebootField class represents a reboot field in the game.
 * When a player lands on a reboot field, a specific action occurs.
 * The specific action needs to be implemented in the doAction method.
 *
 * @author Daniel Overballe Lerche, s235095@dtu.dk
 * @author Nikolaj Schæbel, s220471@dtu.dk
 */
public class RebootField extends FieldAction {
    @Expose
    Heading heading;

    /**
     * Returns the heading of the reboot field.
     *
     * @return The heading of the reboot field
     *
     * @author Daniel Overballe Lerche, s235095@dtu.dk
     * @author Nikolaj Schæbel, s220471@dtu.dk
     */
    public Heading getHeading() {
        return heading;
    }

    /**
     * Sets the heading of the reboot field.
     *
     * @param heading The heading of the reboot field
     *
     * @author Daniel Overballe Lerche, s235095@dtu.dk
     * @author Nikolaj Schæbel, s220471@dtu.dk
     */
    public void setHeading(Heading heading) {
        this.heading = heading;
    }

    /**
     * Performs the action of the reboot field on a given space in the game.
     * The specific action needs to be implemented.
     *
     * @param gameController the game controller
     * @param space the space on which the action is performed
     * @return true if the action was successful, false otherwise
     */
    @Override
    public boolean doAction(GameController gameController, Space space) {
        return false;
    }
}