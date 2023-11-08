public class Player {

	String name;
	CardList hand;
	int numOfBid;
	int points;
	int numOfHandsWon;
	boolean isBot;
	Player next;

	public Player(String name, CardList hand, int bid, int points, int numOfHandsWon, boolean isBot, Player next) {
		super();
		this.name = name;
		this.hand = hand;
		this.numOfBid = bid;
		this.points = points;
		this.numOfHandsWon = numOfHandsWon;
		this.isBot = isBot;
		this.next = next;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isBot() {
		return isBot;
	}

	public void setBot(boolean isBot) {
		this.isBot = isBot;
	}

	public CardList getHand() {
		return hand;
	}

	public void setHand(CardList hand) {
		this.hand = hand;
	}

	public Player getNext() {
		return next;
	}

	@Override
	public boolean equals(Object obj) {

		Player p = (Player) obj;

		return this.name.equals(p.name);
	}

	public void setNext(Player next) {
		this.next = next;
	}

	public Player() {
		this.hand = new CardList();

	}

	// Getters and Setters

	public int getNumOfHandsWon() {
		return numOfHandsWon;
	}

	public void setNumOfHandsWon(int numOfHandsWon) {
		this.numOfHandsWon = numOfHandsWon;
	}

	public CardList getList() {
		return hand;
	}

	public void setList(CardList list) {
		this.hand = list;
	}

	public int getBid() {
		return numOfBid;
	}

	public void setBid(int bid) {
		this.numOfBid = bid;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	// Plays the best card for Bots according to middle and bot's hand
	public static Card playTheBest(CardList pCards, CardList middleCards) {

		return null;
	}

}
