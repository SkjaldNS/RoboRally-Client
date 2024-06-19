package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.controller.field.RebootField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RebootFieldTest {

    private RebootField rebootField;

    @BeforeEach
    void setUp() {
        rebootField = new RebootField();
    }

    @Test
    void testGetAndSetHeading() {
        rebootField.setHeading(Heading.NORTH);
        assertEquals(Heading.NORTH, rebootField.getHeading());

        rebootField.setHeading(Heading.EAST);
        assertEquals(Heading.EAST, rebootField.getHeading());
    }

    @Test
    void testDoAction() {
        GameController mockGameController = mock(GameController.class);
        Space mockSpace = mock(Space.class);

        boolean result = rebootField.doAction(mockGameController, mockSpace);
        assertFalse(result); // The method is not implemented yet, so it should return false
    }
}
