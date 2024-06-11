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
import org.jetbrains.annotations.NotNull;

import static dk.dtu.compute.se.pisd.roborally.model.Heading.SOUTH;

/**
 * ...
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class Player extends Subject {

    @Expose
    final public static int NO_REGISTERS = 5;
    @Expose
    final public static int NO_CARDS = 8;

    public Board board;

    @Expose
    private DiscardPileField discardedPile;

    @Expose
    private Command lastCommand = null;

    @Expose
    private Deck deck;

    @Expose
    private String name;

    @Expose
    private int robotId;

    @Expose
    private int powerUpCnt = 0;

    @Expose
    private Command currentCommand;

    @Expose
    private int checkpointCollected = 0;

    @Expose
    private Space space;
    @Expose
    private Heading heading = SOUTH;


    @Expose
    private CommandCardField[] program;
    @Expose
    private CommandCardField[] cards;

    public Player(){}

    public Player(@NotNull Board board, int robotId, @NotNull String name) {
        this.board = board;
        this.name = name;
        this.robotId = robotId;

        this.space = null;

        program = new CommandCardField[NO_REGISTERS];
        for (int i = 0; i < program.length; i++) {
            program[i] = new CommandCardField(this);
        }

        cards = new CommandCardField[NO_CARDS];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new CommandCardField(this);
        }

        discardedPile = new DiscardPileField(this);

        this.deck = new Deck();
        deck.shuffleDeck();

    }

    public String getName() {
        return name;
    }

    public Deck getDeck() {
        return deck;
    }
    public Command getCurrentCommand() {
        return currentCommand;
    }
    public void setCurrentCommand(Command command){
        this.currentCommand = command;
    }
    public void oneUpPowerUpCnt() {
        this.powerUpCnt++;
    }
    public int getPowerUpCnt() {
        return powerUpCnt;
    }
    public void setLastCommand(Command lastCommand) {
        if(lastCommand != Command.AGAIN){
            this.lastCommand = lastCommand;}
    }
    public Command getLastCommand() {
        return lastCommand;
    }

    public int getRobotId() {
        return robotId;
    }

    public Space getSpace() {
        return space;
    }

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

    public Heading getHeading() {
        return heading;
    }

    public void setHeading(@NotNull Heading heading) {
        if (heading != this.heading) {
            this.heading = heading;
            notifyChange();
            if (space != null) {
                space.playerChanged();
            }
        }
    }

    public void setCheckpoint(int checkpointCollected) {
        this.checkpointCollected = checkpointCollected;
        notifyChange();
    }

    public int getCheckpointCollected() {
        return checkpointCollected;
    }

    public CommandCardField[] getProgram() {
        return program;
    }

    public CommandCardField[] getCards() {
        return cards;
    }

    public CommandCardField getProgramField(int i) {
        return program[i];
    }

    public CommandCardField getCardField(int i) {
        return cards[i];
    }

    public DiscardPileField getDiscardedPile() {
        return discardedPile;
    }
}
