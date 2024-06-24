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
import dk.dtu.compute.se.pisd.roborally.model.Space;

/**
 * The FieldAction class represents an action that can be performed on a field in the game.
 * It is an abstract class, meaning it cannot be instantiated directly.
 * Instead, it must be subclassed by other classes that provide an implementation for the doAction method.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public abstract class FieldAction {

    /**
     * Performs an action on a given space in the game.
     *
     * @param gameController the game controller
     * @param space the space on which the action is performed
     * @return true if the action was successful, false otherwise
     */
    public abstract boolean doAction(GameController gameController, Space space);
}
