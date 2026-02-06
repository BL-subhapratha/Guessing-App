public class ValidationService {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;

    public int validateGuess(String input) throws InvalidGuessException {

        if (input == null || input.trim().isEmpty()) {
            throw new InvalidGuessException("Input cannot be empty.");
        }

        int guess;
        try {
            guess = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new InvalidGuessException("Please enter a valid numeric value.");
        }

        if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
            throw new InvalidGuessException(
                "Guess must be between " + MIN_NUMBER + " and " + MAX_NUMBER + "."
            );
        }

        return guess; // accepted input
    }
}