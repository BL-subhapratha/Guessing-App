import java.util.Scanner;

public class GuessingGame {

    private GameConfig config;
    private Scanner scanner;
    private HintService hintService;
    private ValidationService validationService;
    private StorageService storageService;
    private String playerName;
    private boolean playAgain;

    private void askForRestart() {
        System.out.print("Do you want to play again? (Y/N): ");
        String choice = scanner.nextLine().trim().toUpperCase();

        if (!choice.equals("Y")) {
            playAgain = false;
        } else {
            System.out.println("Restarting game...");
        }
    }

    private void shutdown() {
        System.out.println("==================================");
        System.out.println("Thank you for playing!");
        System.out.println("Final summary saved successfully.");
        System.out.println("Goodbye!");
        System.out.println("==================================");

        scanner.close();
    }

    public GuessingGame() {
        scanner = new Scanner(System.in);
        playAgain = true;

        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();

        storageService = new StorageService();
        hintService = new HintService();
        validationService = new ValidationService();

        while (playAgain) {
            config = new GameConfig();   // RESET game state
            displayPreviousResults();
            startGameLoop();
            askForRestart();
        }

        shutdown();
    }

    private void displayPreviousResults() {
        System.out.println("Previous Game Results:");
        for (GameResult result : storageService.loadResults()) {
            System.out.println(result);
        }
        System.out.println("----------------------------------");
    }

     private void logInvalidAttempt(String input) {
        System.out.println("[LOG] Invalid input received: \"" + input + "\"");
    }
    
    private void startGameLoop() {
        while (config.hasAttemptsLeft()) {
            System.out.print("Enter your guess: ");
            String input = scanner.nextLine();

            try {
                int userGuess = validationService.validateGuess(input);
                processGuess(userGuess);

                if (userGuess == config.getTargetNumber()) {
                    System.out.println("Congratulations! You guessed correctly!");

                    int attemptsUsed = 7 - config.getRemainingAttempts();
                    storageService.saveResult(
                        new GameResult(playerName, attemptsUsed, true)
                    );
                    return;
                }

            } catch (InvalidGuessException e) {
                logInvalidAttempt(input);
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Game Over! You've used all attempts.");
        System.out.println("The correct number was: " + config.getTargetNumber());

        storageService.saveResult(
            new GameResult(playerName, 7, false)
        );
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