package dk.dtu.compute.se.pisd.roborally.model;

/**
 * The Move class represents a move in the game.
 * It contains the commands for each register, the game ID, player ID, and turn ID.
 */
public class Move {
    private Command reg1;
    private Command reg2;
    private Command reg3;
    private Command reg4;
    private Command reg5;
    private int gameId;
    private int playerId;
    private int turnId;

    /**
     * @return the command in the first register
     */
    public Command getReg1() {
        return reg1;
    }

    /**
     * Sets the command in the first register.
     *
     * @param reg1 the command to set in the first register
     */
    public void setReg1(Command reg1) {
        this.reg1 = reg1;
    }

    /**
     * @return the command in the second register
     */
    public Command getReg2() {
        return reg2;
    }

    /**
     * Sets the command in the second register.
     *
     * @param reg2 the command to set in the second register
     */
    public void setReg2(Command reg2) {
        this.reg2 = reg2;
    }

    /**
     * @return the command in the third register
     */
    public Command getReg3() {
        return reg3;
    }

    /**
     * Sets the command in the third register.
     *
     * @param reg3 the command to set in the third register
     */
    public void setReg3(Command reg3) {
        this.reg3 = reg3;
    }

    /**
     * @return the command in the fourth register
     */
    public Command getReg4() {
        return reg4;
    }

    /**
     * Sets the command in the fourth register.
     *
     * @param reg4 the command to set in the fourth register
     */
    public void setReg4(Command reg4) {
        this.reg4 = reg4;
    }

    /**
     * @return the command in the fifth register
     */
    public Command getReg5() {
        return reg5;
    }

    /**
     * Sets the command in the fifth register.
     *
     * @param reg5 the command to set in the fifth register
     */
    public void setReg5(Command reg5) {
        this.reg5 = reg5;
    }

    /**
     * @return the game ID
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * Sets the game ID.
     *
     * @param gameId the game ID to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the player ID
     */
    public int getPlayerId() {
        return playerId;
    }

    /**
     * Sets the player ID.
     *
     * @param playerId the player ID to set
     */
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    /**
     * @return the turn ID
     */
    public int getTurnId() {
        return turnId;
    }

    /**
     * Sets the turn ID.
     *
     * @param turnId the turn ID to set
     */
    public void setTurnId(int turnId) {
        this.turnId = turnId;
    }

    /**
     * Checks if the command in the given register is interactive.
     *
     * @param register the command to check
     * @return true if the command is interactive, false otherwise
     */
    public boolean isChoice(Command register){
        return register.isInteractive();
    }

    /**
     * Sets the command in the register that is interactive.
     *
     * @param register the command to set
     */
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
