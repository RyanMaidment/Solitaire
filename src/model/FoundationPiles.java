package model;

import controller.GamePanel;

import java.awt.Graphics;

public class FoundationPiles extends Pile{
	private int suit;
	public FoundationPiles(int x, int y, int i) {
		super(x, y);
		super.setSize(102, 126);
		this.suit = i;
	}

	public boolean moveFromDiscard(DiscardPile source, Card card) {
		if(accepts(card)) {
			GamePanel.counter = GamePanel.counter + 20;
			GamePanel.p = GamePanel.p + 1;
			System.out.println(GamePanel.p);
			GamePanel.addCounter();
			this.push(source.pop());
			return true;
		}
		return false;
	}

	public void moveTo(Columns destination, Card card) {
		if(destination.accepts(card)) {
			destination.push(this.pop());
		}
	}

		public boolean accepts(Card card) {
			if(!this.isEmpty()) {
				return this.topCard().getValue() == card.getValue() - 1 &&
						this.topCard().getSuit().equals(card.getSuit());
			}
			return card.getValue() == 1 && intToSuit(card.getSuit()); // Ace
		}

	private boolean intToSuit(String pSuit) {
		if (pSuit.equals("C")) {
			return this.suit == 3;
		} else if (pSuit.equals("S")) {
			return this.suit == 1;
		} else if (pSuit.equals("H")) {
			return this.suit == 2;
		} else if (pSuit.equals("D")) {
			return this.suit == 4;
		}
		return false;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.isEmpty()) {
			g.drawImage(Card.getCardOutline(), 0, 0,
					this.getWidth(), this.getHeight(), this);
		}else {
			g.drawImage(this.topCard().getImageFront(),
					0, 0, this.getWidth(), this.getHeight(), this);
		}
	}

}
