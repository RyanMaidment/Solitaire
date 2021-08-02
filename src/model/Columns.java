package model;

import controller.GamePanel;

import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.Deque;

public class Columns extends Pile {
	
	public Columns(int x, int y, int size) {
		super(x, y);
		super.setSize(150, 800);
		super.setOpaque(false);

		for (int i = 0; i < size; i++) {
			push(GamePanel.popDeck());
		}
		if (size > 0) {
			topCard().showFace();
		}
	}

	public boolean moveFromDiscard(DiscardPile source, Card card) {
		if (this.accepts(card)) {
			GamePanel.counter = GamePanel.counter + 5;
			GamePanel.addCounter();
			this.push(source.pop());
			return true;
		}
		return false;
	}

	public boolean accepts(Card card) {
		if (!this.isEmpty()) {
			return this.topCard().getValue() == card.getValue() + 1
					&& !this.topCard().getColor().equals(card.getColor());
		}
		return card.getValue() == 13;
	}

	public boolean moveTo(FoundationPiles destination, Card card) {
		if (!this.isEmpty() || card.getValue() == 13) {
			if (destination.accepts(card)) {
				Deque<Card> toBeMovedCards = new ArrayDeque<>();
				GamePanel.p = GamePanel.p + 1;
				System.out.println(GamePanel.p);
				GamePanel.counter = GamePanel.counter + 20;
				GamePanel.addCounter();
				while(!this.isEmpty()) {
					Card tmp = this.pop();
					toBeMovedCards.push(tmp);
					if(tmp.equals(card)) {
						break;
					}
				}
				while(!toBeMovedCards.isEmpty()) {
					destination.push(toBeMovedCards.pop());
				}
			}
		}

		if(!this.isEmpty()) {
			this.topCard().showFace();
		}
		return false;
	}

	public boolean moveTo(Columns destination, Card card) {
		if (!this.isEmpty() || card.getValue() == 13) {
			if (destination.accepts(card)) {
				Deque<Card> toBeMovedCards = new ArrayDeque<>();
				GamePanel.counter = GamePanel.counter + 5;
				GamePanel.addCounter();
				while (!this.isEmpty()) {
					Card tmp = this.pop();
					toBeMovedCards.push(tmp);
					if (tmp.equals(card)) {
						break;
					}
				}
				while (!toBeMovedCards.isEmpty()) {
					destination.push(toBeMovedCards.pop());
				}
			}
		}
		if(!this.isEmpty()) {
			this.topCard().showFace();
		}
		return false;
	}

	public Card getClickedCard(int y) {
		int index = y / 20;
		if (index < this.deckOfCards.toArray().length) {
			Card returnMe = (Card) deckOfCards.toArray()[index];
			if (returnMe.isFrontSide()) {
				return returnMe;
			}
		}
		return (Card) deckOfCards.toArray()[deckOfCards.toArray().length - 1];
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cardOverlap = 0;
			for (Card c : this.deckOfCards) {
				if (c.isFrontSide()) {
					g.drawImage(c.getImageFront(), 0, cardOverlap, 102, 126, this);
				} else {
					g.drawImage(Card.getImageBack(), 0, cardOverlap, 102, 126, this);
				}
				cardOverlap += 20;
			}
		}
	}
