import java.util.Scanner;

public class GuessingGame {

    private GameConfig config;
    private Scanner scanner;

    public GuessingGame() {
        config = new GameConfig();
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
        config.decrementAttempts();

        if (guess > config.getTargetNumber()) {
            System.out.println("Too High!");
        } else if (guess < config.getTargetNumber()) {
            System.out.println("Too Low!");
        }

        System.out.println("Remaining attempts: " + config.getRemainingAttempts());
        System.out.println("----------------------------------");
    }

    public static void main(String[] args) {
        new GuessingGame();
    }
}