package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game findGameByGameName(String gameName);
    int findNumberOfPlayersByGameId(Long gameId);

    Game findGameByGameId(Long gameId);
}
