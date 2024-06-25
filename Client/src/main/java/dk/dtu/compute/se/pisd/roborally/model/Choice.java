package dk.dtu.compute.se.pisd.roborally.model;

import com.google.gson.annotations.Expose;



/**
 * Class representing a choice made by a player in a game
 */
public class Choice {

    /**
     * Enum for the different types of choices
     */
    public enum ChoiceType {
        LEFT("Left"),
        RIGHT("Right");

        final public String displayName;

        /**
         * Constructor for the enum
         * @param displayName the display name of the choice
         */
        ChoiceType(String displayName) {
            this.displayName = displayName;
        }
    }

    private int gameId;
    private ChoiceType choiceType;
    private int turnId;
    private int playerId;

    /**
     * Constructor for the Choice class
     * @param choiceType the type of choice
     * @param turnID the ID of the turn
     * @param playerID the ID of the player
     * @param gameId the ID of the game
     */
    public Choice(ChoiceType choiceType, int turnID, int playerID, int gameId) {
        this.choiceType = choiceType;
        this.turnId = turnID;
        this.playerId = playerID;
        this.gameId = gameId;
    }

    /**
     * Getter for the choice type
     * @return the choice type
     */
    public ChoiceType getChoiceType() {
        return choiceType;
    }

    /**
     * Getter for the turn ID
     * @return the turn ID
     */
    public int getTurnId() {
        return turnId;
    }

    /**
     * Getter for the player ID
     * @return the player ID
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Getter for the game ID
     * @return the game ID
     */
    public int getGameId() {
        return gameId;
    }
}
