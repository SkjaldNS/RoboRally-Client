package dk.dtu.roborally_server.controllerTests;

import dk.dtu.roborally_server.controller.ChoiceController;
import dk.dtu.roborally_server.model.Choice;
import dk.dtu.roborally_server.repository.ChoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ChoiceControllerTest {

    private ChoiceRepository choiceRepository;
    private ChoiceController choiceController;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        choiceRepository = mock(ChoiceRepository.class);
        choiceController = new ChoiceController(choiceRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(choiceController).build();
    }

    @Test
    public void testGetChoices() throws Exception {
        Choice choice = new Choice();
        when(choiceRepository.findChoiceByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong())).thenReturn(choice);

        mockMvc.perform(get("/games/1/choices/1/1"))
                .andExpect(status().isOk());

        verify(choiceRepository, times(1)).findChoiceByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong());
    }

    @Test
    public void testCreateChoice() throws Exception {
        when(choiceRepository.findChoiceByGameIdAndTurnIdAndPlayerId(anyLong(), anyLong(), anyLong())).thenReturn(null);

        mockMvc.perform(post("/games/1/choices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"gameId\":1,\"turnId\":1,\"playerId\":1,\"choiceType\":\"LEFT\"}"))
                .andExpect(status().isOk());

        verify(choiceRepository, times(1)).save(any(Choice.class));
    }
}