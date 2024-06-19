package dk.dtu.compute.se.pisd.roborally.model;

import dk.dtu.compute.se.pisd.roborally.model.Command;
import dk.dtu.compute.se.pisd.roborally.model.Deck;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit tests for the Deck class.
 */
public class DeckTest {

    @Test
    public void testDeckConstructor() {
        Deck deck = new Deck();
        List<Command> expectedCommands = Arrays.asList(
                Command.FORWARD, Command.FORWARD, Command.FORWARD,
                Command.RIGHT, Command.RIGHT, Command.RIGHT, Command.LEFT, Command.LEFT, Command.LEFT,
                Command.FAST_FORWARD, Command.FAST_FORWARD, Command.FAST_FORWARD,
                Command.AGAIN, Command.AGAIN, Command.AGAIN, Command.POWER_UP, Command.POWER_UP, Command.POWER_UP,
                Command.FAST_FAST_FORWARD, Command.FAST_FAST_FORWARD, Command.FAST_FAST_FORWARD,
                Command.U_TURN, Command.U_TURN, Command.U_TURN, Command.OPTION_LEFT_RIGHT, Command.OPTION_LEFT_RIGHT, Command.OPTION_LEFT_RIGHT,
                Command.BACK_UP, Command.BACK_UP, Command.BACK_UP
        );

        assertEquals(expectedCommands.size(), deck.initDeck.size());
        assertTrue(deck.initDeck.containsAll(expectedCommands));
    }

    @Test
    public void testShuffleDeck() {
        Deck deck = new Deck();
        List<Command> originalOrder = new ArrayList<>(deck.initDeck);

        deck.shuffleDeck();

        List<Command> shuffledOrder = deck.initDeck;

        assertEquals(originalOrder.size(), shuffledOrder.size());
        assertTrue(shuffledOrder.containsAll(originalOrder));

        // There's a chance that the shuffle might result in the same order,
        // but it is very small. If it happens, running the test again should pass.
        assertFalse(originalOrder.equals(shuffledOrder), "The deck was not shuffled.");
    }
}
