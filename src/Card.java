public class Card {
    private String rank;
    private String suit;
    private int value;
    private String design;

    public Card(String rank, String suit, int value, String design) {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.design = design;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getDesign() { return design; }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setDesign(String design) { this.design = design; }

    @Override
    public String toString() {
        return rank + " of " + suit + "\n" + design;
    }
}