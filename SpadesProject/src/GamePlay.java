import java.util.Scanner;

public class GamePlay {
	static boolean spadesBroken;
	static int numOfTours = 1;
	static int numOfRounds = 1;


	public static void main(String[] args) {

		// Creating the players
		Player player = new Player();

		player.isBot = false;
		Player bot1 = new Player();

		bot1.isBot = true;
		Player bot2 = new Player();

		bot2.isBot = true;
		Player bot3 = new Player();

		bot3.isBot = true;

		// Creating the player list
		PlayerList pList = new PlayerList();
		pList.add(player);
		pList.add(bot1);
		pList.add(bot2);
		pList.add(bot3);

		// I will assume first starter is a real player no matter what the bids are
		Player lastWinner = player;
		// Creating the deck of cards in the middle
		CardList middle = new CardList();

		// Continues until game is over
		while (!isGameOver(player, bot1, bot2, bot3)) {

			// Creation of deck
			CardList deck = createDeck();
			// Shuffling the deck
			deck.shuffle();

			// Distributing the cards to player and bots
			for (int i = 0; i < 52; i = i + 4) {

				Card c = new Card();
				c = deck.getCardI(i);
				Card p = new Card(c.getValue(), c.getType(), c.getFace());
				player.hand.add(p);

				c = deck.getCardI(i + 1);
				p = new Card(c.getValue(), c.getType(), c.getFace());
				bot1.hand.add(p);

				c = deck.getCardI(i + 2);
				p = new Card(c.getValue(), c.getType(), c.getFace());
				bot2.hand.add(p);

				c = deck.getCardI(i + 3);
				p = new Card(c.getValue(), c.getType(), c.getFace());
				bot3.hand.add(p);

			}
			System.out.println("Welcome to tour " + numOfTours);
			System.out.println("Your new hand as follows: ");
			player.hand.output();
			// Bidding
			player.numOfBid = numOfBids(player);
			bot1.numOfBid = numOfBids(bot1);
			bot2.numOfBid = numOfBids(bot2);
			bot3.numOfBid = numOfBids(bot3);

			// Output of bids
			System.out.println("Your bid is: " + player.numOfBid);
			System.out.println("Bot1's bid is: " + bot1.numOfBid);
			System.out.println("Bot2's bid is: " + bot2.numOfBid);
			System.out.println("Bot3's bid is: " + bot3.numOfBid);

			// Continues 13 times for every tour
			for (int j = 1; j <= 13; j++) {
				System.out.println("Round " + numOfRounds);
				// First Played
				Card winnerCard = new Card();
				Card index0 = play(middle, lastWinner);
				int value = index0.getValue();
				String type = index0.getType();
				String face = index0.getFace();
				index0 = new Card(value, type, face);
				middle.add(index0);

				winnerCard = index0;

				// Second Played
				Player player2 = lastWinner.next;
				Card index1 = play(middle, player2);
				value = index1.getValue();
				type = index1.getType();
				face = index1.getFace();
				index1 = new Card(value, type, face);
				middle.add(index1);
				if (index1.getType().equals(winnerCard.getType())) {
					if (index1.getValue() > winnerCard.getValue()) {
						lastWinner = player2;
						winnerCard = index1;
					}
				} else {
					if (index1.getType().equals("Spade")) {
						lastWinner = player2;
						winnerCard = index1;
					}
				}

				// Third Played
				Player player3 = player2.next;
				Card index2 = play(middle, player3);
				value = index2.getValue();
				type = index2.getType();
				face = index2.getFace();
				index2 = new Card(value, type, face);
				middle.add(index2);
				if (index2.getType().equals(winnerCard.getType())) {
					if (index2.getValue() > winnerCard.getValue()) {
						lastWinner = player3;
						winnerCard = index2;
					}
				} else {
					if (index2.getType().equals("Spade")) {
						lastWinner = player3;
						winnerCard = index2;
					}
				}

				// Fourth Played
				Player player4 = player3.next;
				
				Card index3 = play(middle, player4);
				value = index3.getValue();
				type = index3.getType();
				face = index3.getFace();
				index3 = new Card(value, type, face);
				middle.add(index3);
				if (index3.getType().equals(winnerCard.getType())) {
					if (index3.getValue() > winnerCard.getValue()) {
						lastWinner = player4;
						winnerCard = index3;
					}
				} else {
					if (index3.getType().equals("Spade")) {
						lastWinner = player4;
						winnerCard = index3;
					}
				}

				// End of the round
				
				System.out.println("Middle as follows: ");
				middle.output();
				System.out.println("End of the round");
				lastWinner.numOfHandsWon++;
				middle = new CardList();
				numOfRounds++;

			}
			// Calculating the points
			player.points += calculate(player);
			bot1.points += calculate(bot1);
			bot2.points += calculate(bot2);
			bot3.points += calculate(bot3);

			// End of tour message
			tourOver(bot1, bot2, bot3, player);
			numOfTours++;

			spadesBroken = false;
			bot1.numOfHandsWon = 0;
			bot2.numOfHandsWon = 0;
			bot3.numOfHandsWon = 0;
			player.numOfHandsWon = 0;
			numOfRounds = 1;

		}

		// Game over message
		gameOver(bot1, bot2, bot3, player);

	}

