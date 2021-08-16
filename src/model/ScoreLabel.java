package model;

import javax.swing.*;

import controller.Solitaire;

import java.awt.*;

public class ScoreLabel extends JLabel {

  public ScoreLabel() {
    super(Solitaire.playerName + "'s Score", JLabel.CENTER);
    this.setForeground(Color.WHITE);
    super.setBounds(110, 600, 210, 30);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

  }

}
