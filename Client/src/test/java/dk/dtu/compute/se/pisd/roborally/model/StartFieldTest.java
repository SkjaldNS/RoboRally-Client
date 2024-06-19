package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.controller.field.StartField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StartFieldTest {

    private StartField startField;

    @BeforeEach
    void setUp() {
        startField = new StartField();
    }

    @Test
    void testDoAction() {
        GameController mockGameController = mock(GameController.class);
        Space mockSpace = mock(Space.class);

        boolean result = startField.doAction(mockGameController, mockSpace);
        assertFalse(result); // The method is not implemented yet, so it should return false
    }
}
