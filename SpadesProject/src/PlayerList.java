public class PlayerList {

	Player head;
	Player tail;

	public PlayerList() {
		head = null;
		tail = null;

	}

	public Player getHead() {
		return head;
	}

	public void setHead(Player head) {
		this.head = head;
	}

	public Player getTail() {
		return tail;
	}

	public void setTail(Player tail) {
		this.tail = tail;
	}

	// add the element to the end of the list

	public void add(Player player) {
		if (head == null) {
			head = player;
			tail = player;
		} else {
			tail.setNext(player);
			tail = player;
		}
		tail.setNext(head);
	}
}
