package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Move;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoveRepository extends JpaRepository<Move, Long> {

    /**
     * Find all moves by game id and turn id
     */
    public List<Move> findMoveByGameIdAndTurnId(Long gameId, Long turnId);

    /**
     * Find a move by game id, turn id and player id
     */
    public Move findMoveByGameIdAndTurnIdAndPlayerId(Long gameId, Long turnId, Long playerId);

}
