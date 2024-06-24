package dk.dtu.compute.se.pisd.roborally.model;

import com.google.gson.annotations.Expose;



public class Choice {

    public enum ChoiceType {
        LEFT("Left"),
        RIGHT("Right");

        final public String displayName;

        ChoiceType(String displayName) {
            this.displayName = displayName;
        }
    }

    private int gameId;
    private ChoiceType choiceType;
    private int turnId;
    private int playerId;

    public Choice(ChoiceType choiceType, int turnID, int playerID, int gameId) {
        this.choiceType = choiceType;
        this.turnId = turnID;
        this.playerId = playerID;
        this.gameId = gameId;
    }

    public ChoiceType getChoiceType() {
        return choiceType;
    }

    public int getTurnId() {
        return turnId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getGameId() {
        return gameId;
    }
}
