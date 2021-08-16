package controller;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class MainMenu {
    JFrame frame;
    JButton solitareButton;
    JButton leaderboardButton;
    JLabel title;
    JButton rulesButton;
    JButton quitButton;

    public MainMenu() {
        frame = new JFrame();
        rulesButton = new JButton("Rules");
        rulesButton.setBounds(125, 200, 150, 40);
        quitButton = new JButton("Quit");
        quitButton.setBounds(125, 250, 150, 40);
        leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setBounds(125, 150, 150, 40);
        title = new JLabel("Main Menu");
        title.setBounds(100, 30, 300, 30);
        title.setFont(title.getFont().deriveFont(40f));
        BufferedImage img = null;
        try {
            img = ImageIO.read((Objects.requireNonNull(getClass().getResource("/resources/backgroundMainMenu.jpg"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert img != null;
        Image dimg = img.getScaledInstance(400, 350, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        frame.setContentPane(new JLabel(imageIcon));
        solitareButton = new JButton("Solitaire");
        solitareButton.setBounds(125, 100, 150, 40);
        frame.add(rulesButton);
        frame.add(quitButton);
        frame.add(leaderboardButton);
        frame.add(solitareButton);
        frame.add(title);
        leaderboardButton.addActionListener(e -> {
            String leaderboard = "";
            try {
                leaderboard = readFromScore();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(frame, leaderboard);

        });

        rulesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "OBJECT OF THE GAME\n"
                    + "The first objective is to release and play into position certain cards to build up each foundation, in sequence and in suit, "
                    + "\n"
                    + "from the ace through the king. The ultimate objective is to build the whole pack onto the foundations, and if that can be done, the Solitaire game is won.\n"
                    + "\n" + "RANK OF CARDS\n"
                    + "The rank of cards in Solitaire games is: K (high), Q, J, 10, 9, 8, 7, 6, 5, 4, 3, 2, A (low).\n"
                    + "\n" + "THE DEAL\n" + "There are four different types of piles in Solitaire:\n" + "\n"
                    + "The Tableau: Seven piles that make up the main table.\n"
                    + "The Foundations: Four piles on which a whole suit or sequence " + "\n"
                    + "must be built up. In most Solitaire games, the four aces are the bottom card or base of the foundations. The foundation piles are hearts, diamonds, spades, and clubs.\n"
                    + "The Stock (or “Hand”) Pile: If the entire pack is not laid out " + "\n"
                    + "in a tableau at the beginning of a game, the remaining cards form the stock pile from which additional cards are brought into play according to the rules.\n"
                    + "The Talon (or “Waste”) Pile: Cards from the stock pile that " + "\n"
                    + "have no place in the tableau or on foundations are laid face up in the waste pile.\n"
                    + "To form the tableau, seven piles need to be created. " + "\n"
                    + "Starting from left to right, place the first card face up to make the first pile, " + "\n"
                    + "deal one card face down for the next six piles. Starting again from left to right, " + "\n"
                    + "place one card face up on the second pile and deal one card face down on piles three through seven. "
                    + "\n"
                    + "Starting again from left to right, place one card face up on the third pile and deal one card face down on piles four through seven. "
                    + "\n"
                    + "Continue this pattern until pile seven has one card facing up on top of a pile of six cards facing down.\n"
                    + "\n" + "The remaining cards form the stock (or “hand”) pile and are placed above the tableau.\n"
                    + "\n" + "When starting out, the foundations and waste pile do not have any cards.\n" + "\n"
                    + "THE PLAY\n"
                    + "The initial array may be changed by \"building\" - transferring cards among the face-up cards in the tableau. "
                    + "\n"
                    + "Certain cards of the tableau can be played at once, while others may not be played until certain blocking cards are removed. "
                    + "\n"
                    + "For example, of the seven cards facing up in the tableau, if one is a nine and another is a ten, you may transfer the nine to on top of the ten to begin building that pile in sequence. "
                    + "\n"
                    + "Since you have moved the nine from one of the seven piles, you have now unblocked a face down card; this card can be turned over and now is in play.\n"
                    + "\n"
                    + "As you transfer cards in the tableau and begin building sequences, if you uncover an ace, the ace should be placed in one of the foundation piles. "
                    + "\n" + "The foundations get built by suit and in sequence from ace to king.\n" + "\n"
                    + "Continue to transfer cards on top of each other in the tableau in sequence. If you can’t move any more face up cards, "
                    + "\n"
                    + "you can utilize the stock pile by flipping over the first card. This card can be played in the foundations or tableau."
                    + "\n"
                    + "If you cannot play the card in the tableau or the foundations piles, move the card to the waste pile and turn over another card in the stock pile.\n"
                    + "\n"
                    + "If a vacancy in the tableau is created by the removal of cards elsewhere it is called a “space”, "
                    + "\n"
                    + "and it is of major importance in manipulating the tableau. If a space is created, it can only be filled in with a king."
                    + "\n"
                    + " Filling a space with a king could potentially unblock one of the face down cards in another pile in the tableau.\n"
                    + "\n"
                    + "Continue to transfer cards in the tableau and bring cards into play from the stock pile until all the cards are built in suit sequences in the foundation piles to win!");
        });
        quitButton.addActionListener(e -> {
            frame.dispose();
            System.exit(0);
        });
        solitareButton.addActionListener(e -> {
            try {
                new Solitaire();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            frame.dispose();
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

    private String readFromScore() throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader("./Score.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();

    }

    public static void main(String[] args) throws IOException {
        new MainMenu();
    }

}
