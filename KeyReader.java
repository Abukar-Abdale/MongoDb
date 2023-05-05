import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public class KeyReader {
    private final String connectionString;

    public KeyReader() throws IOException {
        String userHome = System.getProperty("user.home");
        Path keyFilePath = Paths.get(userHome, "Documents", "connection_string.txt");
        if (!Files.exists(keyFilePath)) {
            throw new IOException("Error reading file: File not found.");
        }

        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(keyFilePath.toFile())) {
            props.load(fis);
            connectionString = props.getProperty("connection_string");
        } catch (IOException e) {
            throw new IOException("Error reading file: " + e.getMessage());
        }
    }

    public String getConnectionString() {
        return connectionString;
    }
}
