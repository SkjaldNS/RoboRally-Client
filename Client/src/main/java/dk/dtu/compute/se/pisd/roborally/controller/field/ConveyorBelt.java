/*
 *  This file is part of the initial project provided for the
 *  course "Project in Software Development (02362)" held at
 *  DTU Compute at the Technical University of Denmark.
 *
 *  Copyright (C) 2019, 2020: Ekkart Kindler, ekki@dtu.dk
 *
 *  This software is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; version 2 of the License.
 *
 *  This project is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
package dk.dtu.compute.se.pisd.roborally.controller.field;

import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import org.jetbrains.annotations.NotNull;

/**
 * The ConveyorBelt class represents a conveyor belt in the game.
 * When a player lands on a conveyor belt, a specific action occurs.
 * The specific action needs to be implemented in the doAction method.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class ConveyorBelt extends FieldAction {

    public enum Color {
        BLUE, GREEN
    }

    public enum Type {
        STRAIGHT,
        CORNER,
        JUNCTION
    }
    private Color color;

    private Heading[] heading;

    /**
     * Returns the heading array of the conveyor belt.
     * @return the heading array of the conveyor belt
     * @author Daniel Overballe Lerche, s235095@dtu.dk
     * @author Nikolaj Schæbel, s220471@dtu.dk
     */
    public Heading[] getHeadings() {
        return heading;
    }

    /**
     * Sets the heading array of the conveyor belt.
     * @param heading the heading array to set
     * @author Daniel Overballe Lerche, s235095@dtu.dk
     * @author Nikolaj Schæbel, s220471@dtu.dk
     */
    public void setHeadings(Heading[] heading) {
        if(!isValidHeading(heading)) return;
        this.heading = heading;
    }

    /**
     * Checks if the heading array contains a valid combination of values.
     * @return true if the heading array is valid, false otherwise
     * @author Daniel Overballe Lerche, s235095@dtu.dk
     * @author Nikolaj Schæbel, s220471@dtu.dk
     */
    public static boolean isValidHeading(Heading[] heading) {
        if(heading.length == 0) return false;
        if(heading.length == 2) {
            return (heading[0].ordinal() % 2) != (heading[1].ordinal() % 2);
        } else if (heading.length == 3) {
            if(heading[0].ordinal() == heading[1].ordinal()) {
                return (heading[0].ordinal() % 2) != (heading[2].ordinal() % 2);
            } else if(heading[0].opposite() == heading[1]) {
                return (heading[0].ordinal() % 2) != (heading[2].ordinal() % 2);
            }
        }
        return true;
    }

    /**
     * Returns the color of the conveyor belt.
     * @return the color of the conveyor belt
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the conveyor belt.
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Performs the action of the conveyor belt on a given space in the game.
     * The specific action needs to be implemented.
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