	// At the end of the tour, calculates the points of players
	private static int calculate(Player player) {
		// If player claimed 0 bid
		if (player.numOfBid == 0) {
			// If he succeeds
			if (player.numOfHandsWon == 0) {
				return 100;
			}
			// If he does not succeed
			else {
				return -100;
			}
		}
		// If player claims more than one
		else {
			// If he succeeds
			if (player.numOfHandsWon >= player.numOfBid) {
				return 10 * player.numOfBid + (player.numOfHandsWon - player.numOfBid);
			}
			// If he does not succeed
			else {
				return -10 * player.numOfBid;
			}

		}

	}

	// The message of end of tour
	public static void tourOver(Player p1, Player p2, Player p3, Player p4) {

		System.out.println("Tour is over");
		System.out.println("Your points " + p4.points);
		System.out.println("Bot1 points: " + p1.points);
		System.out.println("Bot2 points: " + p2.points);
		System.out.println("Bot3 points: " + p3.points);

	}

	// The message of game ending
	public static void gameOver(Player p1, Player p2, Player p3, Player p4) {

		System.out.println("Game is over");
		System.out.println("Your points: " + p4.points);
		System.out.println("Bot1 points: " + p1.points);
		System.out.println("Bot2 points: " + p2.points);
		System.out.println("Bot3 points: " + p3.points);

	}

	// Checks whether the game ended or not
	public static boolean isGameOver(Player p1, Player p2, Player p3, Player p4) {
		return (p1.points >= 500 || p2.points >= 500 || p3.points >= 500 || p4.points >= 500);

	}

	// Method for deck of 52 cards
	public static CardList createDeck() {
		// Creating the cards and deck of 52 card

		CardList deck = new CardList();
		int index = 0;
		for (int i = 2; i < 11; i++) {
			Card c = new Card(i, "Club", "");
			deck.add(c);
			index++;
		}
		Card c = new Card(0, "Club", "Jack");
		deck.add(c);
		index++;
		c = new Card(0, "Club", "Queen");
		deck.add(c);
		index++;
		c = new Card(0, "Club", "King");
		deck.add(c);
		index++;
		c = new Card(0, "Club", "Ace");
		deck.add(c);
		index++;

		for (int i = 2; i < 11; i++) {
			c = new Card(i, "Spade", "");
			deck.add(c);
			index++;
		}
		c = new Card(0, "Spade", "Jack");
		deck.add(c);
		index++;
		c = new Card(0, "Spade", "Queen");
		deck.add(c);
		index++;
		c = new Card(0, "Spade", "King");
		deck.add(c);
		index++;
		c = new Card(0, "Spade", "Ace");
		deck.add(c);
		index++;

		for (int i = 2; i < 11; i++) {
			c = new Card(i, "Heart", "");
			deck.add(c);
			index++;
		}
		c = new Card(0, "Heart", "Jack");
		deck.add(c);
		index++;
		c = new Card(0, "Heart", "Queen");
		deck.add(c);
		index++;
		c = new Card(0, "Heart", "King");
		deck.add(c);
		index++;
		c = new Card(0, "Heart", "Ace");
		deck.add(c);
		index++;

		for (int i = 2; i < 11; i++) {
			c = new Card(i, "Diamond", "");
			deck.add(c);
			index++;
		}
		c = new Card(0, "Diamond", "Jack");
		deck.add(c);
		index++;
		c = new Card(0, "Diamond", "Queen");
		deck.add(c);
		index++;
		c = new Card(0, "Diamond", "King");
		deck.add(c);
		index++;
		c = new Card(0, "Diamond", "Ace");
		deck.add(c);
		index++;

		return deck;
	}

	// Returns the number of type cards in the CardList
	public static int numOfTypes(CardList list, String type) {
		Card c = list.head;
		int count = 0;
		while (c != null) {
			if (c.getType().equals(type)) {
				count++;
			}
			c = c.next;
		}
		return count;

	}

