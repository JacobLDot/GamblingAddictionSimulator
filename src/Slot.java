import java.awt.*;

public class Slot {
    private GameView window;
    private Image[] slotsImages;
    private String emoji;

    public Slot(String emoji, GameView window) {
        this.window = window;
        slotsImages = window.getSlotsImages();
    }

    public void draw(Graphics g) {
        g.drawImage(slotsImages[0], 420, 165, 120, 120, window);
    }
}