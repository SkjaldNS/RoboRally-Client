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
package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;

/**
 * A field for a command card that is placed on the board. The field is
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class CommandCardField extends Subject {

    public Player player;

    private CommandCard card;

    private boolean visible;

    /**
     * The constructor for the field for a command card. The field is initially
     * empty and visible.
     * @param player the player that is associated with this field
     */
    public CommandCardField(Player player) {
        this.player = player;
        this.card = null;
        this.visible = true;
    }

    public CommandCardField(){
    }

    /**
     * @return the card that is placed on this field
     */
    public CommandCard getCard() {
        return card;
    }

    /**
     * Sets the card that is placed on this field. This will notify the
     * observers of this field.
     * @param card the card to be placed on this field
     */
    public void setCard(CommandCard card) {
        if (card != this.card) {
            this.card = card;
            notifyChange();
        }
    }

    /**
     * @return whether the card on this field is visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the visibility of the card on this field. This will notify the
     * observers of this field.
     * @param visible whether the card on this field should be visible
     */
    public void setVisible(boolean visible) {
        if (visible != this.visible) {
            this.visible = visible;
            notifyChange();
        }
    }
}
