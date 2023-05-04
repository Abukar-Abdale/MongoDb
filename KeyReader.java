import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KeyReader {
    public static String readConnectionString(String filePath) {
        String connectionString = null;
        try (BufferedReader br = new BufferedReader(new FileReader(userHome, "Documents","connection_string.txt"))) {
            connectionString = br.readLine();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return connectionString;
    }
}
