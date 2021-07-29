package model;

import java.awt.Graphics;

public class DiscardPile extends Pile {

	public DiscardPile(int x, int y) {
		super(x, y);
		super.setSize(102, 126);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (this.isEmpty()) {
			g.drawImage(Card.getCardOutline(), 0, 0, 102, 126, this);
		} else {
			g.drawImage(this.topCard().getImageFront(), 0, 0, 102, 126, this);
		}

	}
}
