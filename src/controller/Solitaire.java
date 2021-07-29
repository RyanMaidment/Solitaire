package controller;

import model.GameTimer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class Solitaire implements ActionListener {
	JFrame frame;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem restart;
	protected GamePanel gamePanel;
	GameTimer gameTimer;
	public Solitaire() {
			menuBar = new JMenuBar();
			menu = new JMenu("Menu");
			menuBar.add(menu);
			restart = new JMenuItem("Restart");
			restart.addActionListener(this);
			menu.add(restart);
			frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			gamePanel = new GamePanel();
			gamePanel.setPreferredSize(new Dimension(900, 700));
			frame.setJMenuBar(menuBar);
			frame.add(gamePanel);
			frame.setVisible(true);
			frame.pack();
	}

	public static void main(String[] args) {
		new Solitaire();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	if(e.getSource() == restart) {
		frame.dispose();
		new Solitaire();
	}
	}
}

