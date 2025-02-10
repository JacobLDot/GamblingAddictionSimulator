import java.awt.*;

public class Card {
    private String rank;
    private String suit;
    private int value;
    private String design;
    private GameView window;
    private Image[] slotsImages;
    private Image[] cardsImages;

    public Card(String rank, String suit, int value, String design, GameView window) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.design = design;
        this.window = window;
        slotsImages = window.getSlotsImages();
        cardsImages = window.getCardsImages();
    }

    public String getSuit() { return suit; }

    public int getValue() { return value; }

    public String toString() {
        return rank + " of " + suit + "\n" + design;
    }

    public void draw(Graphics g) {
        if (suit.equals("Clubs")) {
            g.drawImage(cardsImages[value - 1], 100, 100, 250, 350, window);
        } else if (suit.equals("Diamonds")) {
            g.drawImage(cardsImages[value + 9], 100, 100, 250, 350, window);
        } else if (suit.equals("Hearts")) {
            g.drawImage(cardsImages[value + 19], 100, 100, 250, 350, window);
        } else {
            g.drawImage(cardsImages[value + 29], 100, 100, 250, 350, window);
        }
    }
}