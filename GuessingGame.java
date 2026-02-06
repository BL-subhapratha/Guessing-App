import java.util.Scanner;

public class GuessingGame {

    private GameConfig config;
    private Scanner scanner;
    private HintService hintService;

    public GuessingGame() {
        config = new GameConfig();
        hintService = new HintService();
        scanner = new Scanner(System.in);
        startGameLoop();
    }

    private void startGameLoop() {
        while (config.hasAttemptsLeft()) {
            System.out.print("Enter your guess: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear invalid input
                continue;
            }

            int userGuess = scanner.nextInt();
            processGuess(userGuess);

            if (userGuess == config.getTargetNumber()) {
                System.out.println("Congratulations! You guessed correctly!");
                return;
            }
        }

        System.out.println("Game Over! You've used all attempts.");
        System.out.println("The correct number was: " + config.getTargetNumber());
    }

    private void processGuess(int guess) {
        if (guess > config.getTargetNumber()) {
            config.decrementAttempts();
            System.out.println("Too High!");
            provideHint();
        } 
        else if (guess < config.getTargetNumber()) {
            config.decrementAttempts();
            System.out.println("Too Low!");
            provideHint();
        }

        System.out.println("Remaining attempts: " + config.getRemainingAttempts());
        System.out.println("----------------------------------");
    }

    private void provideHint() {
        if (config.hasHintsLeft()) {
            config.decrementHints();
            int hintCount = config.getUsedHints();
            String hint = hintService.generateHint(config.getTargetNumber(), hintCount);
            System.out.println(hint);
        }
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}