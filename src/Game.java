import java.util.Scanner;
import java.util.Random;

public class Game {
    private Deck deck;
    private Player player;
    private GameView window;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private String[] ranks;
    private String[] suits;
    private int[] values;
    private String[] designs;
    private Card card;
    private Slot board[];
    private String doubleOrNothing;
    private boolean playingSlots;
    private boolean finishedInstructions;
    private int winnings;
    private final int CLOVER = 200;
    private final int DIAMOND = 100;
    private final int GRAPE = 100;
    private final int LEMON = 18;
    private final int MANGO = 14;
    private final int ORANGE = 10;
    private final int STRAWBERRY = 5;

    public Game() {
        ranks = new String[]{"A", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        suits = new String[]{"Clubs", "Diamonds", "Hearts", "Spades"};
        values = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        designs = new String[]{
                "+-----+\n|A    |\n|  ♣  |\n|    A|\n+-----+",
                "+-----+\n|2    |\n|  ♣  |\n|    2|\n+-----+",
                "+-----+\n|3    |\n|  ♣  |\n|    3|\n+-----+",
                "+-----+\n|4    |\n|  ♣  |\n|    4|\n+-----+",
                "+-----+\n|5    |\n|  ♣  |\n|    5|\n+-----+",
                "+-----+\n|6    |\n|  ♣  |\n|    6|\n+-----+",
                "+-----+\n|7    |\n|  ♣  |\n|    7|\n+-----+",
                "+-----+\n|8    |\n|  ♣  |\n|    8|\n+-----+",
                "+-----+\n|9    |\n|  ♣  |\n|    9|\n+-----+",
                "+-----+\n|10   |\n|  ♣  |\n|   10|\n+-----+",
                "+-----+\n|A    |\n|  ♦  |\n|    A|\n+-----+",
                "+-----+\n|2    |\n|  ♦  |\n|    2|\n+-----+",
                "+-----+\n|3    |\n|  ♦  |\n|    3|\n+-----+",
                "+-----+\n|4    |\n|  ♦  |\n|    4|\n+-----+",
                "+-----+\n|5    |\n|  ♦  |\n|    5|\n+-----+",
                "+-----+\n|6    |\n|  ♦  |\n|    6|\n+-----+",
                "+-----+\n|7    |\n|  ♦  |\n|    7|\n+-----+",
                "+-----+\n|8    |\n|  ♦  |\n|    8|\n+-----+",
                "+-----+\n|9    |\n|  ♦  |\n|    9|\n+-----+",
                "+-----+\n|10   |\n|  ♦  |\n|   10|\n+-----+",
                "+-----+\n|A    |\n|  ♥  |\n|    A|\n+-----+",
                "+-----+\n|2    |\n|  ♥  |\n|    2|\n+-----+",
                "+-----+\n|3    |\n|  ♥  |\n|    3|\n+-----+",
                "+-----+\n|4    |\n|  ♥  |\n|    4|\n+-----+",
                "+-----+\n|5    |\n|  ♥  |\n|    5|\n+-----+",
                "+-----+\n|6    |\n|  ♥  |\n|    6|\n+-----+",
                "+-----+\n|7    |\n|  ♥  |\n|    7|\n+-----+",
                "+-----+\n|8    |\n|  ♥  |\n|    8|\n+-----+",
                "+-----+\n|9    |\n|  ♥  |\n|    9|\n+-----+",
                "+-----+\n|10   |\n|  ♥  |\n|   10|\n+-----+",
                "+-----+\n|A    |\n|  ♠  |\n|    A|\n+-----+",
                "+-----+\n|2    |\n|  ♠  |\n|    2|\n+-----+",
                "+-----+\n|3    |\n|  ♠  |\n|    3|\n+-----+",
                "+-----+\n|4    |\n|  ♠  |\n|    4|\n+-----+",
                "+-----+\n|5    |\n|  ♠  |\n|    5|\n+-----+",
                "+-----+\n|6    |\n|  ♠  |\n|    6|\n+-----+",
                "+-----+\n|7    |\n|  ♠  |\n|    7|\n+-----+",
                "+-----+\n|8    |\n|  ♠  |\n|    8|\n+-----+",
                "+-----+\n|9    |\n|  ♠  |\n|    9|\n+-----+",
                "+-----+\n|10   |\n|  ♠  |\n|   10|\n+-----+"
        };
        this.board = new Slot[3];
        this.player = new Player("Player 1");
        window = new GameView(this);
        this.deck = new Deck(ranks, suits, values, designs, window);
    }

    public void startSlots() {
        Scanner scanner = new Scanner(System.in);
        window.repaint();
        printSlotsInstructions();
        finishedInstructions = true;
        String[] slotSymbols1 = {"🍀", "💎", "💎", "💎", "🍇", "🍇", "🍋", "🥭", "🥭", "🥭", "🥭", "🥭", "🥭", "🥭", "🍊", "🍊", "🍊", "🍊", "🍊", "🍓", "🍓"};
        String[] slotSymbols2 = {"🍀", "💎", "💎", "🍇", "🍇", "🍋", "🍋", "🍋", "🍋", "🍋", "🥭", "🥭", "🥭", "🍊", "🍊", "🍊", "🍊", "🍊", "🍓", "🍓", "🍓", "🍓", "🍓", "🍓"};
        String[] slotSymbols3 = {"🍀", "💎", "🍇", "🍇", "🍋", "🍋", "🍋", "🍋", "🍋", "🍋", "🍋", "🍋", "🥭", "🥭", "🥭", "🍊", "🍊", "🍊", "🍊", "🍉", "🍉", "🍉", "🍉"};
        int numReels = 3;
        int roundCount = 0;
        int roundQuitPoint = (int)(Math.random() * 2) + 3;
        Random random = new Random();
        while (player.getPoints() > 0) {
            clearCard();
            clearBoard();
            playingSlots = true;
            doubleOrNothing = "";
            window.repaint();
            roundCount++;
            System.out.println("\nYour Chips: " + player.getPoints());
            System.out.print("How many chips do you want to bet? [1—3 or 10 or ALL]: ");
            int betAmount = scanner.nextInt();
            scanner.nextLine();
            while (betAmount > player.getPoints() || betAmount <= 0 || betAmount > 3 && betAmount != 10 && betAmount != player.getPoints()) {
                System.out.println("INVALID INPUT");
                System.out.print("How many chips do you want to bet? [1—3 or 10 or ALL]: ");
                betAmount = scanner.nextInt();
                scanner.nextLine();
            }
            String[] reels1 = new String[numReels];
            for (int i = 0; i < numReels; i++) {
                reels1[i] = slotSymbols1[random.nextInt(slotSymbols1.length)];
                board[0] = new Slot(reels1[1], window, 245, 170);
            }
            String[] reels2 = new String[numReels];
            for (int i = 0; i < numReels; i++) {
                reels2[i] = slotSymbols2[random.nextInt(slotSymbols2.length)];
                board[1] = new Slot(reels2[1], window, 325, 170);
            }
            String[] reels3 = new String[numReels];
            for (int i = 0; i < numReels; i++) {
                reels3[i] = slotSymbols3[random.nextInt(slotSymbols3.length)];
                board[2] = new Slot(reels3[1], window, 405, 170);
            }
            window.repaint();
            checkWinningCombination(betAmount, reels1, reels2, reels3);
            if (roundCount <= roundQuitPoint && player.getPoints() > 0) {
                System.out.print("Do you want to play again? [" + ANSI_GREEN + "yes" + ANSI_RESET + "/" + ANSI_RED + "no" + ANSI_RESET + "]: ");
                String playAgain = scanner.nextLine().toLowerCase();
                while (!playAgain.equals("yes") && !playAgain.equals("no")) {
                    System.out.println("INVALID INPUT");
                    System.out.print("Do you want to play again? [" + ANSI_GREEN + "yes" + ANSI_RESET + "/" + ANSI_RED + "no" + ANSI_RESET + "]: ");
                    playAgain = scanner.nextLine().toLowerCase();
                }
                if (!playAgain.equals("yes")) {
                    break;
                }
            } else {
                System.out.println(ANSI_BLUE + "You are addicted! You can't stop now!" + ANSI_RESET);
                try {
                    if(doubleOrNothing.equals("no")) {
                        Thread.sleep(2000);
                    } else {
                        Thread.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (player.getPoints() > 0) {
            System.out.println("You finished the slots game with " + player.getPoints() + " chips!");
        } else {
            System.out.println("You're out of chips! Better luck next time!");
        }
    }

    public static boolean allReelsMatch(String symbol, String[] reels1, String[] reels2, String[] reels3) {
        return reels1[1].equals(symbol) && reels2[1].equals(symbol) && reels3[1].equals(symbol);
    }

    public static boolean twoReelsMatch(String symbol1, String symbol2, String[] reels1, String[] reels2, String[] reels3) {
        return (reels1[1].equals(symbol1) && reels2[1].equals(symbol1) && reels3[1].equals(symbol2)) ||
                (reels1[1].equals(symbol1) && reels2[1].equals(symbol2) && reels3[1].equals(symbol1)) ||
                (reels1[1].equals(symbol2) && reels2[1].equals(symbol1) && reels3[1].equals(symbol1));
    }

    public static int calculateWinnings(int betAmount, int win1, int win2, int win3, int win10, int winALL) {
        if (betAmount == 1) {
            return win1;
        } else if (betAmount == 2) {
            return win2;
        } else if (betAmount == 3) {
            return win3;
        } else if (betAmount == 10) {
            return win10;
        } else {
            return winALL;
        }
    }

    public void checkWinningCombination(int betAmount, String[] reels1, String[] reels2, String[] reels3) {
        Scanner scanner = new Scanner(System.in);
        winnings = 0;
        if (allReelsMatch("🍀", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, CLOVER, CLOVER * 2, CLOVER * 3, CLOVER * 10, player.getPoints() * 7777777);
        } else if (allReelsMatch("💎", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, DIAMOND, DIAMOND * 2, DIAMOND * 3, DIAMOND * 10, player.getPoints() * 100);
        } else if (allReelsMatch("🍇", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, GRAPE, GRAPE * 2, GRAPE * 3, GRAPE * 10, player.getPoints() * 12);
        } else if (allReelsMatch("🍋", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, LEMON, LEMON * 2, LEMON * 3, LEMON * 10, player.getPoints() * 11);
        } else if (allReelsMatch("🥭", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, MANGO, MANGO * 2, MANGO * 3, MANGO * 10, player.getPoints() * 10);
        } else if (allReelsMatch("🍊", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, ORANGE, ORANGE * 2, ORANGE * 3, ORANGE * 10, player.getPoints() * 9);
        } else if (allReelsMatch("🍓", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, STRAWBERRY, STRAWBERRY * 2, STRAWBERRY * 3, STRAWBERRY * 10, player.getPoints() * 8);
        } else if (twoReelsMatch("🍇", "💎", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, GRAPE, GRAPE * 2, GRAPE * 3, GRAPE * 10, player.getPoints() * 7);
        } else if (twoReelsMatch("🍋", "💎", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, LEMON, LEMON * 2, LEMON * 3, LEMON * 10, player.getPoints() * 6);
        } else if (twoReelsMatch("🥭", "💎", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, MANGO, MANGO * 2, MANGO * 3, MANGO * 10, player.getPoints() * 5);
        } else if (twoReelsMatch("🍊", "💎", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, ORANGE, ORANGE * 2, ORANGE * 3,ORANGE * 10, player.getPoints() * 4);
        } else if ((reels1[1].equals(reels2[1]) && reels1[1].equals("🍓")) || (reels1[1].equals(reels3[1]) && (reels1[1].equals("🍓")) || (reels2[1].equals(reels3[1]) && (reels2[1].equals("🍓"))))) {
            winnings = calculateWinnings(betAmount, 5, 10, 15, 50, player.getPoints() * 3);
        } else if ((reels1[1].equals("🍓")) || reels2[1].equals("🍓") || reels3[1].equals("🍓")) {
            winnings = calculateWinnings(betAmount, 2, 4, 6, 20, player.getPoints() * 2);
        } if (winnings == 0) {
            System.out.println("No match. You win " + winnings + " chips!");
        } else {
            System.out.println("You win " + winnings + " chips!");
        }
        if (winnings > 0) {
            System.out.print("Would you like to Double or Nothing " + winnings + " chips? (" + (2 * winnings) + ") [" + ANSI_GREEN + "yes" + ANSI_RESET + "/" + ANSI_RED + "no" + ANSI_RESET + "]: ");
            doubleOrNothing = scanner.nextLine().toLowerCase();
            while (!doubleOrNothing.equals("yes") && !doubleOrNothing.equals("no")) {
                System.out.println("INVALID INPUT");
                System.out.print("Would you like to Double or Nothing " + winnings + " chips? (" + (2 * winnings) + ") [" + ANSI_GREEN + "yes" + ANSI_RESET + "/" + ANSI_RED + "no" + ANSI_RESET + "]: ");
                doubleOrNothing = scanner.nextLine().toLowerCase();
            }
            boolean playerWinsDouble;
            if (doubleOrNothing.equals("yes")) {
                playingSlots = false;
                window.repaint();
                System.out.print(ANSI_YELLOW + "Golden Spin 🌟" + ANSI_RESET + "! Bet on [even/odd/red/black]: ");
                String betType = scanner.nextLine().toLowerCase();
                while (!betType.equals("even") && !betType.equals("odd") && !betType.equals("red") && !betType.equals("black") && !betType.equals("gamerhacker69")) {
                    System.out.println("INVALID INPUT");
                    System.out.print("Bet on [even/odd/red/black]: ");
                    betType = scanner.nextLine().toLowerCase();
                }
                card = deck.deal();
                window.repaint();
                System.out.println("Card Drawn: " + card);
                playerWinsDouble = checkIfPlayerWins(betType, card);
                if (playerWinsDouble) {
                    System.out.println("You doubled your reward and earned " + (2 * winnings) + " chips!");
                    player.addPoints((2 * winnings) - betAmount);
                } else if (!playerWinsDouble) {
                    System.out.println("You lost your reward.");
                    player.addPoints(-betAmount);
                }
            } if (doubleOrNothing.equals("no")) {
                System.out.println("You left with " + winnings + " chips.");
                player.addPoints(winnings - betAmount);
            }
        } else {
            player.addPoints(winnings - betAmount);
        }
    }

    private boolean checkIfPlayerWins(String betType, Card card) {
        return switch (betType.toLowerCase()) {
            case "even" -> card.getValue() % 2 == 0;
            case "odd" -> card.getValue() % 2 == 1;
            case "red" -> card.getSuit().equals("Hearts") || card.getSuit().equals("Diamonds");
            case "black" -> card.getSuit().equals("Clubs") || card.getSuit().equals("Spades");
            default -> false;
        };
    }

    public void printSlotsInstructions() {
        window.repaint();
        Scanner scanner = new Scanner(System.in);
        System.out.print(ANSI_GREEN + "PRESS ENTER TO CONTINUE:" + ANSI_RESET);
        scanner.nextLine();
    }

    public int getPoints() {
        return player.getPoints();
    }

    public boolean getFinishedInstructions() {
        return finishedInstructions;
    }

    public Card getCard() {
        return card;
    }

    public void clearCard() {
        this.card = null;
    }

    public Slot[] getBoard() {
        return board;
    }

    public void clearBoard() {
        for (int i = 0; i < 3 ; i++) {
            board[i] = null;
        }
    }

    public boolean getPlayingSlots() {
        return playingSlots;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startSlots();
    }
}