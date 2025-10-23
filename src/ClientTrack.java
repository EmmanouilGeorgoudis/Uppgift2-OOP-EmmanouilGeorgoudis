import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ClientTrack extends Client {

    public void registerPass(Client client, String pathInput) throws IOException {

        Path path = Path.of(pathInput);
        List<String> updatedLines = new ArrayList<>();
        
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String textFile = Files.readString(path);
            String line;
            
            if (!textFile.contains(client.getName())) {
                try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                    writer.write(client.getName() + " " + client.getPersonalID() + ";");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            while ((line = reader.readLine()) != null) {
                String[] lineSplit = line.split(";");
                String lastValue = lineSplit[lineSplit.length - 1];
                
                if (line.contains(client.getName())) {
                    try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
                        if (!lastValue.equals(updateDateToString())) {
                            writer.write(updateDateToString() + ";");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                updatedLines.add(line);
            }
        }
    }
    
    public String getRegisterPasstoString(Client client, String pathInput) {

        String printMessage;
        Path path = Path.of(pathInput);

        String[] lineSplit = new String[0];
        int sumRegister = 0;
        try {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(client.getName())) {
                        lineSplit = line.split(";");
                        sumRegister = lineSplit.length - 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder dates = new StringBuilder();
        for (int i = 1; i < lineSplit.length; i++) {
            if (!lineSplit[i].isBlank()) {
                dates.append(lineSplit[i]).append("\n");
            }
        }

        printMessage = client.getName().toUpperCase() + "\nPersonnr: " + client.getPersonalID() +
                "\n" + client.getName().split(" ")[0] + " har tränat " +
                sumRegister + " gånger.\n\n" + dates;
        
        return printMessage;
    }

}
