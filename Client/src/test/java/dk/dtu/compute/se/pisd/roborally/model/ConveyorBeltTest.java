package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.controller.field.ConveyorBelt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * @author Asma Maryam, s230716@dtu.dk
 */
class ConveyorBeltTest {

    private ConveyorBelt conveyorBelt;

    @BeforeEach
    void setUp() {
        conveyorBelt = new ConveyorBelt();
    }

    @Test
    void testGetAndSetHeadings() {
        Heading[] headings = {Heading.NORTH, Heading.EAST};
        conveyorBelt.setHeadings(headings);
        assertArrayEquals(headings, conveyorBelt.getHeadings());
    }

    @Test
    void testSetInvalidHeadings() {
        Heading[] invalidHeadings = {Heading.NORTH, Heading.SOUTH}; // Invalid because both are opposite directions
        conveyorBelt.setHeadings(invalidHeadings);
        assertNull(conveyorBelt.getHeadings());
    }

    @Test
    void testGetAndSetColor() {
        conveyorBelt.setColor(ConveyorBelt.Color.BLUE);
        assertEquals(ConveyorBelt.Color.BLUE, conveyorBelt.getColor());

        conveyorBelt.setColor(ConveyorBelt.Color.GREEN);
        assertEquals(ConveyorBelt.Color.GREEN, conveyorBelt.getColor());
    }

    @Test
    void testDoAction() {
        GameController mockGameController = mock(GameController.class);
        Space mockSpace = mock(Space.class);

        boolean result = conveyorBelt.doAction(mockGameController, mockSpace);
        assertFalse(result); // The method is not implemented yet, so it should return false
    }
}
