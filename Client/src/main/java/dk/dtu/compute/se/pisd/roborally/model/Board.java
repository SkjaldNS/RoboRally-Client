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

import com.google.gson.annotations.Expose;
import dk.dtu.compute.se.pisd.designpatterns.observer.Subject;
import dk.dtu.compute.se.pisd.roborally.controller.field.Antenna;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static dk.dtu.compute.se.pisd.roborally.model.Phase.INITIALISATION;

/**
 * This class represents the board of the game. The board is a rectangular
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class Board extends Subject {

    public int width;

    private int MAX_NUMBER_OF_CARDS;

    private int currentNumberOfCards;

    private int totalMoves = 0;

    public int height;

    private Integer gameId;

    private Space[][] spaces;

    private final List<Player> players = new ArrayList<>();

    private Player current;

    private Phase phase = INITIALISATION;

    private Space antennaSpace;

    private final List<Space> startSpaces = new ArrayList<>();

    private int step = 0;

    private boolean stepMode;

    private Player local;

    // Empty constructor for GSON since it will give errors without it
    public Board() {}

    /**
     * Constructor for the board.
     * @param width the width of the board
     * @param height the height of the board
     */
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        spaces = new Space[width][height];
        for (int x = 0; x < width; x++) {
            for(int y = 0; y < height; y++) {
                Space space = new Space(this, x, y);
                spaces[x][y] = space;
            }
        }
        this.stepMode = false;
        this.MAX_NUMBER_OF_CARDS =  getPlayersNumber() * 9;
        this.currentNumberOfCards = MAX_NUMBER_OF_CARDS;
    }

    /**
     * Returns the game ID.
     *
     * @return the game ID
     */
    public Integer getGameId() {
        return gameId;
    }

    /**
     * Sets the game ID. If the game already has an ID, an exception is thrown.
     *
     * @param gameId the game ID to set
     * @throws IllegalStateException if the game already has an ID
     */
    public void setGameId(int gameId) {
        if (this.gameId == null) {
            this.gameId = gameId;
        } else {
            if (!this.gameId.equals(gameId)) {
                throw new IllegalStateException("A game with a set id may not be assigned a new id!");
            }
        }
    }

    /**
     * Sets the local player.
     *
     * @param playerLocal the local player to set
     */
    public void setLocalPlayer(Player playerLocal) {
        this.local = playerLocal;
    }

    /**
     * Returns the start spaces of the board.
     *
     * @return the start spaces of the board
     */
    public List<Space> getStartSpaces() {
        return startSpaces;
    }

    /**
     * Adds a start space to the board. If the space is already a start space, nothing happens.
     *
     * @param space the start space to add
     */
    public void addStartSpace(Space space) {
        if (!startSpaces.contains(space)) {
            startSpaces.add(space);
        }
    }

    /**
     * Returns the space which holds the antenna.
     * @return the space which holds the antenna
     * @author Daniel Overballe Lerche, s235095@dtu.dk
     * @author Nikolaj Schæbel, s220471@dtu.dk
     */
    public Space getAntennaSpace() {
        return antennaSpace;
    }

    /**
     * Sets the space which holds the antenna.
     * @param antennaSpace the space which holds the antenna
     * @author Daniel Overballe Lerche, s235095@dtu.dk
     * @author Nikolaj Schæbel, s220471@dtu.dk
     */
    public void setAntennaSpace(Space antennaSpace) {
        this.antennaSpace = antennaSpace;
    }

    /**
     * Returns the current number of cards.
     *
     * @return the current number of cards
     */
    public int getCurrentNumberOfCards() {
        return currentNumberOfCards;
    }

    /**
     * Decreases the current number of cards by one.
     */
    public void useCard() {
        currentNumberOfCards--;
    }


    /**
     * Returns the space at the given coordinates on the board.
     * If the coordinates are out of bounds, null is returned.
     *
     * @param x the x-coordinate of the space
     * @param y the y-coordinate of the space
     * @return the space at the given coordinates, or null if out of bounds
     */
    public Space getSpace(int x, int y) {
        if (x >= 0 && x < width &&
                y >= 0 && y < height) {
            return spaces[x][y];
        } else {
            return null;
        }
    }

    public int getPlayersNumber() {
        return players.size();
    }

    /**
     * Adds a player to the board. If the player is already on the board,
     * nothing happens.
     *
     * @param player the player to add
     */
    public void addPlayer(@NotNull Player player) {
        if (player.board == this && !players.contains(player)) {
            players.add(player);
            notifyChange();
        }
    }

    /**
     * Returns the player at the given index. If the index is out of bounds,
     * null is returned.
     *
     * @param i the index of the player
     * @return the player at the given index, or null if out of bounds
     */
    public Player getPlayer(int i) {
        if (i >= 0 && i < players.size()) {
            return (Player) players.get(i);
        } else {
            return null;
        }
    }

    /**
     * Returns the current player of the game.
     *
     * @return the current player of the game
     */
    public Player getCurrentPlayer() {
        return current;
    }

    /**
     * Sets the current player of the game. If the given player is not on the board,
     * nothing happens.
     *
     * @param player the player to set as current
     */
    public void setCurrentPlayer(Player player) {
        if (player != this.current && players.contains(player)) {
            this.current = player;
            notifyChange();
        }
    }

    /**
     * Returns the local player.
     *
     * @return the local player
     */
    public Player getLocalPlayer() { return local; }

    /**
     * Returns the current phase of the game.
     *
     * @return the current phase of the game
     */
    public Phase getPhase() {
        return phase;
    }

    /**
     * Sets the current phase of the game.
     *
     * @param phase the phase to set as current
     */
    public void setPhase(Phase phase) {
        if (phase != this.phase) {
            this.phase = phase;
            notifyChange();
        }
    }

    /**
     * Returns the current step of the game.
     *
     * @return the current step of the game
     */
    public int getStep() {
        return step;
    }

    /**
     * Sets the current step of the game.
     *
     * @param step the step to set as current
     */
    public void setStep(int step) {
        if (step != this.step) {
            this.step = step;
            notifyChange();
        }
    }

    /**
     * Returns whether the game is in step mode.
     *
     * @return true if the game is in step mode, false otherwise
     */
    public boolean isStepMode() {
        return stepMode;
    }

    /**
     * Sets whether the game is in step mode.
     *
     * @param stepMode true to set the game to step mode, false otherwise
     */
    public void setStepMode(boolean stepMode) {
        if (stepMode != this.stepMode) {
            this.stepMode = stepMode;
            notifyChange();
        }
    }

    /**
     * Returns the index of the given player. If the player is not on the board,
     * -1 is returned.
     *
     * @param player the player whose index to return
     * @return the index of the player, or -1 if the player is not on the board
     */
    public int getPlayerNumber(@NotNull Player player) {
        if (player.board == this) {
            return players.indexOf(player);
        } else {
            return -1;
        }
    }

    /**
     * Sets the list of players on the board to the given list.
     *
     * @param players the list of players to set
     */
    public void setPlayers(List<Player> players) {
        this.players.clear();
        this.players.addAll(players);
        notifyChange();
    }

    /**
     * Returns the neighbour of the given space of the board in the given heading.
     * The neighbour is returned only, if it can be reached from the given space
     * (no walls or obstacles in either of the involved spaces); otherwise,
     * null will be returned.
     *
     * @param space the space for which the neighbour should be computed
     * @param heading the heading of the neighbour
     * @return the space in the given direction; null if there is no (reachable) neighbour
     */
    public Space getNeighbour(@NotNull Space space, @NotNull Heading heading) {
        if (space.getWalls().contains(heading)) {
            return null;
        }
        // TODO needs to be implemented based on the actual spaces
        //      and obstacles and walls placed there. For now it,
        //      just calculates the next space in the respective
        //      direction in a cyclic way.

        // XXX another option (not for now) would be that null represents a hole
        //     or the edge of the board in which the players can fall

        int x = space.x;
        int y = space.y;
        switch (heading) {
            case SOUTH:
                y = (y + 1) % height;
                break;
            case WEST:
                x = (x + width - 1) % width;
                break;
            case NORTH:
                y = (y + height - 1) % height;
                break;
            case EAST:
                x = (x + 1) % width;
                break;
        }

        if(x < 0 || x >= width || y < 0 || y >= height) {
            return null;
        }
        Heading reverse = Heading.values()[(heading.ordinal() + 2)% Heading.values().length];
        Space result = getSpace(x, y);
        if (result != null) {
            if (result.getWalls().contains(reverse)) {
                return null;
            }
        }
        return result;
    }

    /**
     * Returns a status message for the game, containing the current phase, the current player,
     * the current step, and the total number of moves.
     *
     * @return a status message for the game
     */
    public String getStatusMessage() {
        return "Phase: " + getPhase().name() +
                ", Player = " + getCurrentPlayer().getName() +
                ", Step: " + getStep() +
                ", Total Moves:" + getTotalMoves();
    }

    /**
     * Returns the total number of moves.
     *
     * @return the total number of moves
     */
    public int getTotalMoves() {
        return totalMoves;
    }

    /**
     * Sets the total number of moves.
     *
     * @param totalMoves the total number of moves to set
     */
    public void setTotalMoves(int totalMoves) {
        this.totalMoves = totalMoves;
    }

    /**
     * Returns an array of the players on the board.
     *
     * @return an array of the players on the board
     */
    public Player[] getPlayers() {
        return players.toArray(new Player[0]);
    }

    /**
     * Returns a 2D array of the spaces on the board.
     *
     * @return a 2D array of the spaces on the board
     */
    public Space[][] getSpaces() {
        return spaces;
    }

    /**
     * Returns the width of the board.
     *
     * @return the width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the board.
     *
     * @return the height of the board
     */
    public int getHeight() {
        return height;
    }
}
