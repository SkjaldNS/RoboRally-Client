package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PlayerRepository extends JpaRepository<Player, Long> {


    public Player findPlayerByPlayerName(String playerName);

    public Optional<Player> findPlayerById(Long playerId);

    public List<Player> findPlayersByGameId(Long gameId);

    public Player findPlayerByIdAndGameId(Long playerId, Long gameId);

    public void deletePlayersByGameId(Long gameId);
}
