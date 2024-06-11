package dk.dtu.compute.se.pisd.roborally.model;

import com.google.gson.annotations.Expose;

public class Game {
    @Expose
    private int gameID;
    @Expose
    private int boardID;
    @Expose
    private int gameStatus;
    @Expose
    private int turnID;
    @Expose
    private int numPlayers;
}
