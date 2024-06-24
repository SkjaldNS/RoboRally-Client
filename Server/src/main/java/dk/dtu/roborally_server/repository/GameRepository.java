package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    /**
     * Find a game by its name
     * @param gameName the name of the game
     * @return the game with the given name
     */
    Game findGameByGameName(String gameName);

    /**
     * Find the number of players in a game
     * @param gameId the id of the game
     * @return the number of players in the game
     */
    int findNumberOfPlayersByGameId(Long gameId);

    /**
     * Find a game by its id
     * @param gameId the id of the game
     * @return the game with the given id
     */
    Game findGameByGameId(Long gameId);
}
