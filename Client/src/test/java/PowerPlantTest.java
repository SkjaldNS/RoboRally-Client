import dk.dtu.compute.se.pisd.roborally.controller.GameController;
import dk.dtu.compute.se.pisd.roborally.model.Space;
import dk.dtu.compute.se.pisd.roborally.controller.field.PowerPlant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PowerPlantTest {

    private PowerPlant powerPlant;

    @BeforeEach
    void setUp() {
        powerPlant = new PowerPlant();
    }

    @Test
    void testDoAction() {
        GameController mockGameController = mock(GameController.class);
        Space mockSpace = mock(Space.class);

        boolean result = powerPlant.doAction(mockGameController, mockSpace);
        assertFalse(result); // The method is not implemented yet, so it should return false
    }
}
