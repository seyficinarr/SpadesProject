
public class CardList {

	Card head;
	Card tail;

	public CardList() {
		head = null;
		tail = null;

	}

	public Card getHead() {
		return head;
	}

	public void setHead(Card head) {
		this.head = head;
	}

	public Card getTail() {
		return tail;
	}

	public void setTail(Card tail) {
		this.tail = tail;
	}

	// Returns the number of elements
	public int numOfElements() {
		int count = 0;
		Card c = head;
		while (c != null) {
			count++;
			c = c.next;
		}

		return count;
	}

	public Card getCardI(int i) {
		Card tmp = head;
		int index = 0;
		while (tmp != null) {
			if (index == i) {
				return tmp;
			}
			index++;
			tmp = tmp.getNext();
		}
		return null;
	}

	// Remove card according to card
	public void remove(Card c) {

		// Head
		if (head.type.equals(c.type) && c.value == head.value) {
			head = head.next;
		}
		// Tail
		else if (tail.type.equals(c.type) && c.value == tail.value) {
			Card current = head.next;
			Card previous = head;
			while (current != null) {
				if (current.value == c.value && c.type.equals(current.type)) {
					previous.next = null;
					tail = previous;
					break;

				}
				current = current.next;
				previous = previous.next;
			}

		}
		// Middle
		else {
			Card current = head.next;
			Card previous = head;
			while (current.next != null) {
				if (current.value == c.value && c.type.equals(current.type)) {
					previous.next = current.next;
					break;

				}
				current = current.next;
				previous = previous.next;
			}

		}
	}

	// add the element to the end of the list

	public void add(Card card) {
		if (head == null) {
			head = card;
			tail = card;
		} else {
			tail.setNext(card);
			tail = card;
		}
	}

	// Output of the card list
	public void output() {
		Card c = head;
		while (c != null) {
			String face = c.face;
			int value = c.value;
			String type = c.type;
			System.out.println("Face: " + face + " Value: " + value + " Type: " + type);
			c = c.next;
		}

	}

	// shuffles the cardList
	public void shuffle() {
		int deckSize = numOfElements();
		for (int i = 0; i < deckSize; i++) {
			int j = (int) (Math.random() * deckSize);
			Card card1 = getCardI(i);
			Card card2 = getCardI(j);
			swapCards(card1, card2);
		}
	}

	// swaps two cards in the list
	private void swapCards(Card card1, Card card2) {
		int tempValue = card1.getValue();
		String tempType = card1.getType();
		String tempFace = card1.getFace();

		card1.setValue(card2.getValue());
		card1.setType(card2.getType());
		card1.setFace(card2.getFace());

		card2.setValue(tempValue);
		card2.setType(tempType);
		card2.setFace(tempFace);
	}

	// Checks whether the cardList contains the types of given type of card
	public boolean containsType(Card card) {

		Card listCard = this.head;

		while (listCard != null) {
			if (listCard.getType().equals(card.getType())) {
				return true;
			}
			listCard = listCard.next;
		}

		return false;

	}

}
