package dk.dtu.roborally_server.controller;

import dk.dtu.roborally_server.repository.MoveRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moves")
public class MoveController {

    private MoveRepository moveRepository;

    public MoveController(MoveRepository moveRepository) {this.moveRepository = moveRepository;}
}
