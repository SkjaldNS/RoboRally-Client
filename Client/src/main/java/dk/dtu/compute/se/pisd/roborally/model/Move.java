package dk.dtu.compute.se.pisd.roborally.model;

import com.google.gson.annotations.Expose;

public class Move {
    private Command reg1;
    private Command reg2;
    private Command reg3;
    private Command reg4;
    private Command reg5;
    private int gameId;
    private int playerId;
    private int turnId;

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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getTurnId() {
        return turnId;
    }

    public void setTurnId(int turnId) {
        this.turnId = turnId;
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
