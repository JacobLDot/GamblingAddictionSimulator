import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;
    private int points;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.points = 200;
    }

    public int getPoints() { return points; }

    public void addPoints(int points) { this.points += points; }

    public String toString() {
        return name + " has " + points + " points\n" + name + "'s cards: " + hand;
    }
}