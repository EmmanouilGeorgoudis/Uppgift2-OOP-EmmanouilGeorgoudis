import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizedClientTest {

    @Test
    public void AuthorizedClientTest(){
        int[] firstPay = new int[]{2004, 4, 15};
        int[] lastPay = new int[]{2025, 5, 11};

        Client client1 = new Client("Name", "Adress", "email", "personalID", firstPay, lastPay, MembershipType.PLATINA);

        List<Client> clients = new ArrayList<>();
        clients.add(client1);

        ClientAuthorizator test = new ClientAuthorizator(clients);

        String nameTest = "";
        for (AuthorizedClient ac : test.getAuthorizedClients()) {
            nameTest = ac.getName();
        }
        assertEquals(nameTest,client1.getName());
    }

}