package dk.dtu.compute.se.pisd.roborally.model;

import com.google.gson.annotations.Expose;

public class Choice {
    @Expose
    private String choiceType;
    @Expose
    private int turnID;
    @Expose
    private int playerID;
}
