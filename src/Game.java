import java.util.Scanner;
import java.util.Random;

public class Game {
    private Deck deck;
    private Player player;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public Game(String[] ranks, String[] suits, int[] values, String[] designs) {
        this.deck = new Deck(ranks, suits, values, designs);
        this.player = new Player("Player 1");
    }

    public void startSlots() {
        Scanner scanner = new Scanner(System.in);
        printSlotsInstructions();
        // List of slot possibilities, varying for each reel
        String[] slotSymbols1 = {"🍀", "💎", "💎", "💎", "🍇", "🍇", "🍋", "🥭", "🥭", "🥭", "🥭", "🥭", "🥭", "🥭", "🍊", "🍊", "🍊", "🍊", "🍊", "🍓", "🍓"};
        String[] slotSymbols2 = {"🍀", "💎", "💎", "🍇", "🍇", "🍋", "🍋", "🍋", "🍋", "🍋", "🥭", "🥭", "🥭", "🍊", "🍊", "🍊", "🍊", "🍊", "🍓", "🍓", "🍓", "🍓", "🍓", "🍓"};
        String[] slotSymbols3 = {"🍀", "💎", "🍇", "🍇", "🍋", "🍋", "🍋", "🍋", "🍋", "🍋", "🍋", "🍋", "🥭", "🥭", "🥭", "🍊", "🍊", "🍊", "🍊", "🍉", "🍉", "🍉", "🍉"};
        int numReels = 3;
        // Addiction counters
        int roundCount = 0;
        int roundQuitPoint = (int)(Math.random() * 6) + 5;
        Random random = new Random();
        while (player.getPoints() > 0) {
            // Get bet amount
            roundCount++;
            System.out.println("\nYour Chips: " + player.getPoints());
            System.out.print("How many chips do you want to bet? [1—3]: ");
            int betAmount = scanner.nextInt();
            scanner.nextLine();
            // Validate bet amount
            while (betAmount > player.getPoints() || betAmount <= 0 || betAmount > 3) {
                System.out.println("INVALID INPUT");
                System.out.print("How many chips do you want to bet? [1—3]: ");
                betAmount = scanner.nextInt();
                scanner.nextLine();
            }
            System.out.println("Spinning the reels 🎡");
            String[] reels1 = new String[numReels];
            // Set each reel to a random symbol
            for (int i = 0; i < numReels; i++) {
                reels1[i] = slotSymbols1[random.nextInt(slotSymbols1.length)];
            }
            String[] reels2 = new String[numReels];
            for (int i = 0; i < numReels; i++) {
                reels2[i] = slotSymbols2[random.nextInt(slotSymbols2.length)];
            }
            String[] reels3 = new String[numReels];
            for (int i = 0; i < numReels; i++) {
                reels3[i] = slotSymbols3[random.nextInt(slotSymbols3.length)];
            }
            System.out.println("   " + reels1[0] + " | " + reels2[0] + " | " + reels3[0]);
            System.out.println("—> " + ANSI_GREEN + reels1[1] + " | " + reels2[1] + " | " + reels3[1] + ANSI_RESET + " <—");
            System.out.println("   " + reels1[2] + " | " + reels2[2] + " | " + reels3[2]);
            int matchingSymbols = 1;
            int winnings = 0;
            // Method to see if it wins any chips
            checkWinningCombination(betAmount, reels1, reels2, reels3);
            // Play again option if not addicted yet
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
            }
        }
        // End game
        if (player.getPoints() > 0) {
            System.out.println("You finished the slots game with " + player.getPoints() + " chips!");
        } else {
            System.out.println("You're out of chips! Better luck next time!");
        }
    }

    public static boolean allReelsMatch(String symbol, String[] reels1, String[] reels2, String[] reels3) {
        // True or false if all reels match (Jackpot)
        return reels1[1].equals(symbol) && reels2[1].equals(symbol) && reels3[1].equals(symbol);
    }

    public static boolean twoReelsMatch(String symbol1, String symbol2, String[] reels1, String[] reels2, String[] reels3) {
        // True or false if 2 reels match
        return (reels1[1].equals(symbol1) && reels2[1].equals(symbol1) && reels3[1].equals(symbol2)) ||
                (reels1[1].equals(symbol1) && reels2[1].equals(symbol2) && reels3[1].equals(symbol1)) ||
                (reels1[1].equals(symbol2) && reels2[1].equals(symbol1) && reels3[1].equals(symbol1));
    }

    public static int calculateWinnings(int betAmount, int win1, int win2, int win3) {
        // Either win 1x, 2x, or 3x based on betAmount
        if (betAmount == 1) {
            return win1;
        } else if (betAmount == 2) {
            return win2;
        } else {
            return win3;
        }
    }

    public void checkWinningCombination(int betAmount, String[] reels1, String[] reels2, String[] reels3) {
        Scanner scanner = new Scanner(System.in);
        int winnings = 0;
        // Winning possibilities and rewards
        if (allReelsMatch("🍀", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 200, 400, 600);
        } else if (allReelsMatch("💎", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 100, 200, 300);
        } else if (allReelsMatch("🍇", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 100, 200, 300);
        } else if (allReelsMatch("🍋", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 18, 36, 54);
        } else if (allReelsMatch("🥭", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 14, 28, 42);
        } else if (allReelsMatch("🍊", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 10, 20, 30);
        } else if (allReelsMatch("🍓", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 5, 10, 15);
        } else if (twoReelsMatch("🍇", "💎", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 100, 200, 300);
        } else if (twoReelsMatch("🍋", "💎", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 18, 36, 54);
        } else if (twoReelsMatch("🥭", "💎", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 14, 28, 42);
        } else if (twoReelsMatch("🍊", "💎", reels1, reels2, reels3)) {
            winnings = calculateWinnings(betAmount, 10, 20, 30);
        } else if ((reels1[1].equals(reels2[1]) && reels1[1].equals("🍓")) || (reels1[1].equals(reels3[1]) && (reels1[1].equals("🍓")) || (reels2[1].equals(reels3[1]) && (reels2[1].equals("🍓"))))) {
            winnings = calculateWinnings(betAmount, 5, 10, 15);
        } else if ((reels1[1].equals("🍓")) || reels2[1].equals("🍓") || reels3[1].equals("🍓")) {
            winnings = calculateWinnings(betAmount, 2, 4, 6);
        } if (winnings == 0) {
            System.out.println("No match. You win " + winnings + " chips!");
        } else {
            System.out.println("You win " + winnings + " chips!");
        }
        String doubleOrNothing = "";
        if (winnings > 0) {
            // Double or nothing
            System.out.print("Would you like to Double or Nothing " + winnings + " chips? (" + (2 * winnings) + ") [" + ANSI_GREEN + "yes" + ANSI_RESET + "/" + ANSI_RED + "no" + ANSI_RESET + "]: ");
            doubleOrNothing = scanner.nextLine().toLowerCase();
            while (!doubleOrNothing.equals("yes") && !doubleOrNothing.equals("no")) {
                System.out.println("INVALID INPUT");
                System.out.print("Would you like to Double or Nothing " + winnings + " chips? (" + (2 * winnings) + ") [" + ANSI_GREEN + "yes" + ANSI_RESET + "/" + ANSI_RED + "no" + ANSI_RESET + "]: ");
                doubleOrNothing = scanner.nextLine().toLowerCase();
            }
            boolean playerWinsDouble = false;
            if (doubleOrNothing.equals("yes")) {
                // Get betType (even odd red black)
                System.out.print(ANSI_YELLOW + "Golden Spin 🌟" + ANSI_RESET + "! Bet on [even/odd/red/black]: ");
                String betType = scanner.nextLine().toLowerCase();
                while (!betType.equals("even") && !betType.equals("odd") && !betType.equals("red") && !betType.equals("black") && !betType.equals("gamerhacker69")) {
                    System.out.println("INVALID INPUT");
                    System.out.print("Bet on [even/odd/red/black]: ");
                    betType = scanner.nextLine().toLowerCase();
                }
                // Deal a card from the deck
                Card card = deck.deal();
                System.out.println("Card Drawn: " + card);
                // Method to check if the betType equals the cardType
                playerWinsDouble = checkIfPlayerWins(betType, card);
                if (playerWinsDouble) {
                    System.out.println("You doubled your reward and earned " + (2 * winnings) + " chips!");
                    player.addPoints((2 * winnings) - betAmount);
                } else if (!playerWinsDouble) {
                    System.out.println("You lost your reward.");
                    player.addPoints(-betAmount);
                }
            } if (doubleOrNothing.equals("no")) {
                System.out.println("You walked away from doubling your chips, leaving with " + winnings + " chips.");
                player.addPoints(winnings - betAmount);
            }
        } else {
            player.addPoints(winnings - betAmount);
        }
    }

    private boolean checkIfPlayerWins(String betType, Card card) {
        // Switch case to see if the betType equals the cardType (basically a simplified version of if/else)
        return switch (betType.toLowerCase()) {
            case "even" -> card.getValue() % 2 == 0;
            case "odd" -> card.getValue() % 2 == 1;
            case "red" -> card.getSuit().equals("Hearts") || card.getSuit().equals("Diamonds");
            case "black" -> card.getSuit().equals("Clubs") || card.getSuit().equals("Spades");
            default -> false;
        };
    }

    public void printSlotsInstructions() {
        // Logo & instructions
        System.out.println(ANSI_BLUE + "  ________       .__       .___                _________      .__        \n" +
                " /  _____/  ____ |  |    __| _/____   ____    /   _____/_____ |__| ____  \n" +
                "/   \\  ___ /  _ \\|  |   / __ |/ __ \\ /    \\   \\_____  \\\\____ \\|  |/    \\ \n" +
                "\\    \\_\\  (  <_> )  |__/ /_/ \\  ___/|   |  \\  /        \\  |_> >  |   |  \\\n" +
                " \\______  /\\____/|____/\\____ |\\___  >___|  / /_______  /   __/|__|___|  /\n" +
                "        \\/                  \\/    \\/     \\/          \\/|__|           \\/ " + ANSI_RESET);
        System.out.println("In this game, you'll spin three reels with different symbols.");
        System.out.println("If you combine the correct combinations, you get chips back!");
        System.out.println("If you win a reward, you will have a chance to play... Double or Nothing!");
        System.out.println("Make sure to not gamble too much though, you will become addicted!");
        Scanner scanner = new Scanner(System.in);
        System.out.print(ANSI_GREEN + "PRESS ENTER TO CONTINUE:" + ANSI_RESET);
        scanner.nextLine();
        clearScreen();
    }

    public void clearScreen() {
        // Clears the screen by printing a bunch of empty rows
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
        System.out.flush();
    }

    public static void main(String[] args) {
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        String[] designs = {
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
        Game game = new Game(ranks, suits, values, designs);
        game.startSlots();
    }
}