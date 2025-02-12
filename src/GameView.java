import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private Image slotsTableImage;
    private Image rouletteTableImage;
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
        rouletteTableImage = new ImageIcon("Resources/Backgrounds/Roulette.png").getImage();
        slotsImages = new Image[11];
        cardsImages = new Image[40];

        String[] slotNames = {"Clover", "Diamond", "Grape", "Lemon", "Mango", "Orange", "Strawberry", "Watermelon"};
        for (int i = 0; i< slotNames.length; i++) {
            slotsImages[i+1] = new ImageIcon("Resources/Slots/" + slotNames[i] + ".jpg").getImage();
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

    private void drawInstructions(Graphics g) {
        String instructionText = getInstructions();
        g.setFont(new Font("DialogInput", Font.PLAIN, 14));
        g.setColor(Color.BLACK);
        int yPos = 75;
        for (String line : instructionText.split("\n")) {
            g.drawString(line, 75, yPos);
            yPos += 20;
        }
    }

    private String getInstructions() {
        return "Welcome to Golden Spin\n" + "In this game, you'll spin three reels with different symbols.\n" + "If you combine the correct combinations, you get chips back!\n" + "If you win a reward, you will have a chance to play... Double or Nothing!\n" + "Make sure to not gamble too much though, you will become addicted!\n" + "Press ENTER to continue...";
    }

    public void paint(Graphics g) {
        if(game.getFinishedInstructions() == true) {
            g.drawImage(rouletteTableImage, 0, 23, WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, this);
        } else {
            drawInstructions(g);
        }

        Card card = game.getCard();
        if (card != null) {
            card.draw(g);
        }

        for (int i = 0; i < game.getBoard().length; i++) {
            for (int j = 0; j < game.getBoard().length; j++) {
                if (game.getBoard()[i][j] != null) {
                    game.getBoard()[i][j].draw(g);
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
