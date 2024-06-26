package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {

    /**
     * Find a choice by gameId, turnId and playerId
     */
    public Choice findChoiceByGameIdAndTurnIdAndPlayerId(Long gameId, Long turnId, Long playerId);

}
