package model;

import javax.swing.*;
import java.awt.*;

public class GameTimer extends JLabel {
  public GameTimer() {

    super("", JLabel.CENTER);
    this.setForeground(Color.WHITE);
    super.setBounds(550, 600, 200, 30);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

  }

}
