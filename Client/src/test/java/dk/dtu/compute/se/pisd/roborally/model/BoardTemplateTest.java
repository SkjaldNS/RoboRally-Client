package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.roborally.fileaccess.model.BoardTemplate;
import dk.dtu.compute.se.pisd.roborally.fileaccess.model.SpaceTemplate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JUnit tests for the BoardTemplate class.
 */
public class BoardTemplateTest {

    @Test
    public void testConstructor() {
        BoardTemplate boardTemplate = new BoardTemplate();
        assertEquals(0, boardTemplate.width);
        assertEquals(0, boardTemplate.height);
        assertNotNull(boardTemplate.spaces);
        assertTrue(boardTemplate.spaces.isEmpty());
    }

    @Test
    public void testWidth() {
        BoardTemplate boardTemplate = new BoardTemplate();
        int width = 10;
        boardTemplate.width = width;
        assertEquals(width, boardTemplate.width);
    }

    @Test
    public void testHeight() {
        BoardTemplate boardTemplate = new BoardTemplate();
        int height = 15;
        boardTemplate.height = height;
        assertEquals(height, boardTemplate.height);
    }

    @Test
    public void testSpaces() {
        BoardTemplate boardTemplate = new BoardTemplate();
        List<SpaceTemplate> spaces = new ArrayList<>();
        SpaceTemplate space1 = new SpaceTemplate();
        SpaceTemplate space2 = new SpaceTemplate();
        spaces.add(space1);
        spaces.add(space2);

        boardTemplate.spaces = spaces;

        assertEquals(2, boardTemplate.spaces.size());
        assertTrue(boardTemplate.spaces.contains(space1));
        assertTrue(boardTemplate.spaces.contains(space2));
    }

    @Test
    public void testAddSpace() {
        BoardTemplate boardTemplate = new BoardTemplate();
        SpaceTemplate space = new SpaceTemplate();
        boardTemplate.spaces.add(space);

        assertEquals(1, boardTemplate.spaces.size());
        assertTrue(boardTemplate.spaces.contains(space));
    }

    @Test
    public void testRemoveSpace() {
        BoardTemplate boardTemplate = new BoardTemplate();
        SpaceTemplate space = new SpaceTemplate();
        boardTemplate.spaces.add(space);
        boardTemplate.spaces.remove(space);

        assertEquals(0, boardTemplate.spaces.size());
        assertFalse(boardTemplate.spaces.contains(space));
    }
}
