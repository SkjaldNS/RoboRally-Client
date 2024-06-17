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
    public void setId(Long playerId) {
        this.playerId = playerId;
    }
}
