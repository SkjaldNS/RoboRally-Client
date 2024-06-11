package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameReposity extends JpaRepository<Game, Long> {
}
