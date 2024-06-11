package dk.dtu.roborally_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long gameId;
    private String gameName;
    private Long boardId;
    private String gameStatus;
    private Long TurnID;
    private Long maxPlayers;


}
