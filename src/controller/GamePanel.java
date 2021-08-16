package controller;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GamePanel extends JPanel {
	GameTimer gameTimer;
	TimeLabel TimeLabel;
	static GameCounter gameCounter;
	ScoreLabel scoreLabel;
	public static Timer timer;
	private static int start = -1;
	private static Deck deck;
	private static DiscardPile discardPile;
	private static FoundationPiles[] foundationPiles;
	private static Columns[] columns;
	private static String displayTimeString;
	public static int counter;
	public static int p;

	public GamePanel() {
		super.setLayout(null);
		initializePiles();
		p = 0;
		gameTimer = new GameTimer();
		TimeLabel = new TimeLabel();
		gameCounter = new GameCounter();
		scoreLabel = new ScoreLabel();
		add(scoreLabel);
		add(gameTimer);
		add(gameCounter);
		add(TimeLabel);
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

	public static Columns[] getColumns() {
		return columns;
	}

	public static Card popDeck() {
		return deck.pop();
	}

	public static void ifWon() {
		try {
			writeScoreToFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDialog gameOver = new JDialog((Dialog) null, "Game Over", true);
		gameOver.setSize(300,300);
		gameOver.setLayout(null);
		JTextField message = new JTextField("Congratulations, YOU WON!");
		JButton exit = new JButton("Exit");
		JButton restart = new JButton("Restart");
		message.setVisible(true);
		message.setEditable(false);
		message.setBounds(40,10,160,40);
		gameOver.add(message);
		exit.setVisible(true);
		exit.setBounds(100,160,80,40);
		gameOver.add(exit);
		restart.setVisible(true);
		restart.setBounds(100,110,80,40);
		gameOver.add(restart);
		restart.addActionListener(e -> {
			counter = 0;
			try {
				gameOver.dispose();
				Solitaire.getJFrame().dispose();
				new Solitaire();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		});
		exit.addActionListener(e -> {
			System.exit(0);
		});
		gameOver.setVisible(true);
	}

	public static void writeScoreToFile() throws IOException {
		String score = "./Score.txt";
		PrintWriter writer = new PrintWriter(new FileWriter(score, true));
		writer.append("\n");
		writer.append("Name: "+Solitaire.playerName + " Score: " + counter + " Time: " + displayTimeString);
		writer.close();
	}

	public static FoundationPiles[] getFoundationPiles() {
		return foundationPiles;
	}

	public static DiscardPile getDiscardPile() {
		return discardPile;
	}

	public static Deck getDeck() {
		return deck;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Card.getBackground(), 0, 0, this.getWidth(), this.getHeight(), this);
	}

}
