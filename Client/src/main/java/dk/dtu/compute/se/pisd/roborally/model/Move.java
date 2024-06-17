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

    public Command getReg1() {
        return reg1;
    }

    public void setReg1(Command reg1) {
        this.reg1 = reg1;
    }

    public Command getReg2() {
        return reg2;
    }

    public void setReg2(Command reg2) {
        this.reg2 = reg2;
    }

    public Command getReg3() {
        return reg3;
    }

    public void setReg3(Command reg3) {
        this.reg3 = reg3;
    }

    public Command getReg4() {
        return reg4;
    }

    public void setReg4(Command reg4) {
        this.reg4 = reg4;
    }

    public Command getReg5() {
        return reg5;
    }

    public void setReg5(Command reg5) {
        this.reg5 = reg5;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public int getTurnID() {
        return turnID;
    }

    public void setTurnID(int turnID) {
        this.turnID = turnID;
    }

    public boolean isChoice(Command register){
        if(register.isInteractive()){
            return true;
        } else {
            return false;
        }
    }

    public void setChoice(Command register){
        if(isChoice(getReg1())){
            this.reg1 = register;
        } else if(isChoice(getReg2())){
            this.reg2 = register;
        } else if(isChoice(getReg3())){
            this.reg3 = register;
        } else if(isChoice(getReg4())){
            this.reg4 = register;
        } else if(isChoice(getReg5())){
            this.reg5 = register;
        }
    }

}
