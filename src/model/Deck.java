package model;

import java.awt.Graphics;
import java.util.Collections;

public class Deck extends Pile {

	enum Suit {
		S, H, D, C
	};

	public Deck(int x, int y) {
		super(x, y);
		super.setSize(102, 126);
			for(Suit suit : Suit.values()) {
				for(int j = 1; j <= 13; ++j) {
					push(new Card(j, suit.toString()));
				}
			}
		Collections.shuffle(deckOfCards);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (!isEmpty()) {
			g.drawImage(Card.getImageBack(), 0, 0, 102, 126, this);
		}
	}

}
