public class GameResult {

    private String playerName;
    private int attemptsUsed;
    private boolean isWin;

    public GameResult(String playerName, int attemptsUsed, boolean isWin) {
        this.playerName = playerName;
        this.attemptsUsed = attemptsUsed;
        this.isWin = isWin;
    }

    public String toFileString() {
        return playerName + "," + attemptsUsed + "," + (isWin ? "WIN" : "LOSS");
    }

    public static GameResult fromFileString(String line) {
        String[] parts = line.split(",");
        return new GameResult(
                parts[0],
                Integer.parseInt(parts[1]),
                parts[2].equals("WIN")
        );
    }

    @Override
    public String toString() {
        return "Player: " + playerName +
               ", Attempts: " + attemptsUsed +
               ", Result: " + (isWin ? "WIN" : "LOSS");
    }
}