package model;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import java.awt.event.*;

public class GameTimer extends JLabel {
  public GameTimer() {

    super("Time Left", JLabel.CENTER);
    this.setForeground(Color.WHITE);
    super.setBounds(300, 600, 200, 30);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

  }

}
