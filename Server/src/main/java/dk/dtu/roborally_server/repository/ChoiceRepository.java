package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    public Choice findChoiceByGameIdAndTurnIdAndPlayerId(Long gameId, Long turnId, Long playerId);

    public Choice insertChoiceByGameIdAndTurnIdAndPlayerId(Long gameId, Long turnId, Long playerId);
}
