package dk.dtu.compute.se.pisd.roborally.model;

import com.google.gson.annotations.Expose;

public class Move {
    @Expose
    private Command reg1;
    @Expose
    private Command reg2;
    @Expose
    private Command reg3;
    @Expose
    private Command reg4;
    @Expose
    private Command reg5;
    @Expose
    private int gameID;
    @Expose
    private int playerID;
    @Expose
    private int turnID;
}
