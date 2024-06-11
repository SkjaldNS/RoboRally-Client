package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PlayerReposity extends JpaRepository<Player, Long> {

    public Player findPlayerByPlayerName(String playerName);
}
