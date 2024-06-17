package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PlayerRepository extends JpaRepository<Player, Long> {


    public Player findPlayerByPlayerName(String playerName);

    public Optional<Player> findPlayerById(Long playerId);
}
