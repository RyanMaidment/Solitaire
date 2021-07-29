package controller;

import model.*;

import java.awt.Component;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class GameMoveListener extends MouseInputAdapter {
    private Deck deck = GamePanel.getDeck();
    private DiscardPile discardPile = null;
    private Columns selectedColumn = null;
    private GamePanel gamePanel;
    private FoundationPiles selectedFoundation = null;
    private Card selectedCard = null;

    @Override
    public void mousePressed(MouseEvent e) {
        Component component = e.getComponent().getComponentAt(e.getPoint());
        if (component instanceof FoundationPiles) {
            selectedFoundation = (FoundationPiles) component;
            selectedColumn = null;
            discardPile = null;
            selectedCard = selectedFoundation.topCard();
        } else if (component instanceof Columns) {
            selectedColumn = (Columns) component;
            discardPile = null;
            selectedCard = selectedColumn.getClickedCard(e.getY() - 150);
            for (FoundationPiles foundation : GamePanel.getFoundationPiles()) {
                if (selectedColumn.moveTo(foundation, selectedCard)) {
                    selectedColumn = null;
                    break;
                }
            }
        } else if (component instanceof Deck) {
            selectedColumn = null;
            if (!deck.isEmpty()) {
                DiscardPile waste = GamePanel.getWastePile();
                waste.push(deck.pop());
                waste.topCard().showFace();
            } else if (deck.isEmpty()) {
                DiscardPile waste = GamePanel.getWastePile();
                while (!waste.isEmpty()) {
                    deck.push(waste.pop());
                }
            }
        } else if (component instanceof DiscardPile) {
            selectedColumn = null;
            discardPile = GamePanel.getWastePile();
            selectedCard = discardPile.topCard();
            if (selectedCard != null) {
                for (FoundationPiles foundation : GamePanel.getFoundationPiles()) {
                    foundation.moveFromWaste(discardPile, selectedCard);
                }
            }
        }
        e.getComponent().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selectedCard != null) {
            Component releasedComponent = e.getComponent().getComponentAt(e.getPoint());
            if (releasedComponent instanceof Columns) {
                if (discardPile != null) {
                    Columns destination = (Columns) releasedComponent;
                    if (!discardPile.isEmpty()) {
                        destination.moveFromWaste(discardPile, selectedCard);
                    }
                    discardPile.repaint();
                } else if (selectedColumn != null) {
                    Columns source = selectedColumn;
                    Columns destination = (Columns) releasedComponent;
                    source.moveTo(destination, selectedCard);
                    source.repaint();
                } else if (selectedFoundation != null) {
                    FoundationPiles source = selectedFoundation;
                    Columns destination = (Columns) releasedComponent;
                    source.moveTo(destination, selectedCard);
                    source.repaint();
                    destination.repaint();
                } else if (gamePanel.ifWon()) {
                    System.out.println("Congrats.");
                }
            }
            e.getComponent().repaint();
            selectedCard = null;
            selectedFoundation = null;
            selectedColumn = null;
            discardPile = null;
        }


    }
}