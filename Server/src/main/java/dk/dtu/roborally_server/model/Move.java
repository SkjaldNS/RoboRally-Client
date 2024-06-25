package dk.dtu.roborally_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "move")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class Move {
    /**
     * The Command enum.
     * This enum is used to determine the type of command the player has made.
     * The command can either be FORWARD, RIGHT, LEFT, FAST_FORWARD, AGAIN, POWER_UP, FAST_FAST_FORWARD, U_TURN, BACK_UP, OPTION_LEFT_RIGHT.
     */
    public enum Command {

        FORWARD("Fwd"),
        RIGHT("Turn Right"),
        LEFT("Turn Left"),
        FAST_FORWARD("Fast Fwd"),
        AGAIN("Again"),
        POWER_UP("Power Up"),
        FAST_FAST_FORWARD("Fast Fast Fwd"),
        U_TURN("U-Turn"),
        BACK_UP("Back Up"),
        OPTION_LEFT_RIGHT("Left OR Right", LEFT, RIGHT);

        final public String displayName;

        /**
         * The constructor for the Command enum.
         * @param displayName the name that showed in the GUI
         * @param options the options of the command (if any)
         * @author Daniel Overballe Lerche, s235095@dtu.dk (javadoc only)
         */
        Command(String displayName, Command... options) {
            this.displayName = displayName;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "GAMEID")
    private Long gameId;
    @Column(name = "TURNID")
    private Long turnId;

    @Column(name = "PLAYERID")
    private Long playerId;

    @Column(name = "REG1")
    private Command reg1;

    @Column(name = "REG2")
    private Command reg2;

    @Column(name = "REG3")
    private Command reg3;

    @Column(name = "REG4")
    private Command reg4;

    @Column(name = "REG5")
    private Command reg5;
}
