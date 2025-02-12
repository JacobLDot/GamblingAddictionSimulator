import java.awt.*;

public class Slot {
    private GameView window;
    private Game game;
    private Image[] slotsImages;
    private String emoji;
    private int xPos, yPos;

    public Slot(String emoji, GameView window, int xPos, int yPos) {
        this.emoji = emoji;
        this.window = window;
        this.xPos = xPos;
        this.yPos = yPos;
        slotsImages = window.getSlotsImages();
    }

    public void draw(Graphics g) {
        int emojiIndex = getEmojiIndex(emoji);
        g.drawImage(slotsImages[emojiIndex], xPos, yPos, 70, 70, window);
    }

    public int getEmojiIndex(String emoji) {
        switch (emoji) {
            case "🍀": return 0;
            case "💎" : return 1;
            case "🍇" : return 2;
            case "🍋" : return 3;
            case "🥭" : return 4;
            case "🍊" : return 5;
            case "🍓" : return 6;
            case "🍉" : return 7;
            default: return -1;
        }
    }
}