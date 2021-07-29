package model;

import controller.GamePanel;

import java.awt.Graphics;
import java.util.ArrayDeque;
import java.util.Deque;

public class Columns extends Pile {
private int s;
	
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

	public int getS(){
		return s;
	}
	public void moveFromWaste(DiscardPile source, Card card) {
		if (this.accepts(card)) {
			this.push(source.pop());
		}
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
				s++;
				System.out.println(s);
				Deque<Card> toBeMovedCards = new ArrayDeque<>();
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

	public void moveTo(Columns destination, Card card) {
		if (!this.isEmpty() || card.getValue() == 13) {
			if (destination.accepts(card)) {
				Deque<Card> toBeMovedCards = new ArrayDeque<>();
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
