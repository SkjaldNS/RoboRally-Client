package dk.dtu.roborally_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long playerId;
    @Column(name = "GAMEID")
    private Long gameId;
    @Column(name = "PLAYERNAME")
    private String playerName;
    @Column(name = "ROBOTID")
    private int robotId;

    /**
     * The constructor for the Player class.
     * @param playerId the id of the player
     */
    public void setId(Long playerId) {
        this.playerId = playerId;
    }
}
