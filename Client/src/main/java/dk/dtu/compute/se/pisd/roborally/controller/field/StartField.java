package dk.dtu.compute.se.pisd.roborally.controller.field;

import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Space;

/**
 * The StartField class represents the starting field in the game.
 * When a player lands on the start field, a specific action occurs.
 * The specific action needs to be implemented in the doAction method.
 *
 * @author Daniel Overballe Lerche, s235095@dtu.dk
 * @author Nikolaj Schæbel, s220471@dtu.dk
 */
public class StartField extends FieldAction {

    /**
     * Performs the action of the start field on a given space in the game.
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
