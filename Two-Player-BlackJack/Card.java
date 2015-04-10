/**
 * Card class representing each card object
 * 
 * @author Abraham Vargas
 * 
 */
public class Card {

	private String rank; // card rank: a number between 2 and 10, or Jack,
							// Queen, King, or Ace
	private char suit; // card suit: S, C, H, or D(Spades, clubs, hearts, or
						// diamonds)
	private static String[] Ranks = { "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "Jack", "Queen", "King", "Ace" };
	private static char[] Suits = { 'C', 'H', 'D', 'S' };

	/**
	 * Default Constructor
	 */
	public Card() {
		this.rank = "2";
		this.suit = 'C';
	}

	/**
	 * Class Constructor
	 */
	public Card(String aRank, char aSuit) {
		if (checkRank(aRank) == true)
			this.rank = aRank;
		if (checkSuit(aSuit) == true)
			this.suit = aSuit;
	}

	/**
	 * Validates the rank of the card
	 * 
	 * @param aRank
	 *            rank to be validated
	 * @return boolean value
	 */
	public boolean checkRank(String aRank) {
		int i;

		for (i = 0; i < Ranks.length; i++) {
			if (Ranks[i].equals(aRank))
				return true;
		}

		return false;
	}

	/**
	 * Validates the suit of the card
	 * 
	 * @param aSuit
	 *            suit to be validated
	 * @return boolean value
	 */
	public boolean checkSuit(char aSuit) {
		int i;

		for (i = 0; i < Suits.length; i++) {
			if (Suits[i] == aSuit)
				return true;
		}

		return false;
	}

	// returns the rank of the card
	public String getRank() {
		return rank;
	}

	// returns the suit of the card
	public char getSuit() {
		return suit;
	}

	// sets the new rank of the card
	public void setRank(String newRank) {
		this.rank = newRank;
	}

	// sets the new suit of the card
	public void setSuit(char newSuit) {
		this.suit = newSuit;
	}

	/**
	 * Returns the rank and suit of card provided.
	 */
	public String toString() {
		String cardSuit;

		switch (suit) {
		case 'S':
			cardSuit = rank + " of Spades";
			break;
		case 'C':
			cardSuit = rank + " of Clubs";
			break;
		case 'H':
			cardSuit = rank + " of Hearts";
			break;
		case 'D':
			cardSuit = rank + " of Diamonds";
			break;
		default:
			cardSuit = "2 of Hearts";
		}// end switch

		return cardSuit; // returns the card suit and rank.
	}// end toString

	// Returns the possible ranks of cards
	public static String[] getPossibleRanks() {
		return Ranks;
	}

	// returns the possible suits of cards
	public static char[] getPossibleSuits() {
		return Suits;
	}
}
