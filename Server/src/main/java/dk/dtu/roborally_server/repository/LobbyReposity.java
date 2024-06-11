package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LobbyReposity extends JpaRepository<Lobby, Long> {
}
