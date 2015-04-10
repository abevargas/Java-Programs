import java.lang.Integer;
import java.util.ArrayList;
import java.util.Random;
import java.io.*;

/**
 * Black Jack two-player game implemented
 * 
 * @author Abraham Vargas
 * 
 */
public class TwoPlayerBlackJack {
	private static final int MAX_SIZE = 10;
	private static final int MIN_SIZE = 3;
	static String answer;

	public static void main(String[] args) {
		twoPlayerBlackJack();

	}

	/**
	 * Default Constructor
	 */
	public TwoPlayerBlackJack() {
		twoPlayerBlackJack();
	}

	/**
	 * Returns the value of the card
	 * 
	 * @param c
	 *            card object
	 * @return card value
	 */
	public static int getCardValue(Card c) {
		int cardValue;

		Integer rankValue = new Integer(c.getRank());
		cardValue = rankValue;

		if (c.getRank().equals("Ace"))
			cardValue = 1;
		else
			cardValue = 10;

		return cardValue;
	}

	/**
	 * Computes value of hand
	 * 
	 * @param hand
	 * @return
	 */
	public static int computeHandValue(ArrayList<Card> hand) {
		int handValue = 0;
		int numOfAces = 0;

		for (int i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			if (c.getRank().equals("Ace"))
				numOfAces++;

			handValue += getCardValue(c);
		}

		if (numOfAces >= 1 && handValue < 12) {
			handValue = handValue + 10;
		}
		return handValue;
	}

	/**
	 * Prints the value of each card and then the total value of the hand.
	 * 
	 * @param hand
	 */
	public static void printHandValue(ArrayList<Card> hand) {
		int i;
		for (i = 0; i < hand.size(); i++) {
			Card c = hand.get(i);
			System.out.println(getCardValue(c) + " ");
		}

		System.out.println(computeHandValue(hand));
	}

	/**
	 * Decides which player is the winner.
	 * 
	 * @param hand01
	 *            first player
	 * @param hand02
	 *            second player
	 * @return 0:tie, 1:first player wins, -1:second player wins
	 */
	public static int isWinner(ArrayList<Card> hand01, ArrayList<Card> hand02) {
		int hand1value = computeHandValue(hand01);
		int hand2value = computeHandValue(hand02);

		if (hand1value == hand2value)
			return 0;

		else if ((hand1value <= 21 && hand2value <= 21 && hand1value > hand2value)
				|| (hand1value >= 21 && hand2value >= 21 && hand1value < hand2value)
				|| (hand1value <= 21 && hand2value > 21))
			return 1;

		else
			return -1;
	}

	/**
	 * Starts the game while player still wants to play
	 */
	public static void twoPlayerBlackJack() {
		DeckOfCards deck = new DeckOfCards();
		Random r = new Random();

		while (toContinue() == true) {
			int size01 = r.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;
			int size02 = r.nextInt(MAX_SIZE - MIN_SIZE) + MIN_SIZE;

			ArrayList<Card> hand01 = deck.getHand(size01);
			ArrayList<Card> hand02 = deck.getHand(size02);

			System.out.println("Welcome to BlackJack!");
			System.out.println("First Player: ");
			printHandValue(hand01);
			System.out.println("\nSecond Player: ");
			printHandValue(hand02);

			if (isWinner(hand01, hand02) == 1)
				System.out.println("First player wins!");
			else if (isWinner(hand01, hand02) == -1)
				System.out.println("Second player wins!");
			else
				System.out.println("It's a tie!");
		}
	}

	/**
	 * Decides whether player would like to play again
	 * 
	 * @param answer
	 *            yes or no
	 * @return boolean value
	 */
	public static boolean toContinue() {
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Would you like to play again? Please answer yes or no");
		
		try{
		answer = userIn.readLine();
		}catch(IOException e)
		{
			answer.equals("no");
		}
		answer = answer.toLowerCase();
		
		return answer.equals("yes") || answer.equals("y");
	}
}
