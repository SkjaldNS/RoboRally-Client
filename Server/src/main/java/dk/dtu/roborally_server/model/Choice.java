package dk.dtu.roborally_server.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "choices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long choiceMode;
    private String choice;
    private Long TurnId;
}
