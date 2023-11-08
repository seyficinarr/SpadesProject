
public class Card {

	int value;
	String type;
	String face;
	Card next;

	public Card() {

	}

	// Getters and Setters
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	public Card getNext() {
		return next;
	}

	public void setNext(Card next) {
		this.next = next;
	}

	// Constructor
	public Card(int value, String type, String face) {

		this.value = value;
		this.type = type;
		this.face = face;

		if (face.equals("Jack")) {
			this.value = 11;
		} else if (face.equals("Queen")) {
			this.value = 12;
		} else if (face.equals("King")) {
			this.value = 13;
		} else if (face.equals("Ace")) {
			this.value = 14;
		} else {
			this.face = "No Face";
		}

	}

	@Override
	public boolean equals(Object obj) {
		Card c = (Card) obj;
		return c.value == this.value && c.type.equals(this.type);
	}

}
