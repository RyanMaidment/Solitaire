package controller;

import model.*;

import java.awt.*;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

public class GameMoveListener extends MouseInputAdapter {
    private Deck deck = GamePanel.getDeck();
    private DiscardPile discardPile = null;
    private Columns selectedColumn = null;
    private GamePanel gamePanel;
    private FoundationPiles selectedFoundation = null;
    private Card selectedCard = null;
    private int p;

    @Override
    public void mouseClicked(MouseEvent e) {
        Component component = e.getComponent().getComponentAt(e.getPoint());
        if (component instanceof Columns) {
            selectedColumn = (Columns) component;
            discardPile = null;
            selectedCard = selectedColumn.getClickedCard(e.getY() - 150);
            Boolean x = null;
            for (FoundationPiles foundation : GamePanel.getFoundationPiles()) {
                x=selectedColumn.moveTo(foundation, selectedCard);
            }
            if (!x) {
                for (Columns columns : GamePanel.getColumns()) {
                    selectedColumn.moveTo(columns, selectedCard);
                }
            }
        }else if (component instanceof DiscardPile) {
            selectedColumn = null;
            discardPile = GamePanel.getDiscardPile();
            selectedCard = discardPile.topCard();
            Boolean x = null;
            if (selectedCard != null) {
                for (FoundationPiles foundation : GamePanel.getFoundationPiles()) {
                    x = foundation.moveFromDiscard(discardPile, selectedCard);
                    if(x){
                        break;
                    }
                }
            }
            if (!x) {
                for (Columns columns : GamePanel.getColumns()) {
                    boolean y=columns.moveFromDiscard(discardPile, selectedCard);
                    if(y){
                        break;
                    }
                }
            }
        } else if(GamePanel.p == 52){
            gamePanel.ifWon();
        }
    }
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

        }

    else if (component instanceof Deck) {
            selectedColumn = null;
            if (!deck.isEmpty()) {
                DiscardPile discard = GamePanel.getDiscardPile();
                discard.push(deck.pop());
                discard.topCard().showFace();
            } else if (deck.isEmpty()) {
                DiscardPile discard = GamePanel.getDiscardPile();
                GamePanel.counter = GamePanel.counter - 100;
                if (GamePanel.counter < 0) {
                    GamePanel.counter = 0;
                }
                GamePanel.addCounter();
                while (!discard.isEmpty()) {
                    deck.push(discard.pop());
                }
            }
        } else if (component instanceof DiscardPile) {
            selectedColumn = null;
            discardPile = GamePanel.getDiscardPile();
            selectedCard = discardPile.topCard();

            }else if(GamePanel.p == 52){
            gamePanel.ifWon();
        }
        }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selectedCard != null) {
            Component releasedComponent = e.getComponent().getComponentAt(e.getPoint());
            if (releasedComponent instanceof Columns) {
                if (discardPile != null) {
                    Columns destination = (Columns) releasedComponent;
                    if (!discardPile.isEmpty()) {
                        destination.moveFromDiscard(discardPile, selectedCard);
                    }
                    discardPile.repaint();
                } else if (selectedColumn != null) {
                    Columns source = selectedColumn;
                    Columns destination = (Columns) releasedComponent;
                    source.moveTo(destination, selectedCard);
                } else if (selectedFoundation != null) {
                    FoundationPiles source = selectedFoundation;
                    Columns destination = (Columns) releasedComponent;
                    source.moveTo(destination, selectedCard);
                    source.repaint();
                    destination.repaint();
                } else if(GamePanel.p == 52){
                    gamePanel.ifWon();
                }
            }
            selectedCard = null;
            selectedFoundation = null;
            selectedColumn = null;
            discardPile = null;
        }

    }
}