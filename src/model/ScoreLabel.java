package model;

import javax.swing.*;
import java.awt.*;

public class ScoreLabel extends JLabel {

  public ScoreLabel() {
    super("Score", JLabel.CENTER);
    this.setForeground(Color.WHITE);
    super.setBounds(110, 600, 200, 30);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

  }

}
