import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);

        IOUtil ioUtil = new IOUtil();
        FileUpdate fileUpdate = new FileUpdate();
        ClientTrack clientTrack = new ClientTrack();

        Path oldFile = Paths.get("src/gym_medlemmar.txt");
        Path clientFile = Paths.get("src/gym_medlemmar_update.txt");
        Path PTsList = Path.of("src/gym_medlemmar_clientTracking.txt");

        try {
            if (!Files.exists(clientFile)) {
                Files.copy(oldFile, clientFile);
            }
            if (!Files.exists(PTsList)) {
                Files.createFile(PTsList);
            }
        } catch (IOException e) {
            throw new IllegalStateException("Kopiering gick fel");
        }

        List<Client> clients = ioUtil.readFromFile(clientFile);

        ClientAuthorizator clientAuthorizator = new ClientAuthorizator(clients);
        List<AuthorizedClient> authorizedClients = clientAuthorizator.getAuthorizedClients();
        List<UnauthorizedClient> unauthorizedClients = clientAuthorizator.getUnauthorizedClients();

        String input = JOptionPane.showInputDialog(null, "Ange kundens \"Namn Efternamn\": ").trim();

        int yesNoOption;
        boolean foundMatch = false;

        for (Client c : clients) {
            if (input.equalsIgnoreCase(c.getName())) {
                foundMatch = true;

                if (clientAuthorizator.isAuthorized(c.getLastPaymentDate())) {
                    c = new AuthorizedClient(c);
                    yesNoOption = JOptionPane.showConfirmDialog(null, c.toString() + "\n\n" +
                                    "Vill " + c.getName().split(" ")[0] + " träna nu? ",
                            "Medlem", JOptionPane.YES_NO_OPTION);
                    if (yesNoOption == 0) {
                        clientTrack.registerPass(c, PTsList.toString());
                    }
                    yesNoOption = JOptionPane.showConfirmDialog(null, "Vill du se " +
                            c.getName() + "s historik?", "Tränings Historik", JOptionPane.YES_NO_OPTION);
                    if (yesNoOption == 0) {
                        String message = clientTrack.getRegisterPasstoString(c, PTsList.toString());
                        JOptionPane.showMessageDialog(null, message);
                    }

                } else {
                    c = new UnauthorizedClient(c);
                    yesNoOption = JOptionPane.showConfirmDialog(null, c.toString() +
                                    "\n\nVill " + c.getName().split(" ")[0] + " förnya sitt medlemskap? ",
                            "Ny betalning? ", JOptionPane.YES_NO_OPTION);
                    if (yesNoOption == 0) {
                        try {
                            fileUpdate.update(c, clientFile.toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        if (!foundMatch) {
            System.out.println("Ej medlem");
        }
    }
}