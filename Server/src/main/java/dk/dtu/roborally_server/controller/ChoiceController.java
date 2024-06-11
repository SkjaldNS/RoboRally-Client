package dk.dtu.roborally_server.controller;

import dk.dtu.roborally_server.repository.ChoiceRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/choices")
public class ChoiceController {

    private ChoiceRepository choiceRepository;

    public ChoiceController(ChoiceRepository choiceRepository) {this.choiceRepository = choiceRepository;}
}
