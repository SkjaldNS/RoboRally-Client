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

    @Column(name = "GAMENAME")
    private String gameName;
    @Column(name = "BOARDID")
    private Long boardId;
    @Column(name = "GAMESTATUS")
    private int gameStatus;
    @Column(name = "TURNID")
    private Long turnID;
    @Column(name = "NUMBEROFPLAYERS")
    private Long numberOfPlayers;


}
