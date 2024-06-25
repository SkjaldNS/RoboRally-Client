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
import org.jetbrains.annotations.NotNull;

import static dk.dtu.compute.se.pisd.roborally.model.Heading.SOUTH;

/**
 * Class representing a player in the game. A player has a name, a robot, a
 * set of commands, and a current space on the board.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class Player extends Subject {

    final public static int NO_REGISTERS = 5;
    public Board board;
    private Command lastCommand = null;
    private int playerId;
    private int gameId;
    private String playerName;
    private int robotId;
    private int powerUpCnt = 0;
    private Command currentCommand;
    private int checkpointCollected = 0;
    private Space space;
    private Heading heading = SOUTH;
    private CommandCardField[] program;
    // Player Local
    private DiscardPileField discardedPile;
    private Deck deck;
    private CommandCardField[] cards;
    final public static int NO_CARDS = 8;
    private boolean isLocalPlayer;

    /**
     * Default constructor for the player class
     */
    public Player(){}

    /**
     * Constructor for the player class
     * @param board the board on which the player is placed
     * @param playerName the name of the player
     * @param isLocalPlayer boolean to check if player is local
     */
    public Player(Board board, @NotNull String playerName, boolean isLocalPlayer) {
        this.board = board;
        this.playerName = playerName;
        this.isLocalPlayer = isLocalPlayer;
        this.space = null;
    }
    /*
    public Player(Board board, int playerId) {
        this.board = board;
        this.playerId = playerId;

        program = new CommandCardField[NO_REGISTERS];
        for (int i = 0; i < program.length; i++) {
            program[i] = new CommandCardField(this);
        }
    }
*/

    /**
     * Method to initialize the player
     * @param player the player to be initialized
     */
    

    public void initPlayer() {

        program = new CommandCardField[NO_REGISTERS];
        for (int i = 0; i < program.length; i++) {
            program[i] = new CommandCardField(this);
        }

        cards = new CommandCardField[NO_CARDS];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new CommandCardField(this);
        }

        discardedPile = new DiscardPileField(this);
        deck = new Deck();
        deck.shuffleDeck();
    }

    /**
     * Method to get the deck of the player
     * @return the deck of the player
     */
    public Deck getDeck() {
        if(!isLocalPlayer) return null;
        return deck;
    }

    /**
     * Method to get the discarded pile of the player
     * @return the discarded pile of the player
     */
    public DiscardPileField getDiscardedPile() {
        if(!isLocalPlayer) return null;
        return discardedPile;
    }

    /**
     * Method to get the cards of the player
     * @return the cards of the player
     */
    public CommandCardField getCardField(int i) {
        if(!isLocalPlayer) return null;
        return cards[i];
    }

    /**
     * Method to get the cards of the player
     * @return the cards of the player
     */
    public String getName() {return playerName;}

    /**
     * Method to set the name of the player
     * @param playerName the name of the player
     */
    public void setName(String playerName) {this.playerName = playerName;}

    /**
     * Method to get the current command of the player
     * @return the current command of the player
     */
    public Command getCurrentCommand() {return currentCommand;}

    /**
     * Method to set the current command of the player
     * @param command the command to be set
     */
    public void setCurrentCommand(Command command){this.currentCommand = command;}

    /**
     * Method to get the program of the player
     */
    public void setProgramField(Move move) {
        program[0].setCard(new CommandCard(move.getReg1()));
        program[1].setCard(new CommandCard(move.getReg2()));
        program[2].setCard(new CommandCard(move.getReg3()));
        program[3].setCard(new CommandCard(move.getReg4()));
        program[4].setCard(new CommandCard(move.getReg5()));
    }

    /**
     * Increment the power up count of the player
     */
    public void oneUpPowerUpCnt() {this.powerUpCnt++;}

    /**
     * @return the power up count of the player
     */
    public int getPowerUpCnt() {return powerUpCnt;}

    /**
     * Method to set last command of the player
     * @param lastCommand the last command of the player
     */
    public void setLastCommand(Command lastCommand) {
        if(lastCommand != Command.AGAIN){
            this.lastCommand = lastCommand;}
    }

    /**
     * Method to get the last command of the player
     * @return the last command of the player
     */
    public Command getLastCommand() {return lastCommand;}

    /**
     * Method to get the robotId of the player
     * @return the robotId of the player
     */
    public int getRobotId() {return robotId;}

    /**
     * Method to set the robotId of the player
     * @param robotId the robotId of the player
     */
    public void setRobotId(int robotId) {this.robotId = robotId;}

    /**
     * Method to get the board of the player
     * @param board the board of the player
     */
    public void setBoard(Board board) {this.board = board;}

    /**
     * Method to get the space of the player
     * @return the space of the player
     */
    public Space getSpace() {return space;}

    /**
     * Method to set the space of the player
     * @param space the space of the player
     */
    public void setSpace(Space space) {
        Space oldSpace = this.space;
        if (space != oldSpace &&
                (space == null || space.board == this.board)) {
            this.space = space;
            if (oldSpace != null) {
                oldSpace.setPlayer(null);
            }
            if (space != null) {
                space.setPlayer(this);
            }
            notifyChange();
        }
    }

    /**
     * Method to get the heading of the player
     * @return the heading of the player
     */
    public Heading getHeading() {return heading;}

    /**
     * Method to set the heading of the player
     * @param heading the heading of the player
     */
    public void setHeading(@NotNull Heading heading) {
        if (heading != this.heading) {
            this.heading = heading;
            notifyChange();
            if (space != null) {
                space.playerChanged();
            }
        }
    }

    /**
     * Method to get the checkpoint of the player
     */
    public void setCheckpoint(int checkpointCollected) {
        this.checkpointCollected = checkpointCollected;
        notifyChange();
    }

    /**
     * Method to get the amount of checkpoints collected by the player
     * @return the amount of checkpoints collected by the player
     */
    public int getCheckpointCollected() {return checkpointCollected;}

    /**
     * Method to get the programmed command card fields of the player
     * @return the programmed command card fields of the player
     */
    public CommandCardField[] getProgram() {return program;}


    /**
     * Method to get one specific programmed command card field of the player
     * @param i the index of the command card field
     * @return the programmed command card field of the player
     */
    public CommandCardField getProgramField(int i) {return program[i];}

    /**
     * Method to get the player ID
     * @param playerID the player ID
     */
    public void setPlayerID(int playerID) {this.playerId = playerID;}

    /**
     * Method to get the player ID
     * @return the player ID
     */
    public long getPlayerID() {return playerId;}

    /**
     * Method to get the game ID
     * @return the game ID
     */
    public long getGameID() {return gameId;}

    /**
     * Method to set the game ID
     * @param gameID the game ID
     */
    public void setGameID(int gameID) {this.gameId = gameID;}

    /**
     * Method to set the local player
     * @param localPlayer boolean to check if player is local
     */
    public void setLocalPlayer(boolean localPlayer) {
        isLocalPlayer = localPlayer;
    }

    /**
     * Method to check if player is local
     * @return boolean to check if player is local
     */
    public boolean isLocalPlayer() {return isLocalPlayer;}
}
