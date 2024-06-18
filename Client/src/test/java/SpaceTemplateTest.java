
import dk.dtu.compute.se.pisd.roborally.controller.field.FieldAction;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.SpaceTemplate;
import dk.dtu.compute.se.pisd.roborally.model.Heading;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the SpaceTemplate class.
 */
public class SpaceTemplateTest {

    @Test
    public void testConstructor() {
        SpaceTemplate spaceTemplate = new SpaceTemplate();
        assertNotNull(spaceTemplate.walls);
        assertTrue(spaceTemplate.walls.isEmpty());
        assertNotNull(spaceTemplate.actions);
        assertTrue(spaceTemplate.actions.isEmpty());
        assertEquals(0, spaceTemplate.x);
        assertEquals(0, spaceTemplate.y);
    }

    @Test
    public void testWalls() {
        SpaceTemplate spaceTemplate = new SpaceTemplate();
        List<Heading> walls = new ArrayList<>();
        walls.add(Heading.NORTH);
        walls.add(Heading.SOUTH);

        spaceTemplate.walls = walls;

        assertEquals(2, spaceTemplate.walls.size());
        assertTrue(spaceTemplate.walls.contains(Heading.NORTH));
        assertTrue(spaceTemplate.walls.contains(Heading.SOUTH));
    }



    @Test
    public void testX() {
        SpaceTemplate spaceTemplate = new SpaceTemplate();
        int x = 5;
        spaceTemplate.x = x;
        assertEquals(x, spaceTemplate.x);
    }

    @Test
    public void testY() {
        SpaceTemplate spaceTemplate = new SpaceTemplate();
        int y = 10;
        spaceTemplate.y = y;
        assertEquals(y, spaceTemplate.y);
    }

    @Test
    public void testAddWall() {
        SpaceTemplate spaceTemplate = new SpaceTemplate();
        Heading wall = Heading.EAST;
        spaceTemplate.walls.add(wall);

        assertEquals(1, spaceTemplate.walls.size());
        assertTrue(spaceTemplate.walls.contains(wall));
    }

    @Test
    public void testRemoveWall() {
        SpaceTemplate spaceTemplate = new SpaceTemplate();
        Heading wall = Heading.WEST;
        spaceTemplate.walls.add(wall);
        spaceTemplate.walls.remove(wall);

        assertEquals(0, spaceTemplate.walls.size());
        assertFalse(spaceTemplate.walls.contains(wall));
    }


}
