import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Image slotsTableImage;
    private Image rouletteTableImage;
    private Image[] slotsImages;
    private Image[] cardsImages;
    private final int WINDOW_WIDTH = 1024;
    private final int WINDOW_HEIGHT = 768;
    private final int TILE_BAR_HEIGHT = 23;
    private Game game;

    public GameView(Game game) {
        this.game = game;
        loadImages();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("「Golden Spin」");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setResizable(false);

        this.setVisible(true);
    }

    private void loadImages() {
        slotsTableImage = new ImageIcon("Resources/Slots.png").getImage();
        rouletteTableImage = new ImageIcon("Resources/Roulette.png").getImage();
        slotsImages = new Image[11];
        cardsImages = new Image[40];

        String[] slotNames = {"Clover", "Diamond", "Grape", "Lemon", "Mango", "Orange", "Strawberry", "Watermelon"};
        for (int i = 0; i< slotNames.length; i++) {
            slotsImages[i+1] = new ImageIcon("Resources/" + slotNames[i] + ".png").getImage();
        }

        String[] suits = {"C", "D", "H", "S"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int index = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                if (index < cardsImages.length) {
                    cardsImages[index] = new ImageIcon("Resources/" + suit + rank + ".png").getImage();
                    index++;
                }
            }
        }
    }

    public void paint(Graphics g) {
        g.drawImage(rouletteTableImage, 0, 0, 1024, 768, this);
        game.getCard().draw(g);
        g.drawImage(cardsImages[3], 100, 100, 300, 300, this);
    }

    public Image getSlotsTableImage() {
        return slotsTableImage;
    }

    public Image getRouletteTableImage() {
        return rouletteTableImage;
    }

    public Image[] getSlotsImages() {
        return slotsImages;
    }

    public Image[] getCardsImages() {
        return cardsImages;
    }
}
