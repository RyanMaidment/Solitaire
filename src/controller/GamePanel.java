package controller;

import model.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class GamePanel extends JPanel {
	GameTimer gameTimer;
	static GameCounter gameCounter;
	public static Timer timer;
	private static int start = -1;
	private static Deck deck;
	private static DiscardPile discardPile;
	private static FoundationPiles[] foundationPiles;
	private static Columns[] columns;
	private Columns column;
	private FoundationPiles foundationPile;
	private String displayTimeString;
	public static int counter;
	public static int p;

	public GamePanel() {
		super.setLayout(null);
		initializePiles();
		gameTimer = new GameTimer();
		gameCounter = new GameCounter();
		add(gameTimer);
		add(gameCounter);
		startTimer();
		addCounter();

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

	public static void addCounter() {
		String counterScore = String.valueOf(counter);
		gameCounter.setText(counterScore);
		gameCounter.revalidate();
		gameCounter.repaint();
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
				displayTimeString = formatter.format(displayTime);
				gameTimer.setText(displayTimeString);
				repaint();
			}
		};
		timer = new Timer(1000, actionTimer);
		timer.start();
	}

	public static Columns[] getColumns () {
		return columns;
	}
	public static Card popDeck () {
			return deck.pop();
		}

	public void ifWon() {
		String time = gameTimer.getText();
		String points = gameCounter.getText();
		String name = "";
		System.out.println("Congrats!");
		//TODO: prompt for name, add to leaderboard.
	}

	public static FoundationPiles[] getFoundationPiles () {
			return foundationPiles;
		}

		public static DiscardPile getDiscardPile () {
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
