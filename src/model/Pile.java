package model;

import java.util.Stack;

import javax.swing.JPanel;

public abstract class Pile extends JPanel {
	Stack<Card> deckOfCards;
	public Pile(int x, int y) {
		super.setLocation(x, y);
		deckOfCards = new Stack<>();
	}

	public Card topCard() {
		if(!this.deckOfCards.isEmpty()) {
			return this.deckOfCards.peek();
		}
		return null;
	}

	public Card pop() {
			return deckOfCards.pop();
	}

	public void push(Card card) {
		this.deckOfCards.push(card);
	}

	public boolean isEmpty() {
		return this.deckOfCards.isEmpty();
	}

}
