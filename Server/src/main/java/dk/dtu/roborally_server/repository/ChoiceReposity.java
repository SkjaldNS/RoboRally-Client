package dk.dtu.roborally_server.repository;

import dk.dtu.roborally_server.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceReposity extends JpaRepository<Choice, Long> {
}
