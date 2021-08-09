package model;

import javax.swing.*;
import java.awt.*;

public class GameCounter extends JLabel {
    public GameCounter() {

        super("", JLabel.CENTER);
        this.setForeground(Color.WHITE);
        super.setBounds(200, 600, 200, 30);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

}
