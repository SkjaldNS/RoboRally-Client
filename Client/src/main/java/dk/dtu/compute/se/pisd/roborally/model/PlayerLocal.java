package dk.dtu.compute.se.pisd.roborally.model;

import org.jetbrains.annotations.NotNull;

/**
 * PlayerLocal extends the Player class and implements the nescessary logic for a local player.
 *
 * @Author Nikolaj Schæbel, s220471
 *
 * */
public class PlayerLocal extends Player {

    private DiscardPileField discardedPile;
    private Deck deck;
    private CommandCardHandField[] cards;
    final public static int NO_CARDS = 8;

    public PlayerLocal(@NotNull Deck.Board board, int robotId, @NotNull String name) {

        super(board, robotId, name);

        cards = new CommandCardHandField[NO_CARDS];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new CommandCardHandField(this);
        }

        discardedPile = new DiscardPileField(this);
        deck = new Deck();
        deck.shuffleDeck();
    }

    public Deck getDeck() {return deck;}

    public DiscardPileField getDiscardedPile() {return discardedPile;}

    public CommandCardHandField getCardField(int i) {return cards[i];}

    @Override
    public boolean isLocalPlayer() {return true;}
}