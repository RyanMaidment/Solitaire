package controller;

import model.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class GamePanel extends JPanel {
	GameTimer gameTimer;
	private static int start = -1;
	private static Deck deck;
	private static DiscardPile discardPile;
	private static FoundationPiles[] foundationPiles;
	private static Columns[] columns;
	private Columns column;
	private FoundationPiles foundationPile;

	public GamePanel() {
		super.setLayout(null);
		initializePiles();
		gameTimer = new GameTimer();
		add(gameTimer);
		startTimer();

		GameMoveListener l = new GameMoveListener();
		addMouseListener(l);
		addMouseMotionListener(l);
	}

	private void initializePiles() {

		deck = new Deck(20, 20);
		add(deck);
		discardPile = new DiscardPile(140, 20);
		add(discardPile);
		foundationPiles = new FoundationPiles[4];
		for (int i = 0; i < foundationPiles.length; ++i) {
			foundationPiles[i] = new FoundationPiles(380 + 120 * i, 20, i + 1);
			add(foundationPiles[i]);
		}
		columns = new Columns[7];
		for (int i = 1; i <= columns.length; ++i) {
			columns[i - 1] = new Columns(20 + 120 * (i - 1), 160, i);
			add(columns[i - 1]);
		}
	}

	public void startTimer() {
		Date startDate = new Date();

		ActionListener actionTimer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				start++;
				Date currentDate = new Date();
				Format formatter = new SimpleDateFormat("mm:ss");
				Date displayTime = new Date(currentDate.getTime() - startDate.getTime());
				String displayTimeString = formatter.format(displayTime);
				gameTimer.setText(displayTimeString);
				repaint();
			}
		};
		Timer timer = new Timer(1000, actionTimer);
		timer.start();
	}

	public static Card popDeck () {
			return deck.pop();
		}

	public static Columns[] getColumns () {
		return columns;
	}
	public boolean ifWon(){
		int S = column.getS();
		int Z = foundationPile.getZ();
		System.out.println(S+Z);
			return S + Z == 52;
		}
		public static FoundationPiles[] getFoundationPiles () {
			return foundationPiles;
		}

		public static DiscardPile getWastePile () {
			return discardPile;
		}

		public static Deck getDeck () {
			return deck;
		}

		@Override
		protected void paintComponent (Graphics g){
			super.paintComponent(g);
			g.drawImage(Card.getBackground(), 0, 0, this.getWidth(), this.getHeight(), this);
		}

	}
