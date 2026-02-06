import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageService {

    private static final String FILE_NAME = "game_results.txt";

    public void saveResult(GameResult result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(result.toFileString());
            writer.newLine();
            System.out.println("Game result saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save game result.");
        }
    }

    public List<GameResult> loadResults() {
        List<GameResult> results = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                results.add(GameResult.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("No previous game records found.");
        }

        return results;
    }
}