import java.util.Random;


public class GameConfig {

    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 7;
    private static final int MAX_HINTS = 3;

    private int targetNumber;
    private int remainingAttempts;
    private int remainingHints;

    public GameConfig() {
        initializeGame();
        displayWelcomeMessage();
    }

    private void initializeGame() {
        Random random = new Random();
        targetNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;

        remainingAttempts = MAX_ATTEMPTS;
        remainingHints = MAX_HINTS;
    }

    private void displayWelcomeMessage() {
        System.out.println("==================================");
        System.out.println(" Welcome to the Number Guessing Game!");
        System.out.println("==================================");
        System.out.println("Rules:");
        System.out.println("- Guess a number between " + MIN_NUMBER + " and " + MAX_NUMBER);
        System.out.println("- You have " + remainingAttempts + " attempts");
        System.out.println("- You can use up to " + remainingHints + " hints");
        System.out.println("- Try to guess the number correctly!");
        System.out.println("==================================");
    }

    public static void main(String[] args) {
        new GameConfig();
    }
}