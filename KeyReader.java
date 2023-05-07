import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class KeyReader {
    public static String getConnectionString() {
        String connectionString = null;
        try {
            String path = System.getProperty("user.home") + "\\Documents\\MONGODB_CONNECTION_STRING.txt";
            BufferedReader reader = new BufferedReader(new FileReader(path));
            connectionString = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connectionString;
    }
}
