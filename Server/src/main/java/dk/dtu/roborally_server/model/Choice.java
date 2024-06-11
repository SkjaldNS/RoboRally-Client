package dk.dtu.roborally_server.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "choice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class Choice {
    /**
     * The ChoiceType enum.
     * This enum is used to determine the type of choice the player has made.
     * The choice can either be LEFT or RIGHT.
     */
    public enum ChoiceType {
        LEFT("Left"),
        RIGHT("Right");

        final public String displayName;

        ChoiceType(String displayName) {
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

    @Column(name = "CHOICETYPE")
    private ChoiceType choiceType;

}

