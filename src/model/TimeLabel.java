package model;

import javax.swing.*;
import java.awt.*;

public class TimeLabel extends JLabel {

  public TimeLabel() {
    super("Time", JLabel.CENTER);
    this.setForeground(Color.WHITE);
    super.setBounds(500, 600, 200, 30);

  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

  }

}
