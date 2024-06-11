package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<Player, Long> {

    public Player findPlayerByPlayerName(String playerName);
}
