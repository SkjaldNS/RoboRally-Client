package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PlayerRepository extends JpaRepository<Player, Long> {

    /**
     * Find a player by the player name
     * @param playerName the name of the player
     * @return the player with the given name
     */
    public Player findPlayerByPlayerName(String playerName);

    /**
     * Find a player by the player id
     */
    public Optional<Player> findPlayerById(Long playerId);

    /**
     * Find players by game id
     * @param gameId the id of the game
     * @return a list of players in the game
     */
    public List<Player> findPlayersByGameId(Long gameId);

    /**
     * Find a player by the player id and the game id
     * @param playerId the id of the player
     * @param gameId the id of the game
     * @return the player with the given id and game id
     */
    public Player findPlayerByIdAndGameId(Long playerId, Long gameId);

    /**
     * Delete players by game id
     * @param gameId the id of the game
     */
    public void deletePlayersByGameId(Long gameId);
}
