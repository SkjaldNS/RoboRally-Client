import dk.dtu.compute.se.pisd.roborally.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerLocalTest {

    private Deck.Board board;
    private PlayerLocal playerLocal;

    @BeforeEach
    void setUp() {
        board = new Deck.Board(8, 8);
        playerLocal = new PlayerLocal(board, 1, "TestPlayerLocal");
    }

    @Test
    void testInitialization() {
        assertNotNull(playerLocal.getDeck());
        assertNotNull(playerLocal.getDiscardedPile());

        for (int i = 0; i < PlayerLocal.NO_CARDS; i++) {
            assertNotNull(playerLocal.getCardField(i));
        }
    }

    @Test
    void testIsLocalPlayer() {
        assertTrue(playerLocal.isLocalPlayer());
    }

    @Test
    void testGetDeck() {
        Deck deck = playerLocal.getDeck();
        assertNotNull(deck);
        assertEquals(30, deck.initDeck.size()); // Initial deck size based on the example provided earlier
    }

    @Test
    void testGetDiscardedPile() {
        DiscardPileField discardedPile = playerLocal.getDiscardedPile();
        assertNotNull(discardedPile);
    }


}
