package model;


import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Card {

	private static Image cardOutline;
	private static Image imageBack;
	private int value;
	private String suit;
	private String color;
	private Image imageFront;
	private boolean frontSide;

	public Card(int value, String suit) {
		this.suit=suit;
		this.value=value;
		ImageIcon ii = new ImageIcon(getClass().getResource("/resources/" + value + suit + ".png"));
		imageFront = ii.getImage();
	}

	public int getValue() {
		return value;
	}

	public String getSuit() {
		return suit;
	}

	public String getColor() {
		if (suit == "S" || suit=="C") {
			color = "black";
		}else {
			color = "red";
		}
		return color;
	}

	public boolean isFrontSide() {
		return frontSide;
	}

	public Image getImageFront() {
		return imageFront;
	}

	public static Image getImageBack(){
		URL urlBack = Card.class.getResource("/resources/yellow_back.png");
		imageBack = new ImageIcon(urlBack).getImage();
		return imageBack;
	}

	public static Image getCardOutline(){
		URL urlOutline = Card.class.getResource("/resources/bottom.gif");
		cardOutline = new ImageIcon(urlOutline).getImage();
		return cardOutline;
	}

	public static Image getBackground(){
		URL urlBackground = Card.class.getResource("/resources/background.jpg");
		Image backgroundImage = new ImageIcon(urlBackground).getImage();
		return backgroundImage;
	}

	public void showFace() {
		frontSide = true;
	}

}