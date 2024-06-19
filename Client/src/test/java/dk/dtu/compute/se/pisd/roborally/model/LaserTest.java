package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.controller.field.Laser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * @author Asma Maryam, s230716@dtu.dk
 */
class LaserTest {

    private Laser laser;

    @BeforeEach
    void setUp() {
        laser = new Laser();
    }

    @Test
    void testGetAndSetHeading() {
        laser.setHeading(Heading.NORTH);
        assertEquals(Heading.NORTH, laser.getHeading());

        laser.setHeading(Heading.EAST);
        assertEquals(Heading.EAST, laser.getHeading());
    }

    @Test
    void testDoAction() {
        GameController mockGameController = mock(GameController.class);
        Space mockSpace = mock(Space.class);

        boolean result = laser.doAction(mockGameController, mockSpace);
        assertFalse(result); // The method is not implemented yet, so it should return false
    }

}
