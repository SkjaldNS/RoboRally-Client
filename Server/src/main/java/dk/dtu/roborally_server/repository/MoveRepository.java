package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoveRepository extends JpaRepository<Move, Long> {

    public Move findMoveByGameIdAndTurnId(Long gameId, Long turnId);

    public Move insertMoveByGameIdAndTurnIdAndPlayerId(Long gameId, Long turnId, Long playerId);
}
