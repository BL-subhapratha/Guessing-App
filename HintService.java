public class HintService {

    public String generateHint(int targetNumber, int hintCount) {

        switch (hintCount) {

            case 1:
                // Hint 1: Even or Odd
                return targetNumber % 2 == 0
                        ? "Hint: The number is EVEN."
                        : "Hint: The number is ODD.";

            case 2:
                // Hint 2: Range hint
                int lowerBound = (targetNumber / 10) * 10;
                int upperBound = lowerBound + 10;
                return "Hint: The number lies between " + lowerBound + " and " + upperBound + ".";

            case 3:
                // Hint 3: Divisibility hint
                if (targetNumber % 5 == 0) {
                    return "Hint: The number is divisible by 5.";
                } else if (targetNumber % 3 == 0) {
                    return "Hint: The number is divisible by 3.";
                } else {
                    return "Hint: The number is NOT divisible by 2, 3, or 5.";
                }

            default:
                return "No more hints available.";
        }
    }
}