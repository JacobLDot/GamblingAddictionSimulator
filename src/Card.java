import java.awt.*;

public class Card {
    private String rank;
    private String suit;
    private int value;
    private String design;
    private GameView window;
    private Image[] cardsImages;

    public Card(String rank, String suit, int value, String design, GameView window) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.design = design;
        this.window = window;
        cardsImages = window.getCardsImages();
    }

    public String getSuit() { return suit; }

    public int getValue() { return value; }

    public String toString() {
        return rank + " of " + suit + "\n" + design;
    }

    public void draw(Graphics g) {
        if (suit.equals("Clubs")) {
            for (int i = 0; i < 60; i++) {
                g.drawImage(cardsImages[value - 1], 360-i, 250-i, i*2, i*2, window);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (suit.equals("Diamonds")) {
            for (int i = 0; i < 60; i++) {
                g.drawImage(cardsImages[value + 9], 360-i, 250-i, i*2, i*2, window);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (suit.equals("Hearts")) {
            for (int i = 0; i < 60; i++) {
                g.drawImage(cardsImages[value + 19], 360-i, 250-i, i*2, i*2, window);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = 0; i < 60; i++) {
                g.drawImage(cardsImages[value + 29], 360-i, 250-i, i*2, i*2, window);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}