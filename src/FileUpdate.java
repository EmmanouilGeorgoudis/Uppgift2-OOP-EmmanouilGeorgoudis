import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileUpdate extends Client {


    public void update(Client client, String pathInput) throws IOException {

        Path path = Path.of(pathInput);
        List<String> updatedLines = new ArrayList<>();

        try {

            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(client.getName())) {
                        line = line.replace(client.getLastPaymentDateToString(), updateDateToString());
                    }
                    updatedLines.add(line);
                }
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for (String line : updatedLines) {
                    writer.write(line);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
