import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IOUtil {

    private List<Client> clients;


    public List<Client> readFromFile(Path path){

        List<Client> clients = new ArrayList<>();

        try(Scanner sc = new Scanner(path)){
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] input = sc.nextLine().split(";");

                String name = input[0], adress = input[1], email = input[2], personalID = input[3];

                String[] firstPaymentSplit = input[4].split("-"); //Fick bra tips av AI att skapa metod så slipper jag upprepa denna två ggr, låter det dock vara
                int[] firstPaymentDate = new int[] {
                        Integer.parseInt(firstPaymentSplit[0]),
                        Integer.parseInt(firstPaymentSplit[1]),
                        Integer.parseInt(firstPaymentSplit[2]),
                };

                String[] lastPaymentSplit = input[5].split("-");
                int[] lastPaymentDate = new int[] {
                        Integer.parseInt(lastPaymentSplit[0]),
                        Integer.parseInt(lastPaymentSplit[1]),
                        Integer.parseInt(lastPaymentSplit[2]),
                };

                String membershipInput = input[6];
                MembershipType membershipType= switch (membershipInput) {
                    case "Guld" -> MembershipType.GULD;
                    case "Platina" -> MembershipType.PLATINA;
                    case "Standard" -> MembershipType.STANDARD;
                    default -> throw new IllegalArgumentException("Okänd medlemskaps typ!");
                };

                Client client = new Client(name, adress, email, personalID, firstPaymentDate, lastPaymentDate, membershipType);

                clients.add(client);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return clients;
    }
}