	// Returns the number of faces in the CardList
	public static int numOfFaces(CardList list) {
		Card c = list.head;
		int count = 0;
		while (c != null) {
			if (!c.getFace().equals("No Face") && !c.getFace().equals("Ace")) {
				count++;
			}
			c = c.next;
		}
		return count;

	}

	// Returns the of aces in the CardList
	public static int numOfAces(CardList list) {
		Card c = list.head;
		int count = 0;
		while (c != null) {
			if (c.getFace().equals("Ace")) {
				count++;
			}
			c = c.next;
		}
		return count;

	}

	// Returns the estimated bids for bots
	public static int numOfBids(Player p) {
		CardList l = p.hand;
		int a = numOfAces(l);

		/*
		 * In this part, I basically assume that bot can take the half of the maximum
		 * difference between number of spades and number any other type. If it is
		 * minus, f basically becomes 0. Which is not very good implementation, but I
		 * guess it is fair enough in this point.
		 */
		int b = numOfTypes(l, "Spade");
		int c = numOfTypes(l, "Heart");
		int d = numOfTypes(l, "Club");
		int e = numOfTypes(l, "Diamond");
		int f = Math.max(b - c, b - d);
		f = Math.max(f, b - e);
		f = Math.max(f, 0);
		f = f / 2;
		int g = numOfFaces(l);
		g = 2 * g / 3;

		if (p.isBot) {
			return a + f + g;
		} else {
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter your bid: ");
			int k = sc.nextInt();
			return k;
		}

	}

	// Plays the card according to hand and the middle
	public static Card play(CardList middle, Player player) {
		Scanner sc = new Scanner(System.in);
		Card playedCard = null;

		String typeOfHead = "";
		Card firstPlayed = middle.head;
		if (middle.head != null) {
			typeOfHead = firstPlayed.getType();
		} else {
			typeOfHead = null;
		}

		if (!player.isBot) {

			// Assumption: Player will enter the correct entries according to rules
			System.out.println("Your hand as follows");
			player.hand.output();
			System.out.println("Cards of Middles as follows: ");
			middle.output();
			System.out.println("Please enter the value of your card you want to play: ");
			int value = sc.nextInt();
			System.out.println("Please enter the type of your card you want to play: ");
			String type = sc.next();
			System.out.println("Please enter the face of your card you want to play: ");
			String face = sc.next();
			playedCard = new Card(value, type, face);
			if (playedCard.getType().equals("Spade")) {
				spadesBroken = true;
			}
			player.hand.remove(playedCard);

		} else {
			System.out.println("Bots hand as follows: ");
			player.hand.output();
			

			// Since there is too much possibilities, I will assume that whenever bots found
			// the playable cards, they are going to play that card

			// If there is no card in the middle
			if (middle.head == null) {

				// If the bot has only spades
				if (numOfTypes(player.hand, "Club") == 0 && numOfTypes(player.hand, "Heart") == 0
						&& numOfTypes(player.hand, "Diamond") == 0) {
					Card c = player.hand.getCardI(0);
					playedCard = c;
					player.hand.remove(playedCard);
					spadesBroken = true;
					return playedCard;
				}

				// If spades are broken but hand contains other types to play as well
				if (spadesBroken) {
					Card c = player.hand.getCardI(0);
					playedCard = c;
					player.hand.remove(playedCard);
					if (playedCard.getType().equals("Spade")) {
						spadesBroken = true;
					}
					return playedCard;

				}
				// If spades are not broken
				else {
					Card c = player.hand.getHead();
					while (c != null) {
						if (!c.getType().equals("Spade")) {
							playedCard = c;
							player.hand.remove(playedCard);
							return playedCard;
						}
						c = c.next;

					}

				}
			}
			// If there is card in the middle
			else {
				// If players hand contains the type of card first played
				if (numOfTypes(player.hand, typeOfHead) > 0) {
					Card c = player.hand.getHead();
					while (c != null) {
						if (c.getType().equals(typeOfHead)) {
							playedCard = c;
							player.hand.remove(playedCard);
							return playedCard;
						}
						c = c.next;

					}
				}
				// If players hand does not contain the type of first card played
				else {
					Card c = player.hand.getCardI(0);
					playedCard = c;
					player.hand.remove(playedCard);
					if (playedCard.getType().equals("Spade")) {
						spadesBroken = true;
					}
					return playedCard;

				}
			}

		}

		return playedCard;

	}

}
