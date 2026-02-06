import java.util.Scanner;

public class GuessingGame {

    private GameConfig config;
    private Scanner scanner;
    private HintService hintService;
    private ValidationService validationService;

    public GuessingGame() {
        config = new GameConfig();
        hintService = new HintService();
        validationService = new ValidationService();
        scanner = new Scanner(System.in);
        startGameLoop();
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
                    return;
                }

            } catch (InvalidGuessException e) {
                logInvalidAttempt(input);
                System.out.println("Error: " + e.getMessage());
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