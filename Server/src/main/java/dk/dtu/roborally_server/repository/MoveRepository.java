package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoveRepository extends JpaRepository<Move, Long> {

    public List<Move> findMoveByGameIdAndTurnId(Long gameId, Long turnId);

    public Move findMoveByGameIdAndTurnIdAndPlayerId(Long gameId, Long turnId, Long playerId);

}
