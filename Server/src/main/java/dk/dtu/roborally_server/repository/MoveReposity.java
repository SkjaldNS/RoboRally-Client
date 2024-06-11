package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveReposity extends JpaRepository<Move, Long> {
}
