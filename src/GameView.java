import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends JFrame {
    private Image slotsTableImage;
    private Image rouletteTableImage;
    private Image instructionsImage;
    private Image[] slotsImages;
    private Image[] cardsImages;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int TILE_BAR_HEIGHT = 23;
    private final int WINDOW_WIDTH = (int)screenSize.getWidth();
    private final int WINDOW_HEIGHT = (int)screenSize.getHeight() - TILE_BAR_HEIGHT;
    private Game game;

    public GameView(Game game) {
        this.game = game;
        loadImages();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("「Golden Spin」");
        this.setSize(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void loadImages() {
        slotsTableImage = new ImageIcon("Resources/Backgrounds/Slots.png").getImage();
        rouletteTableImage = new ImageIcon("Resources/Backgrounds/Roulette.jpg").getImage();
        instructionsImage = new ImageIcon("Resources/Backgrounds/Instructions.png").getImage();
        slotsImages = new Image[11];
        cardsImages = new Image[40];

        String[] slotNames = {"clover", "diamond", "grape", "lemon", "mango", "orange", "strawberry", "watermelon"};
        for (int i = 0; i< slotNames.length; i++) {
            slotsImages[i] = new ImageIcon("Resources/Slots/" + slotNames[i] + ".jpg").getImage();
        }

        String[] suits = {"C", "D", "H", "S"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int index = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                if (index < cardsImages.length) {
                    cardsImages[index] = new ImageIcon("Resources/Cards/" + suit + rank + ".png").getImage();
                    index++;
                }
            }
        }
    }

    public void paint(Graphics g) {
        if(game.getFinishedInstructions() == true) {
            if (game.getPlayingSlots()) {
                g.drawImage(slotsTableImage, 0, 23, WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, this);
                g.setFont(new Font("DialogInput", Font.BOLD, 14));
                g.setColor(Color.WHITE);
                g.drawString("Balance: $" + game.getPoints(), 250, 160);
            } else {
                g.drawImage(rouletteTableImage, 0, 23, WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, this);
                g.setFont(new Font("DialogInput", Font.BOLD, 14));
                g.setColor(Color.WHITE);
                g.drawString("Balance: $" + game.getPoints(), 250, 95);
            }
        } else {
            g.drawImage(instructionsImage, 0, 23, WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, this);
        }
        if (!game.getPlayingSlots()) {
            Card card = game.getCard();
            if (card != null) {
                card.draw(g);
            }
        } else if (game.getPlayingSlots()) {
            for (int i = 0; i < game.getBoard().length; i++) {
                    if (game.getBoard()[i] != null) {
                        game.getBoard()[i].draw(g);
                    }
            }
        }
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
