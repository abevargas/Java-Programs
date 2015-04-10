import java.util.ArrayList;
import java.util.Random;

/**
 * Deck of cards class representing a deck of 52 cards
 * 
 * @author Abraham Vargas
 * 
 */
public class DeckOfCards {
	private ArrayList<Card> deck; // The deck of cards
	private static final int DECK_SIZE = 52; // The 52 cards of the deck
	private static final Random randCard = new Random();
	private ArrayList<Card> hand;

	/**
	 * Default Constructor
	 */
	public DeckOfCards() {
		deck = new ArrayList<Card>(DECK_SIZE);
		resetDeckOfCards();
	}

	/**
	 * final static Removes all cards from the deck and populates it with 52 new
	 * cards
	 */
	public final void resetDeckOfCards() {
		String[] ranks = Card.getPossibleRanks();
		char[] suits = Card.getPossibleSuits();

		deck.clear();

		for (int i = 0; i < ranks.length; i++) {
			for (int j = 0; j < suits.length; j++) {
				deck.add(new Card(ranks[i], suits[j]));
			}
		}
	}

	/**
	 * Removes a random card from the Array List of Cards
	 * 
	 * @return the randomly removed card
	 */
	public Card getCard() {
		// if deck has less than one card, reset
		if (deck.size() < 1)
			resetDeckOfCards();
		// return and remove random card
		return deck.remove(randCard.nextInt(deck.size()));
	}

	/**
	 * Returns the player's hand
	 * 
	 * @param size
	 *            of cards in hand
	 * @return player's hand
	 */
	public ArrayList<Card> getHand(int size) {
		if (size > 10 || size < 3)
			return null;

		else {
			while (hand.size() < 7) {
				hand.add(getCard());
			}
		}
		return hand;
	}
}
